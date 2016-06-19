package com.art.ctrl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.schema.MissionSchema;
import com.art.service.TrafficService;

public class CtrlConfPrint implements Controller {
    
    private TrafficService trafficService;
    private String msg;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession tHttpSession = request.getSession();
		String tUserId = (String) tHttpSession.getAttribute("userId");
		String tPrtType =  request.getParameter("prttype");
		String tFile = "";
		if ("in".equals(tPrtType)) {
			//工作流
		    String tWF_SerialNo = request.getParameter("inconf_serialno");
			MissionSchema tMissionSchema = new MissionSchema();
			System.out.println(tWF_SerialNo);
			if (tWF_SerialNo == null || "".equals(tWF_SerialNo)) {
				return new ModelAndView("index");
			}
			tMissionSchema.setMissionid(tWF_SerialNo);
			tMissionSchema.setProcessid("0000000001");
			tMissionSchema.setLastoperator(tUserId);
			
			String tPath = request.getSession().getServletContext().getRealPath("/") + "pdf" + File.separator;
			msg = trafficService.prtInPdf(tMissionSchema, tPath);
			if (msg == null) {
			    msg = "保存成功！";
			} 
			tFile = tPath + "in" + File.separator + tMissionSchema.getMissionprop1() + ".pdf";
		} else {
		  //工作流
		    	String tWF_SerialNo = request.getParameter("outconf_serialno");
			MissionSchema tMissionSchema = new MissionSchema();
			System.out.println(tWF_SerialNo);
			if (tWF_SerialNo == null || "".equals(tWF_SerialNo)) {
				return new ModelAndView("index");
			}
			tMissionSchema.setMissionid(tWF_SerialNo);
			tMissionSchema.setProcessid("0000000002");
			tMissionSchema.setLastoperator(tUserId);
			
			String tPath = request.getSession().getServletContext().getRealPath("/") + "pdf" + File.separator;
			msg = trafficService.prtOutPdf(tMissionSchema, tPath);
			if (msg == null) {
			    msg = "保存成功！";
			} 
			tFile = tPath + "out" + File.separator + tMissionSchema.getMissionprop2() + ".pdf";
		}

		request.setAttribute("pdffile", tFile);
		return new ModelAndView("showpdf");
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
