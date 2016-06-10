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

public class CtrlInManager implements Controller {
    
    private TrafficService trafficService;
    private String msg;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession tHttpSession = request.getSession();
		String tUserId = (String) tHttpSession.getAttribute("userId");
		Date tCurDate = PubFun.getCurSqlDate();
		String tCurTime = PubFun.getCurTime();
		
		String tTF_SerialNo = PubFun.getSerialNo("TF");
		String tEngineNo = request.getParameter("EngineNo");
		String tVIN = request.getParameter("VIN");
		String tModel = request.getParameter("model");
		String tStrCost = request.getParameter("cost");
		String tCert = request.getParameter("cert");
		String tStrMileage = request.getParameter("mileage");
		String tColor = request.getParameter("color");
		String tAttn = request.getParameter("attn");
		String tPhone  = request.getParameter("phone");
		String tComcode = request.getParameter("comcode");
		Date tInDate =tCurDate;
		String tIntime  = tCurTime;
		Date tOutdate = tCurDate;
		String tOuttime = tCurTime;
		String tRemark  = request.getParameter("remark");
		System.out.println("----" + tAttn);
		if (tEngineNo == null || tEngineNo == null)
		{
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
		
		//入库信息
		TrafficSchema tTrafficSchema = new TrafficSchema();
		tTrafficSchema.setSerialNo(tTF_SerialNo);
		tTrafficSchema.setEngineNo(tEngineNo);
		tTrafficSchema.setVIN	(tVIN);
		tTrafficSchema.setModel(tModel);
		tTrafficSchema.setCost(tCost);
		tTrafficSchema.setCert(tCert);
		tTrafficSchema.setMileage(tMileage);
		tTrafficSchema.setColor(tColor);
		tTrafficSchema.setAttn(tAttn	);
		tTrafficSchema.setPhone(tPhone);
		tTrafficSchema.setComcode(tComcode);
		tTrafficSchema.setIndate(tInDate);
		tTrafficSchema.setIntime(tIntime);
		tTrafficSchema.setRemark(tRemark);
		tTrafficSchema.setState("1"); //入库
		tTrafficSchema.setUwflag("1");//入库待审核
		
		//工作流
		MissionSchema tMissionSchema = new MissionSchema();
		String tWF_SerialNo = PubFun.getSerialNo("WF");
		tMissionSchema.setMissionid(tWF_SerialNo);
		tMissionSchema.setMissionprop1(tTF_SerialNo);//入库流水号
		tMissionSchema.setActivityid("1000000002");
		tMissionSchema.setActivitystatus("0");
		tMissionSchema.setProcessid("0000000001");
		tMissionSchema.setCreateoperator(tUserId);
		tMissionSchema.setLastoperator(tUserId);
		tMissionSchema.setMainmissionid(tWF_SerialNo);
		tMissionSchema.setSubmissionid("1");
		tMissionSchema.setMakedate(tCurDate);
		tMissionSchema.setMaketime(tCurTime);
		tMissionSchema.setModifydate(tCurDate);
		tMissionSchema.setModifytime(tCurTime);
		tMissionSchema.setIndate(tCurDate);
		tMissionSchema.setIntime(tCurTime);
		
		if (checkerData()){
		    msg = trafficService.inRecord(tTrafficSchema, tMissionSchema);
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
