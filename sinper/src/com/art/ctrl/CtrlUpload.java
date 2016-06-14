package com.art.ctrl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CtrlUpload implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String path = request.getSession().getServletContext().getRealPath("upload");
	DiskFileItemFactory factory = new DiskFileItemFactory();
	factory.setSizeThreshold(1024 * 1024);
	factory.setRepository(new File(path));
	ServletFileUpload upload = new ServletFileUpload(factory);
	upload.setSizeMax(-1);
	List<FileItem> items = upload.parseRequest(request);
	for (FileItem item : items) {
	    if (!item.isFormField()) {
		String fieldName = item.getFieldName();
		System.out.println(fieldName);
		String fileName = item.getName();
		System.out.println(fileName);
		String tPath = request.getSession().getServletContext().getRealPath("/") + "data" + File.separator + fileName;
		File outFile = new File(tPath);
		 item.write(outFile);
		request.setAttribute("msg", fieldName);
	    }
	}
	return new ModelAndView("msg");
    }

}
