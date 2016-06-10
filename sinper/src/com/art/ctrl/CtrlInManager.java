package com.art.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.schema.TrafficSchema;
import com.art.service.TrafficService;

public class CtrlInManager implements Controller {
    
    private TrafficService trafficService;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String tEngineNo = request.getParameter("EngineNo");
		String tVIN = request.getParameter("VIN");
		String tModel = request.getParameter("model");
		String tCost = request.getParameter("VIN");
		String tCert = request.getParameter("cert");
		String tMileage = request.getParameter("VIN");
		String tColor = request.getParameter("VIN");
		String tAttn = request.getParameter("VIN");
		String tPhone  = request.getParameter("VIN");
		String tComcode = request.getParameter("VIN");
		String tInDate = request.getParameter("VIN");
		String tIntime  = request.getParameter("VIN");
		String tOutdate = request.getParameter("VIN");
		String tOuttime = request.getParameter("VIN");
		String tGrpname = request.getParameter("VIN");
		String tRemark  = request.getParameter("VIN");

		System.out.println("----" + tEngineNo);
		if (tEngineNo == null || tEngineNo == null)
		{
			return new ModelAndView("login");
		}
		TrafficSchema tTrafficSchema = new TrafficSchema();
		tTrafficSchema.setEngineNo(tEngineNo);
		tTrafficSchema.setVIN(tVIN);
		boolean isExists = trafficService.save(tTrafficSchema);
		if (isExists)
			return new ModelAndView("index");
		else
			return new ModelAndView("login");
    }
    
    public TrafficService getTrafficService() {
        return trafficService;
    }

    public void setTrafficService(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

}
