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
import com.art.util.PubFun;

public class CtrlInvertQuery implements Controller {

    private String userId;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession tHttpSession = request.getSession();
	userId = (String) tHttpSession.getAttribute("userId");
	String tType = request.getParameter("qtype");
	String tData = "";
	if ("app".equals(tType)) {
	    tData = appJson(request);
	} else if ("upload".equals(tType)) {
	    tData = uploadJson(request);
	} else if ("conf".equals(tType)) {
	    tData = confJson(request);
	} 

	request.setAttribute("json", tData);
	return new ModelAndView("json");
    }


    public String uploadJson(HttpServletRequest request) throws Exception {
    	String tStrtPage  = request.getParameter("page");
    	String tStrRows = request.getParameter("rows");
    	int tPage = Integer.valueOf(tStrtPage);
    	int tRows = Integer.valueOf(tStrRows);
    	int tIdx = (tPage - 1) * tRows;
    	System.out.println(tRows + " : page : " + tPage);
    	String tLimit = " limit " + tIdx + ", " + tRows;
	String tSql = " select count(1) from MISSION a,TRAFFIC b  where a.activityid='3000000002' and a.missionprop1=b.SerialNo ";// get total
	DataSource tDataSource = new DataSource();
	Connection tConn = tDataSource.openConn();
	Statement tStatement = tConn.createStatement();
	ResultSet tResultSet = tStatement.executeQuery(tSql);
	int tRowNums = 0;
	while (tResultSet.next()) {
		tRowNums = tResultSet.getInt(1);
	}
	tResultSet.close();
	
	tSql = " select a.missionid, b.EngineNo, b.VIN, b.model, b.cost, b.mileage, b.color, b.attn, (SELECT	x.codename FROM	icode x	WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, b.indate, a.createoperator, b.remark "
			+ " from MISSION a,TRAFFIC b  where a.activityid='3000000002' and a.missionprop1=b.SerialNo " + tLimit;
	
	System.out.println(tSql);

	tResultSet = tStatement.executeQuery(tSql);
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
	}
	tContext = tContext.substring(0, tContext.length() - 1) + "]}";
	tDataSource.closeConn(tConn, tStatement, tResultSet);
	String tData = "{\"total\":" + tRowNums + tContext;
	return tData;
    }
 
    public String confJson(HttpServletRequest request) throws Exception {
    	String tStrtPage  = request.getParameter("page");
    	String tStrRows = request.getParameter("rows");
    	int tPage = Integer.valueOf(tStrtPage);
    	int tRows = Integer.valueOf(tStrRows);
    	int tIdx = (tPage - 1) * tRows;
    	System.out.println(tRows + " : page : " + tPage);
    	String tLimit = " limit " + tIdx + ", " + tRows;
	String tSql = " select count(1) from MISSION a,TRAFFIC b  where a.activityid='3000000003' and a.missionprop1=b.SerialNo ";// get total
	DataSource tDataSource = new DataSource();
	Connection tConn = tDataSource.openConn();
	Statement tStatement = tConn.createStatement();
	ResultSet tResultSet = tStatement.executeQuery(tSql);
	int tRowNums = 0;
	while (tResultSet.next()) {
		tRowNums = tResultSet.getInt(1);
	}
	tResultSet.close();
	
	tSql = " select a.missionid, b.EngineNo, b.VIN, b.model, b.cost, b.mileage, b.color, b.attn, (SELECT	x.codename FROM	icode x	WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, b.indate, a.createoperator, b.remark "
			+ " from MISSION a,TRAFFIC b  where a.activityid='3000000003' and a.missionprop1=b.SerialNo " + tLimit;
	
	System.out.println(tSql);

	tResultSet = tStatement.executeQuery(tSql);
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
	}
	tContext = tContext.substring(0, tContext.length() - 1) + "]}";
	tDataSource.closeConn(tConn, tStatement, tResultSet);
	String tData = "{\"total\":" + tRowNums + tContext;
	return tData;
    }
     
    public String appJson(HttpServletRequest request) throws Exception {
	String tComCode = request.getParameter("comcode");
	String tEnginNo = request.getParameter("enginno");
	String tStartDate = request.getParameter("startdate");
	String tEndDate = request.getParameter("enddate");
	String tStrtPage  = request.getParameter("page");
	String tStrRows = request.getParameter("rows");
	int tPage = Integer.valueOf(tStrtPage);
	int tRows = Integer.valueOf(tStrRows);
	int tIdx = (tPage - 1) * tRows;
	System.out.println(tRows + " : page : " + tPage);
	String tLimit = " limit " + tIdx + ", " + tRows;
	System.out.println(tRows + " : page : " + tPage);
	String tWhereSql = " ";
	if (tComCode != null && !"".equals(tComCode)) {
	    tWhereSql += " and b.comcode = '" + tComCode + "' ";
	}
	if (tEnginNo != null && !"".equals(tEnginNo)) {
	    tWhereSql += " and b.EngineNo like '%" + tEnginNo + "%' ";
	}
	if (tStartDate != null && !"".equals(tStartDate)) {
	    tStartDate = PubFun.getDate(tStartDate, "MM/dd/yyyy");
	    tWhereSql += " and b.indate >= date('" + tStartDate + "') ";
	}
	if (tEndDate != null && !"".equals(tEndDate)) {
	    tEndDate = PubFun.getDate(tEndDate, "MM/dd/yyyy");
	    tWhereSql += " and b.indate <= date('" + tEndDate + "') ";
	}
	
	String tSql = " select count(1) from TRAFFIC b where b.state='1' and  b.uwflag='0' and not exists (select 1 from MISSION x where x.processid='0000000003' and x.missionprop1=b.serialno )  " + tWhereSql;
	DataSource tDataSource = new DataSource();
	Connection tConn = tDataSource.openConn();
	Statement tStatement = tConn.createStatement();
	ResultSet tResultSet = tStatement.executeQuery(tSql);
	int tRowNums = 0;
	while (tResultSet.next()) {
		tRowNums = tResultSet.getInt(1);
	}
	tResultSet.close();
	
	 tSql = " select b.serialno, b.EngineNo, b.VIN, b.model, b.cost, b.mileage, b.color, b.attn, (SELECT	x.codename FROM	icode x	WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, b.indate, b.remark "
		+ " from  TRAFFIC b  where b.state='1'  and  b.uwflag='0' and not exists (select 1 from MISSION x where x.processid='0000000003' and x.missionprop1=b.serialno ) " + tWhereSql + tLimit ;
	System.out.println(tSql);
	tDataSource = new DataSource();
	tConn = tDataSource.openConn();
	tStatement = tConn.createStatement();
	tResultSet = tStatement.executeQuery(tSql);
	String tContext = ", \"rows\" : [ ";
	while (tResultSet.next()) {
	    String tSerialNo = tResultSet.getString("serialno");
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
	    tContext += "\"indate\":\"" + tindate + "\"},";
	}
	tContext = tContext.substring(0, tContext.length() - 1) + "]}";
	tDataSource.closeConn(tConn, tStatement, tResultSet);
	String tData = "{\"total\":" + tRowNums + tContext;
	return tData;
    }
    
    
}
