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

public class CtrlOutConfDeal implements Controller {
    
    private TrafficService trafficService;
    private String msg;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession tHttpSession = request.getSession();
		String tUserId = (String) tHttpSession.getAttribute("userId");
		Date tCurDate = PubFun.getCurSqlDate();
		String tCurTime = PubFun.getCurTime();
		
		String tWF_SerialNo = request.getParameter("outconf_serialno");
		String tConfType = request.getParameter("conftype");
		System.out.println(tWF_SerialNo);
		if (tWF_SerialNo == null || "".equals(tWF_SerialNo)) {
			return new ModelAndView("index");
		}
		//入库信息
		TrafficSchema tTrafficSchema = new TrafficSchema();

		//工作流
		MissionSchema tMissionSchema = new MissionSchema();
		tMissionSchema.setMissionid(tWF_SerialNo);
		tMissionSchema.setModifydate(tCurDate);
		tMissionSchema.setModifytime(tCurTime);
		tMissionSchema.setLastoperator(tUserId);
		
		if ("in".equals(tConfType)) {
		    msg = trafficService.outInConf(tTrafficSchema, tMissionSchema);
		} else {
		    msg = trafficService.outOutConf(tTrafficSchema, tMissionSchema);
		}
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
