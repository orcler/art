package com.art.ctrl;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.schema.MissionSchema;
import com.art.service.TrafficService;
import com.art.util.PubFun;

public class CtrlInvertApp implements Controller {
    
    private TrafficService trafficService;
    private String msg;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession tHttpSession = request.getSession();
		String tUserId = (String) tHttpSession.getAttribute("userId");
		String tType = request.getParameter("type");
		Date tCurDate = PubFun.getCurSqlDate();
		String tCurTime = PubFun.getCurTime();
		String  tSerialNo = request.getParameter("ivtapp_serialno");
		MissionSchema tMissionSchema = new MissionSchema();
		String tWF_SerialNo = null;
		if ("app".equals(tType)) {
			tWF_SerialNo = PubFun.getSerialNo("WF");
			tMissionSchema.setMissionid(tWF_SerialNo);
			tMissionSchema.setActivityid("3000000002");;
		} else if ("upload".equals(tType)) {
			tWF_SerialNo = request.getParameter("ivtupload_serialno");
			tMissionSchema.setMissionid(tWF_SerialNo);
			tMissionSchema.setActivityid("3000000003");
		} else if ("conf".equals(tType)){
			tWF_SerialNo = request.getParameter("ivtconf_serialno");
			tMissionSchema.setActivityid("3000000004");
		} else if ("show".equals(tType)) {
			return new ModelAndView("query/show");
		}
		
		//工作流
		tMissionSchema.setMissionid(tWF_SerialNo);
		tMissionSchema.setMissionprop1(tSerialNo);//入库流水号
		tMissionSchema.setSubmissionid("1");
		tMissionSchema.setActivitystatus("0");
		tMissionSchema.setProcessid("0000000003");
		tMissionSchema.setCreateoperator(tUserId);
		tMissionSchema.setLastoperator(tUserId);
		tMissionSchema.setMainmissionid(tWF_SerialNo);
		tMissionSchema.setMakedate(tCurDate);
		tMissionSchema.setMaketime(tCurTime);
		tMissionSchema.setModifydate(tCurDate);
		tMissionSchema.setModifytime(tCurTime);
		tMissionSchema.setIndate(tCurDate);
		tMissionSchema.setIntime(tCurTime);
		tMissionSchema.setModifydate(tCurDate);
		tMissionSchema.setModifytime(tCurTime);
		tMissionSchema.setLastoperator(tUserId);
		
		if ("app".equals(tType)) {
			tWF_SerialNo = PubFun.getSerialNo("WF");
			tMissionSchema.setMissionid(tWF_SerialNo);
			tMissionSchema.setActivityid("3000000002");
			msg = trafficService.invertApp(tMissionSchema);
		} else if ("upload".equals(tType)) {
			tWF_SerialNo = request.getParameter("ivtupload_serialno");
			tMissionSchema.setMissionid(tWF_SerialNo);
			tMissionSchema.setActivityid("3000000003");
			msg = trafficService.invertupload(tMissionSchema);
		} else {
			tWF_SerialNo = request.getParameter("ivtconf_serialno");
			tMissionSchema.setActivityid("3000000004");
			msg = trafficService.invertConf(tMissionSchema);
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
