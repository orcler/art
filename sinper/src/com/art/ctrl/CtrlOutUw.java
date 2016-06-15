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

public class CtrlOutUw implements Controller {
    
    private TrafficService trafficService;
    private String msg;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession tHttpSession = request.getSession();
		String tUserId = (String) tHttpSession.getAttribute("userId");
		Date tCurDate = PubFun.getCurSqlDate();
		String tCurTime = PubFun.getCurTime();
		
		String  tSerialNo = request.getParameter("outuw_serialno");
		String tUwFlag = request.getParameter("outuw_uwstate");
		String tRemark  = request.getParameter("outuw_remark");
 
		//入库信息
		TrafficSchema tTrafficSchema = new TrafficSchema();
		tTrafficSchema.setRemark(tRemark);
		tTrafficSchema.setUwflag(tUwFlag);//入库待审核

		//工作流
		MissionSchema tMissionSchema = new MissionSchema();
		tMissionSchema.setMissionid(tSerialNo);
		tMissionSchema.setModifydate(tCurDate);
		tMissionSchema.setModifytime(tCurTime);
		tMissionSchema.setLastoperator(tUserId);
		
		msg = trafficService.outuw(tTrafficSchema, tMissionSchema);
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
