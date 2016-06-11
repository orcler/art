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

public class CtrlInUw implements Controller {
    
    private TrafficService trafficService;
    private String msg;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession tHttpSession = request.getSession();
		String tUserId = (String) tHttpSession.getAttribute("userId");
		Date tCurDate = PubFun.getCurSqlDate();
		String tCurTime = PubFun.getCurTime();
		
		String  tSerialNo = request.getParameter("inuw_serialno");
		String tUwFlag = request.getParameter("inuw_uwstate");
		
		Date tInDate =tCurDate;
		String tIntime  = tCurTime;
		Date tOutdate = tCurDate;
		String tOuttime = tCurTime;
		String tRemark  = request.getParameter("inuw_remark");
 
		
		//入库信息
		TrafficSchema tTrafficSchema = new TrafficSchema();
		tTrafficSchema.setIndate(tInDate);
		tTrafficSchema.setIntime(tIntime);
		tTrafficSchema.setRemark(tRemark);
		tTrafficSchema.setState("1"); //入库
		tTrafficSchema.setUwflag(tUwFlag);//入库待审核
		String tActivityId = "1000000003";//默认审核通
		if ("1".equals(tUwFlag)) {
			tActivityId = "1000000003";
		} else if ("2".equals(tUwFlag)) {//回退
			tActivityId = "1000000001";
		} else {
			tActivityId = "1000000000";
		}
		
		
		
		//工作流
		MissionSchema tMissionSchema = new MissionSchema();
		String tWF_SerialNo = PubFun.getSerialNo("WF");
		tMissionSchema.setMissionid(tWF_SerialNo);
		tMissionSchema.setMissionprop1(tSerialNo);//入库流水号
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
		
		msg = trafficService.inRecord(tTrafficSchema, tMissionSchema);
		if (msg == null) {
		    msg = "保存成功！";
		}
		request.setAttribute("msg", msg);
		return new ModelAndView("msg");
    }
    
    public TrafficService getTrafficService() {
        return trafficService;
    }

    public void setTrafficService(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

}
