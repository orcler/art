package com.art.ctrl;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.schema.MissionSchema;
import com.art.schema.TrafficSchema;
import com.art.service.TrafficService;
import com.art.util.PubFun;

public class CtrlOutManager implements Controller {

    private TrafficService trafficService;
    private String msg;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession tHttpSession = request.getSession();
	String tUserId = (String) tHttpSession.getAttribute("userId");
	Date tCurDate = PubFun.getCurSqlDate();
	String tCurTime = PubFun.getCurTime();
	String tOldSerialNo = request.getParameter("out_tfserialno");
	String tWF_SerialNo = request.getParameter("or_wfserialno");
	String tIsPayMoney = request.getParameter("or_paymode");
	String tActivityId = request.getParameter("or_activityId");
	String tRemark = request.getParameter("orRemark");
	String tType = null;
	if ("2000000001".equals(tActivityId)) {
	    tType = "UPDATE";
	} else {
	    tType = "INSERT";
	    tWF_SerialNo = PubFun.getSerialNo("WF");
	}
	// 工作流
	MissionSchema tMissionSchema = new MissionSchema();
	TrafficSchema tTrafficSchema = new TrafficSchema();
	String tTF_SerialNo = null;
	System.out.println(tOldSerialNo);
	boolean isPay =  "on".equals(tIsPayMoney);
	System.out.println(tIsPayMoney);
	if (!isPay) {// 使用还款，不需要入库车
	    tTF_SerialNo = PubFun.getSerialNo("TF");
	    String tEngineNo = request.getParameter("orEngineNo");
	    String tVIN = request.getParameter("orVIN");
	    String tModel = request.getParameter("orModel");
	    String tStrCost = request.getParameter("orCost");
	    String tCert = request.getParameter("orCost");
	    String tStrMileage = request.getParameter("orMileage");
	    String tColor = request.getParameter("orColor");
	    String tAttn = request.getParameter("orAttn");
	    String tPhone = request.getParameter("orPhone");
	    String tComcode = request.getParameter("orComcode");

	    Date tInDate = tCurDate;
	    String tIntime = tCurTime;
	    System.out.println("----" + tAttn);
	    if (tEngineNo == null || tEngineNo == null) {
		return new ModelAndView("login");
	    }
	    double tCost = 0.0;
	    if (tStrCost != null && !"".equals(tStrCost)) {
		tCost = Double.parseDouble(tStrCost);
	    }
	    double tMileage = 0.0;
	    if (tStrMileage != null && !"".equals(tStrMileage)) {
		tMileage = Double.parseDouble(tStrMileage);
	    }
	    // 入库信息
	    tTrafficSchema.setSerialNo(tTF_SerialNo);
	    tTrafficSchema.setEngineNo(tEngineNo);
	    tTrafficSchema.setVIN(tVIN);
	    tTrafficSchema.setModel(tModel);
	    tTrafficSchema.setCost(tCost);
	    tTrafficSchema.setCert(tCert);
	    tTrafficSchema.setMileage(tMileage);
	    tTrafficSchema.setColor(tColor);
	    tTrafficSchema.setAttn(tAttn);
	    tTrafficSchema.setPhone(tPhone);
	    tTrafficSchema.setComcode(tComcode);
	    tTrafficSchema.setIndate(tInDate);
	    tTrafficSchema.setIntime(tIntime);
	    tTrafficSchema.setState("1"); // 入库
	}
	tTrafficSchema.setRemark(tRemark);
	
	tMissionSchema.setMissionid(tWF_SerialNo);
	tMissionSchema.setSubmissionid("1");
	tMissionSchema.setProcessid("0000000002");
	tMissionSchema.setMissionprop1(tTF_SerialNo);// 入库流水号
	tMissionSchema.setMissionprop2(tOldSerialNo);// 出库流水号
	tMissionSchema.setMissionprop3(tIsPayMoney);
	tMissionSchema.setActivityid("2000000002");
	tMissionSchema.setActivitystatus("0");
	tMissionSchema.setCreateoperator(tUserId);
	tMissionSchema.setLastoperator(tUserId);
	tMissionSchema.setMainmissionid(tWF_SerialNo);
	tMissionSchema.setMakedate(tCurDate);
	tMissionSchema.setMaketime(tCurTime);
	tMissionSchema.setModifydate(tCurDate);
	tMissionSchema.setModifytime(tCurTime);
	tMissionSchema.setOutdate(tCurDate);
	tMissionSchema.setOuttime(tCurTime);
	
	if (checkerData()) {
	    if ("INSERT".equals(tType)) {
		msg = trafficService.outRecord(isPay, tTrafficSchema, tMissionSchema);
	    } else {
		msg = trafficService.outModify(isPay, tTrafficSchema, tMissionSchema);
	    }
	}
	if (msg == null) {
	    msg = "保存成功！";
	}
	request.setAttribute("msg", msg);
	return new ModelAndView("msg");
    }

    public boolean checkerData() {
	msg = "测试校验不通过";
	return true;
    }

    public TrafficService getTrafficService() {
	return trafficService;
    }

    public void setTrafficService(TrafficService trafficService) {
	this.trafficService = trafficService;
    }

}
