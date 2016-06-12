package com.art.ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.util.DataSource;

public class CtrlDataQuery implements Controller {

    private String userId;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession tHttpSession = request.getSession();
	userId = (String) tHttpSession.getAttribute("userId");
	String tType = request.getParameter("qtype");
	String tData = "";
	if ("inRgster".equals(tType)) {
	    tData = inRgster_json();
	} else if ("inuw".equals(tType)) {
	    tData = inuw_json();
	}

	request.setAttribute("json", tData);
	return new ModelAndView("json");
    }

    public String inRgster_json() throws Exception {
	String tSql = " select a.missionid, b.EngineNo, b.VIN, b.model, b.cost, b.mileage, b.color, b.attn, b.phone, b.comcode , (SELECT x.codename FROM	icode x WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, "
		+ "b.phone, b.indate, a.createoperator,b.remark from MISSION a,TRAFFIC b  where a.activityid='1000000001' and a.missionprop1=b.SerialNo and a.createoperator='" + userId + "' ";
	System.out.println(tSql);
	DataSource tDataSource = new DataSource();
	Connection tConn = tDataSource.openConn();
	Statement tStatement = tConn.createStatement();
	ResultSet tResultSet = tStatement.executeQuery(tSql);
	int tRowNums = 0;
	String tContext = ", \"rows\" : [ ";
	while (tResultSet.next()) {
	    String tSerialNo = tResultSet.getString("missionid");
	    String tEngineNo = tResultSet.getString("EngineNo");
	    String tVIN = tResultSet.getString("VIN");
	    String tmodel = tResultSet.getString("model");
	    double tcost = tResultSet.getDouble("cost");
	    double tmileage = tResultSet.getDouble("mileage");
	    String tcolor = tResultSet.getString("color");
	    String tattn = tResultSet.getString("attn");
	    String tphone = tResultSet.getString("phone");
	    String tcomcode = tResultSet.getString("comname");
	    String tcomname = tResultSet.getString("comname");
	    String tindate = tResultSet.getString("indate");
	    String tcreateoperator = tResultSet.getString("createoperator");
	    String tremark = tResultSet.getString("remark");
	    tContext += "{ \"SerialNo\":\"" + tSerialNo + "\",";
	    tContext += "\"EngineNo\":\"" + tEngineNo + "\",";
	    tContext += "\"VIN\":\"" + tVIN + "\",";
	    tContext += "\"model\":\"" + tmodel + "\",";
	    tContext += "\"cost\":\"" + tcost + "\",";
	    tContext += "\"mileage\":\"" + tmileage + "\",";
	    tContext += "\"color\":\"" + tcolor + "\",";
	    tContext += "\"attn\":\"" + tattn + "\",";
	    tContext += "\"phone\":\"" + tphone + "\",";
	    tContext += "\"comcode\":\"" + tcomcode + "\",";
	    tContext += "\"comname\":\"" + tcomname + "\",";
	    tContext += "\"indate\":\"" + tindate + "\",";
	    tContext += "\"remark\":\"" + tremark + "\",";
	    tContext += "\"operator\":\"" + tcreateoperator + "\"},";
	    tRowNums++;
	}
	tContext = tContext.substring(0, tContext.length() - 1) + "]}";
	tDataSource.closeConn(tConn, tStatement, tResultSet);
	String tData = "{\"total\":" + tRowNums + tContext;
	return tData;
    }

    public String inuw_json() throws Exception {
	String tSql = " select a.missionid, b.EngineNo, b.VIN, b.model, b.cost, b.mileage, b.color, b.attn, (SELECT	x.codename FROM	icode x	WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, b.indate, a.createoperator, b.remark "
		+ " from MISSION a,TRAFFIC b  where a.activityid='1000000002' and a.missionprop1=b.SerialNo ";
	DataSource tDataSource = new DataSource();
	Connection tConn = tDataSource.openConn();
	Statement tStatement = tConn.createStatement();
	ResultSet tResultSet = tStatement.executeQuery(tSql);
	int tRowNums = 0;
	String tContext = ", \"rows\" : [ ";
	while (tResultSet.next()) {
	    String tSerialNo = tResultSet.getString("missionid");
	    String tEngineNo = tResultSet.getString("EngineNo");
	    String tVIN = tResultSet.getString("VIN");
	    String tmodel = tResultSet.getString("model");
	    double tcost = tResultSet.getDouble("cost");
	    double tmileage = tResultSet.getDouble("mileage");
	    String tcolor = tResultSet.getString("color");
	    String tattn = tResultSet.getString("attn");
	    String tcomname = tResultSet.getString("comname");
	    String tindate = tResultSet.getString("indate");
	    String tremark = tResultSet.getString("remark");
	    String tcreateoperator = tResultSet.getString("createoperator");
	    tContext += "{ \"SerialNo\":\"" + tSerialNo + "\",";
	    tContext += "\"EngineNo\":\"" + tEngineNo + "\",";
	    tContext += "\"VIN\":\"" + tVIN + "\",";
	    tContext += "\"model\":\"" + tmodel + "\",";
	    tContext += "\"cost\":\"" + tcost + "\",";
	    tContext += "\"mileage\":\"" + tmileage + "\",";
	    tContext += "\"color\":\"" + tcolor + "\",";
	    tContext += "\"attn\":\"" + tattn + "\",";
	    tContext += "\"comname\":\"" + tcomname + "\",";
	    tContext += "\"remark\":\"" + tremark + "\",";
	    tContext += "\"indate\":\"" + tindate + "\",";
	    tContext += "\"operator\":\"" + tcreateoperator + "\"},";
	    tRowNums++;
	}
	tContext = tContext.substring(0, tContext.length() - 1) + "]}";
	tDataSource.closeConn(tConn, tStatement, tResultSet);
	String tData = "{\"total\":" + tRowNums + tContext;
	return tData;
    }
}
