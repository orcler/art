package com.art.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.art.schema.CodeSchema;
import com.art.service.CodeService;

public class CtrlCodeQuery implements Controller {

    private CodeService codeService;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String tCodeType = request.getParameter("codetype");
	String tSql = " select * from icode where codetype='" + tCodeType + "'";
	 List tList = codeService.codeQuery(tSql);
	 String tData = "[";
	 for (int i = 0; i < tList.size(); i++) {
	     CodeSchema tSchema = (CodeSchema) tList.get(i);
	     System.out.println("------------------" + tSchema.getCode());
	      tData += "{\"id\":\"" + tSchema.getCode() + "\",\"text\":\"" + tSchema.getCodename() +"\"},";
	 }
	 tData =  tData.substring(0, tData.length() -1) +"]";
	 request.setAttribute("json", tData);
	return new ModelAndView("json");
    }
    
    public CodeService getCodeService() {
        return codeService;
    }

    public void setCodeService(CodeService codeService) {
        this.codeService = codeService;
    }

}
