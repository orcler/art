package com.art.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.service.UserService;

public class CtrlLogin implements Controller {
    
    private UserService userService;
    
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String tUserId = request.getParameter("userId");
	String tPwd = request.getParameter("pwd");
	if (tUserId == null || tPwd == null)
	{
	    return new ModelAndView("login"); 
	}
	boolean isExists = userService.login(tUserId, tPwd);
	HttpSession tHttpSession = request.getSession();
	tHttpSession.setAttribute("userId", tUserId);
	if (isExists)
		return new ModelAndView("index");
	else
	    return new ModelAndView("login");
    }

    /**
     * 此后方法为注入准备，无意义
     */
    
    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
