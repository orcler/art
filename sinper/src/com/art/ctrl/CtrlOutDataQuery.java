package com.art.ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.util.DataSource;
import com.art.util.PubFun;

public class CtrlOutDataQuery implements Controller {

    private String userId;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession tHttpSession = request.getSession();
	userId = (String) tHttpSession.getAttribute("userId");
	String tType = request.getParameter("qtype");
	String tData = "";
	if ("out_df".equals(tType)) {
	    tData = outRegsterSearchJson(request);
	} else if ("out_uw".equals(tType)) {
	    tData = outUwjson(request);
	} else if ("inconf".equals(tType)) {
	    tData = inconf_json(request);
	}

	request.setAttribute("json", tData);
	return new ModelAndView("json");
    }

    public String outRegsterDfJson() throws Exception {
	String tSql = " select a.missionid, b.EngineNo, b.VIN, b.model, b.cost, b.cert, b.mileage, b.color, b.attn, b.phone, b.comcode , (SELECT x.codename FROM	icode x WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, "
		+ "b.phone, b.indate, a.createoperator,b.remark from MISSION a,TRAFFIC b  where a.activityid='1000000004' and a.missionprop1=b.SerialNo and b.state='1' and a.createoperator='" + userId + "' ";
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
	    String tcert = tResultSet.getString("cert");
	    double tmileage = tResultSet.getDouble("mileage");
	    String tcolor = tResultSet.getString("color");
	    String tattn = tResultSet.getString("attn");
	    String tphone = tResultSet.getString("phone");
	    String tcomcode = tResultSet.getString("comcode");
	    String tcomname = tResultSet.getString("comname");
	    String tindate = tResultSet.getString("indate");
	    String tcreateoperator = tResultSet.getString("createoperator");
	    String tremark = tResultSet.getString("remark");
	    tContext += "{ \"SerialNo\":\"" + tSerialNo + "\",";
	    tContext += "\"EngineNo\":\"" + tEngineNo + "\",";
	    tContext += "\"VIN\":\"" + tVIN + "\",";
	    tContext += "\"model\":\"" + tmodel + "\",";
	    tContext += "\"cost\":\"" + tcost + "\",";
	    tContext += "\"cert\":\"" + tcert + "\",";
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
    
    public String outRegsterSearchJson(HttpServletRequest request) throws Exception {
	String tComCode = request.getParameter("comcode");
	String tEnginNo = request.getParameter("enginno");
	String tStartDate = request.getParameter("startdate");
	String tEndDate = request.getParameter("enddate");
	String tWhereSql = " and '201' = " + userId;
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
	String tStrtPage  = request.getParameter("page");
    	String tStrRows = request.getParameter("rows");
    	int tPage = Integer.valueOf(tStrtPage);
    	int tRows = Integer.valueOf(tStrRows);
    	int tIdx = (tPage - 1) * tRows;
    	System.out.println(tRows + " : page : " + tPage);
    	String tLimit = " limit " + tIdx + ", " + tRows;
    	String tSql = " select count(1) from MISSION a,TRAFFIC b  where a.activityid='1000000004' and a.missionprop1=b.SerialNo " + tWhereSql;// get total
	DataSource tDataSource = new DataSource();
	Connection tConn = tDataSource.openConn();
	Statement tStatement = tConn.createStatement();
	ResultSet tResultSet = tStatement.executeQuery(tSql);
	int tRowNums = 0;
	while (tResultSet.next()) {
		tRowNums = tResultSet.getInt(1);
	}
	tResultSet.close();
	
	tSql = " select a.missionid,b.SerialNo as outserialno, b.EngineNo, b.VIN, b.model, b.cost, b.cert, b.mileage, b.color, b.attn, b.phone, b.comcode , (SELECT x.codename FROM	icode x WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, "
		+ "b.phone, b.indate, a.createoperator,b.remark from MISSION a,TRAFFIC b  where a.activityid='1000000004' and a.missionprop1=b.SerialNo  and b.state='1' " + tWhereSql + tLimit;
	System.out.println(tSql);
	tResultSet = tStatement.executeQuery(tSql);
	String tContext = ", \"rows\" : [ ";
	while (tResultSet.next()) {
	    String tSerialNo = tResultSet.getString("missionid");
	    String tOutSerialNo = tResultSet.getString("outserialno");
	    String tEngineNo = tResultSet.getString("EngineNo");
	    String tVIN = tResultSet.getString("VIN");
	    String tmodel = tResultSet.getString("model");
	    double tcost = tResultSet.getDouble("cost");
	    String tcert = tResultSet.getString("cert");
	    double tmileage = tResultSet.getDouble("mileage");
	    String tcolor = tResultSet.getString("color");
	    String tattn = tResultSet.getString("attn");
	    String tphone = tResultSet.getString("phone");
	    String tcomcode = tResultSet.getString("comcode");
	    String tcomname = tResultSet.getString("comname");
	    String tindate = tResultSet.getString("indate");
	    String tcreateoperator = tResultSet.getString("createoperator");
	    String tremark = tResultSet.getString("remark");
	    tContext += "{ \"SerialNo\":\"" + tSerialNo + "\",";
	    tContext += "\"outSerialNo\":\"" + tOutSerialNo + "\",";
	    tContext += "\"EngineNo\":\"" + tEngineNo + "\",";
	    tContext += "\"VIN\":\"" + tVIN + "\",";
	    tContext += "\"model\":\"" + tmodel + "\",";
	    tContext += "\"cost\":\"" + tcost + "\",";
	    tContext += "\"cert\":\"" + tcert + "\",";
	    tContext += "\"mileage\":\"" + tmileage + "\",";
	    tContext += "\"color\":\"" + tcolor + "\",";
	    tContext += "\"attn\":\"" + tattn + "\",";
	    tContext += "\"phone\":\"" + tphone + "\",";
	    tContext += "\"comcode\":\"" + tcomcode + "\",";
	    tContext += "\"comname\":\"" + tcomname + "\",";
	    tContext += "\"indate\":\"" + tindate + "\",";
	    tContext += "\"remark\":\"" + tremark + "\",";
	    tContext += "\"operator\":\"" + tcreateoperator + "\"},";
	}
	tContext = tContext.substring(0, tContext.length() - 1) + "]}";
	tDataSource.closeConn(tConn, tStatement, tResultSet);
	String tData = "{\"total\":" + tRowNums + tContext;
	return tData;
    }

    public String outUwjson(HttpServletRequest request) throws Exception {
    	String tStrtPage  = request.getParameter("page");
    	String tStrRows = request.getParameter("rows");
    	int tPage = Integer.valueOf(tStrtPage);
    	int tRows = Integer.valueOf(tStrRows);
    	int tIdx = (tPage - 1) * tRows;
    	System.out.println(tRows + " : page : " + tPage);
    	String tLimit = " limit " + tIdx + ", " + tRows;
	String tSql = " select count(1) from MISSION a,TRAFFIC b  where a.activityid='1000000002' and a.missionprop1=b.SerialNo ";// get total
	DataSource tDataSource = new DataSource();
	Connection tConn = tDataSource.openConn();
	Statement tStatement = tConn.createStatement();
	ResultSet tResultSet = tStatement.executeQuery(tSql);
	int tRowNums = 0;
	while (tResultSet.next()) {
		tRowNums = tResultSet.getInt(1);
	}
	tResultSet.close();
	
	tSql = " select a.missionid, b.EngineNo, b.VIN, b.model, b.cost, b.mileage, b.color, b.attn, (SELECT	x.codename FROM	icode x	WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, b.indate, a.createoperator, b.remark,"
		+ " c.EngineNo oEngineNo, c.VIN oVIN, c.model omodel, c.cost ocost, c.mileage omileage, c.color ocolor "
		+ " from MISSION a,TRAFFIC b, TRAFFIC c  where a.activityid='2000000002' and a.missionprop1=b.SerialNo and a.missionprop2 = c.SerialNo and '202'= " + userId + tLimit;
	
	System.out.println(tSql);

	tResultSet = tStatement.executeQuery(tSql);
	String tContext = ", \"rows\" : [ ";
	while (tResultSet.next()) {
	    String tSerialNo = tResultSet.getString("missionid");
	    String tEngineNo = tResultSet.getString("EngineNo");
	    String tOEngineNo = tResultSet.getString("oEngineNo");
	    String tVIN = tResultSet.getString("VIN");
	    String tOVIN = tResultSet.getString("oVIN");
	    String tmodel = tResultSet.getString("model");
	    String tOmodel = tResultSet.getString("omodel");
	    double tcost = tResultSet.getDouble("cost");
	    double tOcost = tResultSet.getDouble("ocost");
	    double tmileage = tResultSet.getDouble("mileage");
	    double tOmileage = tResultSet.getDouble("omileage");
	    String tcolor = tResultSet.getString("color");
	    String tOcolor = tResultSet.getString("ocolor");
	    String tattn = tResultSet.getString("attn");
	    String tcomname = tResultSet.getString("comname");
	    String tindate = tResultSet.getString("indate");
	    String tremark = tResultSet.getString("remark");
	    String tcreateoperator = tResultSet.getString("createoperator");
	    tContext += "{ \"SerialNo\":\"" + tSerialNo + "\",";
	    tContext += "\"EngineNo\":\"" + tEngineNo + "\",";
	    tContext += "\"oEngineNo\":\"" + tOEngineNo + "\",";
	    tContext += "\"VIN\":\"" + tVIN + "\",";
	    tContext += "\"oVIN\":\"" + tOVIN + "\",";
	    tContext += "\"model\":\"" + tmodel + "\",";
	    tContext += "\"omodel\":\"" + tOmodel + "\",";
	    tContext += "\"cost\":\"" + tcost + "\",";
	    tContext += "\"ocost\":\"" + tOcost + "\",";
	    tContext += "\"mileage\":\"" + tmileage + "\",";
	    tContext += "\"omileage\":\"" + tOmileage + "\",";
	    tContext += "\"color\":\"" + tcolor + "\",";
	    tContext += "\"ocolor\":\"" + tOcolor + "\",";
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
    
    public String inconf_json(HttpServletRequest request) throws Exception {
	String tComCode = request.getParameter("comcode");
	String tEnginNo = request.getParameter("enginno");
	String tStartDate = request.getParameter("startdate");
	String tEndDate = request.getParameter("enddate");
	String tRows = request.getParameter("rows");
	String tPage  = request.getParameter("page");
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
	
	String tSql = " select a.missionid, b.EngineNo, b.VIN, b.model, b.cost, b.mileage, b.color, b.attn, (SELECT	x.codename FROM	icode x	WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, b.indate, a.createoperator, b.remark "
		+ " from MISSION a,TRAFFIC b  where a.activityid='1000000003' and a.missionprop1=b.SerialNo " + tWhereSql;
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
