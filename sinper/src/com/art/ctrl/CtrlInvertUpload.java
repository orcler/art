package com.art.ctrl;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.service.TrafficService;
import com.art.util.DataSource;

public class CtrlInvertUpload implements Controller {

    private TrafficService trafficService;
	private String userId;
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		HttpSession tHttpSession = request.getSession();
		userId = (String) tHttpSession.getAttribute("userId");
		String tSerialNo = request.getParameter("ivtupload_serialno");
		tSerialNo = getSerialNo(tSerialNo);
		String path = request.getSession().getServletContext().getRealPath("upload");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024);
		factory.setRepository(new File(path));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(-1);
		List<FileItem> items = upload.parseRequest(request);
		for (FileItem item : items) {
			if (!item.isFormField()) {
				String fileName = item.getName();
				String tSubfix = fileName.substring(fileName.lastIndexOf("."));
				int tPages = getPages(tSerialNo);
				String tFileName = "data/images/" + tSerialNo.substring(4) +"_" + getPages(tSerialNo) + tSubfix;
				String tPath = request.getSession().getServletContext()	.getRealPath("/") + tFileName; 
				File outFile = new File(tPath);
				System.out.println(tPath);
				item.write(outFile);
				boolean isSucc = save(tSerialNo, tPages, tFileName);
				String msg = null;
				if (isSucc) {
				    msg = "保存成功！";
				}
				request.setAttribute("msg", msg);
			}
		}
		return new ModelAndView("msg");
	}

	public int getPages(String aSerialNo) throws Exception {
		int tPages = 0;
		String tSql = " select ifnull(max(a.pages),0)+1 from Imgs a where a.serialno='" + aSerialNo + "' ";
		DataSource dataSource = new DataSource();
		Connection tConn =dataSource.openConn();
		Statement tStatement = tConn.createStatement();
		ResultSet tResult = tStatement.executeQuery(tSql);
		while (tResult.next()) {
			tPages = tResult.getInt(1);
		}
		dataSource.closeConn(tConn, tStatement, tResult);
		return tPages;
	}
	
	public boolean save(String aSerialNo, int aPages, String aPath) throws Exception {
		String tSql = " insert into Imgs (serialno,pages,iurl) VALUES ('" + aSerialNo + "', " + aPages + ",'" + aPath + "')  ";
		DataSource dataSource = new DataSource();
		Connection tConn =dataSource.openConn();
		Statement tStatement = tConn.createStatement();
		 tStatement.executeUpdate(tSql);
		dataSource.closeConn(tConn, tStatement, null);
		return true;
	}
	
	public String getSerialNo(String aSerialNo) throws Exception {
		String tSql = "select a.missionprop1 from MISSION a where a.activityid='3000000002' and  a.missionid= '" + aSerialNo + "' ";
		DataSource dataSource = new DataSource();
		Connection tConn =dataSource.openConn();
		Statement tStatement = tConn.createStatement();
		ResultSet tResult = tStatement.executeQuery(tSql);
		while (tResult.next()) {
			aSerialNo = tResult.getString(1);
		}
		dataSource.closeConn(tConn, tStatement, tResult);
		return aSerialNo;
	}
	
    public TrafficService getTrafficService() {
        return trafficService;
    }

    public void setTrafficService(TrafficService trafficService) {
        this.trafficService = trafficService;
    }
	
}
