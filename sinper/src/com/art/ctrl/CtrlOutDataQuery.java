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

public class CtrlOutDataQuery implements Controller {

	private String userId;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession tHttpSession = request.getSession();
		userId = (String) tHttpSession.getAttribute("userId");
		String tType = request.getParameter("qtype");
		String tData = "";
		if ("out_df".equals(tType)) {
			tData = outRegsterDefaultJson(request);
		} else if ("out_rs".equals(tType)) {
			tData = outRegsterSearchJson(request);
		} else if ("out_uw".equals(tType)) {
			tData = outUwjson(request);
		} else if ("outconf".equals(tType)) {
			tData = outconf_json(request);
		}

		request.setAttribute("json", tData);
		return new ModelAndView("json");
	}

	public String outRegsterSearchJson(HttpServletRequest request) throws Exception {
		String tStrtPage = request.getParameter("page");
		String tStrRows = request.getParameter("rows");
		int tPage = Integer.valueOf(tStrtPage);
		int tRows = Integer.valueOf(tStrRows);
		int tIdx = (tPage - 1) * tRows;
		System.out.println(tRows + " : page : " + tPage);
		String tComCode = request.getParameter("comcode");
		String tEnginNo = request.getParameter("enginno");
		String tStartDate = request.getParameter("startdate");
		String tEndDate = request.getParameter("enddate");
		String tWhereSql = " and  '101' =  " + userId;
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
		
		String tLimit = " limit " + tIdx + ", " + tRows;
		String tSql = " select count(1) from TRAFFIC a where a.state='1' and a.uwflag='0' ";// get
																																	// total
		DataSource tDataSource = new DataSource();
		Connection tConn = tDataSource.openConn();
		Statement tStatement = tConn.createStatement();
		ResultSet tResultSet = tStatement.executeQuery(tSql);
		int tRowNums = 0;
		while (tResultSet.next()) {
			tRowNums = tResultSet.getInt(1);
		}
		tResultSet.close();

		tSql = " select b.SerialNo AS outserialno,'1000000004' AS activityid,b.paymode AS pay,b.EngineNo AS oEngineNo,b.VIN AS oVIN, "
				+ " b.model AS oModel, b.cost AS oCost, b.mileage AS oMileage, b.color AS oColor, b.attn AS oAttn, b.comcode AS oComcode, "
				+ " (select x.codename FROM icode x	where x.codetype = 'comcode' and x.`code` = b.comcode) AS oComname,b.indate AS outdate,"
				+ "'' AS iOperator FROM TRAFFIC b WHERE b.uwflag='0' and b.state='1' " + tWhereSql + tLimit;

		System.out.println(tSql);

		tResultSet = tStatement.executeQuery(tSql);
		String tContext = ", \"rows\" : [ ";
		while (tResultSet.next()) {
			String tSerialNo = "";
			String tOutSerialno = tResultSet.getString("outserialno");
			String tPay = tResultSet.getString("pay");
			String tActivityId = tResultSet.getString("activityid");

			String tOEngineNo = tResultSet.getString("oEngineNo");
			String tIEngineNo = "";

			String tOVIN = tResultSet.getString("oVIN");
			String tIVIN = "";

			String tOModel = tResultSet.getString("oModel");
			String tIModel = "";

			double tOCost = tResultSet.getDouble("oCost");
			double tICost = 0.00;

			double tOMileage = tResultSet.getDouble("oMileage");
			double tIMileage = 0.00;

			String tOColor = tResultSet.getString("oColor");
			String tIColor = "";

			String tOAttn = tResultSet.getString("oAttn");
			String tIAttn = "";

			String tOComcode = tResultSet.getString("oComcode");
			String tOComname = tResultSet.getString("oComname");
			String tOutDate = tResultSet.getString("outdate");
			String tIRemark = "";
			String tIOperator = tResultSet.getString("iOperator");
			tIRemark = tIRemark == null ? "" : tIRemark;
			tContext += "{ \"SerialNo\":\"" + tSerialNo + "\",";
			tContext += "\"oSerialNo\":\"" + tOutSerialno + "\",";
			tContext += "\"activityId\":\"" + tActivityId + "\",";
			tContext += "\"oEngineNo\":\"" + tOEngineNo + "\",";
			tContext += "\"iEngineNo\":\"" + tIEngineNo + "\",";
			tContext += "\"oVIN\":\"" + tOVIN + "\",";
			tContext += "\"iVIN\":\"" + tIVIN + "\",";
			tContext += "\"oModel\":\"" + tOModel + "\",";
			tContext += "\"iModel\":\"" + tIModel + "\",";
			tContext += "\"oCost\":\"" + tOCost + "\",";
			tContext += "\"iCost\":\"" + tICost + "\",";
			tContext += "\"oMileage\":\"" + tOMileage + "\",";
			tContext += "\"iMileage\":\"" + tIMileage + "\",";
			tContext += "\"oColor\":\"" + tOColor + "\",";
			tContext += "\"iColor\":\"" + tIColor + "\",";
			tContext += "\"oAttn\":\"" + tOAttn + "\",";
			tContext += "\"iAttn\":\"" + tIAttn + "\",";
			tContext += "\"comcode\":\"" + tOComcode + "\",";
			tContext += "\"comname\":\"" + tOComname + "\",";
			tContext += "\"remark\":\"" + tIRemark + "\",";
			tContext += "\"opdate\":\"" + tOutDate + "\",";
			tContext += "\"pay\":\"" + tPay + "\",";
			tContext += "\"operator\":\"" + tIOperator + "\"},";
		}
		tContext = tContext.substring(0, tContext.length() - 1) + "]}";
		tDataSource.closeConn(tConn, tStatement, tResultSet);
		String tData = "{\"total\":" + tRowNums + tContext;
		return tData;
	}

	public String outRegsterDefaultJson(HttpServletRequest request) throws Exception {
		String tStrtPage = request.getParameter("page");
		String tStrRows = request.getParameter("rows");
		int tPage = Integer.valueOf(tStrtPage);
		int tRows = Integer.valueOf(tStrRows);
		int tIdx = (tPage - 1) * tRows;
		System.out.println(tRows + " : page : " + tPage);
		String tLimit = " limit " + tIdx + ", " + tRows;
		String tSql = " select count(1) from MISSION a,TRAFFIC b  where a.activityid='2000000002' and a.missionprop1=b.SerialNo ";// get
																																	// total
		DataSource tDataSource = new DataSource();
		Connection tConn = tDataSource.openConn();
		Statement tStatement = tConn.createStatement();
		ResultSet tResultSet = tStatement.executeQuery(tSql);
		int tRowNums = 0;
		while (tResultSet.next()) {
			tRowNums = tResultSet.getInt(1);
		}
		tResultSet.close();

		tSql = "select t.*,c.EngineNo as iEngineNo,c.VIN as iVIN, c.model as iModel, c.cost as iCost, c.mileage as iMileage, c.color as iColor, c.attn as iAttn, c.cert as iCert, c.phone as iPhone from "
				+ " (select a.missionid,a.missionprop1 as inserialno,a.missionprop2 as outserialno, a.activityid as activityid, b.paymode as pay,b.EngineNo as oEngineNo, b.VIN as oVIN, b.model as oModel,b.cost as oCost, b.mileage as oMileage,b.color as oColor,b.attn as oAttn, "
				+ "  b.comcode as oComcode, (SELECT x.codename FROM icode x WHERE x.codetype = 'comcode' AND x.`code` = b.comcode) AS oComname, b.outdate as outdate, b.remark as oRemark, a.createoperator as iOperator "
				+ "  from MISSION a, TRAFFIC b where a.missionprop2=b.SerialNo and a.activityid = '2000000001' and '101'= "
				+ userId + ") t LEFT JOIN TRAFFIC c on t.inserialno=c.serialno " + tLimit;

		System.out.println(tSql);

		tResultSet = tStatement.executeQuery(tSql);
		String tContext = ", \"rows\" : [ ";
		while (tResultSet.next()) {
			String tSerialNo = tResultSet.getString("missionid");
			String tOutSerialno = tResultSet.getString("outserialno");
			String tPay = tResultSet.getString("pay");
			String tActivityId = tResultSet.getString("activityid");

			String tOEngineNo = tResultSet.getString("oEngineNo");
			String tIEngineNo = tResultSet.getString("iEngineNo");

			String tOVIN = tResultSet.getString("oVIN");
			String tIVIN = tResultSet.getString("iVIN");

			String tOModel = tResultSet.getString("oModel");
			String tIModel = tResultSet.getString("iModel");

			double tOCost = tResultSet.getDouble("oCost");
			double tICost = tResultSet.getDouble("iCost");

			double tOMileage = tResultSet.getDouble("oMileage");
			double tIMileage = tResultSet.getDouble("iMileage");

			String tOColor = tResultSet.getString("oColor");
			String tIColor = tResultSet.getString("iColor");

			String tOAttn = tResultSet.getString("oAttn");
			String tIAttn = tResultSet.getString("iAttn");

			String tOComcode = tResultSet.getString("oComcode");
			String tOComname = tResultSet.getString("oComname");
			
			String tICert = tResultSet.getString("iCert");
			String tIPhone = tResultSet.getString("iPhone");
			String tOutDate = tResultSet.getString("outdate");
			String tORemark = tResultSet.getString("oRemark");
			String tIOperator = tResultSet.getString("iOperator");
			tORemark = tORemark == null ? "" : tORemark;
			tContext += "{ \"SerialNo\":\"" + tSerialNo + "\",";
			tContext += "\"oSerialNo\":\"" + tOutSerialno + "\",";
			tContext += "\"activityId\":\"" + tActivityId + "\",";
			tContext += "\"oEngineNo\":\"" + tOEngineNo + "\",";
			tContext += "\"iEngineNo\":\"" + tIEngineNo + "\",";
			tContext += "\"oVIN\":\"" + tOVIN + "\",";
			tContext += "\"iVIN\":\"" + tIVIN + "\",";
			tContext += "\"oModel\":\"" + tOModel + "\",";
			tContext += "\"iModel\":\"" + tIModel + "\",";
			tContext += "\"oCost\":\"" + tOCost + "\",";
			tContext += "\"iCost\":\"" + tICost + "\",";
			tContext += "\"oMileage\":\"" + tOMileage + "\",";
			tContext += "\"iMileage\":\"" + tIMileage + "\",";
			tContext += "\"oColor\":\"" + tOColor + "\",";
			tContext += "\"iColor\":\"" + tIColor + "\",";
			tContext += "\"oAttn\":\"" + tOAttn + "\",";
			tContext += "\"iAttn\":\"" + tIAttn + "\",";
			tContext += "\"comcode\":\"" + tOComcode + "\",";
			tContext += "\"comname\":\"" + tOComname + "\",";
			tContext += "\"remark\":\"" + tORemark + "\",";
			tContext += "\"opdate\":\"" + tOutDate + "\",";
			tContext += "\"iCert\":\"" + tICert + "\",";
			tContext += "\"pay\":\"" + tPay + "\",";
			tContext += "\"iPhone\":\"" +tIPhone + "\",";
			tContext += "\"operator\":\"" + tIOperator + "\"},";
		}
		tContext = tContext.substring(0, tContext.length() - 1) + "]}";
		tDataSource.closeConn(tConn, tStatement, tResultSet);
		String tData = "{\"total\":" + tRowNums + tContext;
		return tData;
	}

	public String outUwjson(HttpServletRequest request) throws Exception {
		String tStrtPage = request.getParameter("page");
		String tStrRows = request.getParameter("rows");
		int tPage = Integer.valueOf(tStrtPage);
		int tRows = Integer.valueOf(tStrRows);
		int tIdx = (tPage - 1) * tRows;
		System.out.println(tRows + " : page : " + tPage);
		String tLimit = " limit " + tIdx + ", " + tRows;
		String tSql = " select count(1) from MISSION a,TRAFFIC b  where a.activityid='2000000002' and a.missionprop1=b.SerialNo ";// get
																																	// total
		DataSource tDataSource = new DataSource();
		Connection tConn = tDataSource.openConn();
		Statement tStatement = tConn.createStatement();
		ResultSet tResultSet = tStatement.executeQuery(tSql);
		int tRowNums = 0;
		while (tResultSet.next()) {
			tRowNums = tResultSet.getInt(1);
		}
		tResultSet.close();

		tSql = "select t.*,c.EngineNo as iEngineNo,c.VIN as iVIN, c.model as iModel, c.cost as iCost, c.mileage as iMileage, c.color as iColor, c.attn as iAttn from "
				+ " (select a.missionid,a.missionprop1 as inserialno,b.paymode as pay,b.EngineNo as oEngineNo, b.VIN as oVIN, b.model as oModel,b.cost as oCost, b.mileage as oMileage,b.color as oColor,b.attn as oAttn, "
				+ "  b.comcode as oComcode, (SELECT x.codename FROM icode x WHERE x.codetype = 'comcode' AND x.`code` = b.comcode) AS oComname, b.outdate as outdate, a.createoperator as iOperator,b.remark as oRemark"
				+ "  from MISSION a, TRAFFIC b where a.missionprop2=b.SerialNo and a.activityid = '2000000002' and '102'= "
				+ userId + ") t LEFT JOIN TRAFFIC c on t.inserialno=c.serialno " + tLimit;

		System.out.println(tSql);

		tResultSet = tStatement.executeQuery(tSql);
		String tContext = ", \"rows\" : [ ";
		while (tResultSet.next()) {
			String tSerialNo = tResultSet.getString("missionid");
			String tPay = tResultSet.getString("pay");

			String tOEngineNo = tResultSet.getString("oEngineNo");
			String tIEngineNo = tResultSet.getString("iEngineNo");

			String tOVIN = tResultSet.getString("oVIN");
			String tIVIN = tResultSet.getString("iVIN");

			String tOModel = tResultSet.getString("oModel");
			String tIModel = tResultSet.getString("iModel");

			double tOCost = tResultSet.getDouble("oCost");
			double tICost = tResultSet.getDouble("iCost");

			double tOMileage = tResultSet.getDouble("oMileage");
			double tIMileage = tResultSet.getDouble("iMileage");

			String tOColor = tResultSet.getString("oColor");
			String tIColor = tResultSet.getString("iColor");

			String tOAttn = tResultSet.getString("oAttn");
			String tIAttn = tResultSet.getString("iAttn");

			String tOComcode = tResultSet.getString("oComcode");
			String tOComname = tResultSet.getString("oComname");
			String tOutDate = tResultSet.getString("outdate");
			String tORemark = tResultSet.getString("oRemark");
			String tIOperator = tResultSet.getString("iOperator");

			tORemark = tORemark == null ? "" : tORemark;

			tContext += "{ \"SerialNo\":\"" + tSerialNo + "\",";
			tContext += "\"oEngineNo\":\"" + tOEngineNo + "\",";
			tContext += "\"iEngineNo\":\"" + tIEngineNo + "\",";
			tContext += "\"oVIN\":\"" + tOVIN + "\",";
			tContext += "\"iVIN\":\"" + tIVIN + "\",";
			tContext += "\"oModel\":\"" + tOModel + "\",";
			tContext += "\"iModel\":\"" + tIModel + "\",";
			tContext += "\"oCost\":\"" + tOCost + "\",";
			tContext += "\"iCost\":\"" + tICost + "\",";
			tContext += "\"oMileage\":\"" + tOMileage + "\",";
			tContext += "\"iMileage\":\"" + tIMileage + "\",";
			tContext += "\"oColor\":\"" + tOColor + "\",";
			tContext += "\"iColor\":\"" + tIColor + "\",";
			tContext += "\"oAttn\":\"" + tOAttn + "\",";
			tContext += "\"iAttn\":\"" + tIAttn + "\",";
			tContext += "\"comcode\":\"" + tOComcode + "\",";
			tContext += "\"comname\":\"" + tOComname + "\",";
			tContext += "\"remark\":\"" + tORemark + "\",";
			tContext += "\"opdate\":\"" + tOutDate + "\",";
			tContext += "\"pay\":\"" + tPay + "\",";
			tContext += "\"operator\":\"" + tIOperator + "\"},";
		}
		tContext = tContext.substring(0, tContext.length() - 1) + "]}";
		tDataSource.closeConn(tConn, tStatement, tResultSet);
		String tData = "{\"total\":" + tRowNums + tContext;
		return tData;
	}

	public String outconf_json(HttpServletRequest request) throws Exception {
		String tComCode = request.getParameter("comcode");
		String tEnginNo = request.getParameter("enginno");
		String tStartDate = request.getParameter("startdate");
		String tEndDate = request.getParameter("enddate");
		String tRows = request.getParameter("rows");
		String tPage = request.getParameter("page");
		System.out.println(tRows + " : page : " + tPage);
		String tWhereSql = " and  '103' =  " + userId;
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

		String tSql = " select a.missionid, b.EngineNo,b.paymode, b.VIN, b.model, b.cost, b.mileage, b.color, b.attn, (SELECT	x.codename FROM	icode x	WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, b.indate, a.createoperator, b.remark "
				+ " from MISSION a,TRAFFIC b  where a.activityid='2000000003' and a.missionprop2=b.SerialNo "
				+ tWhereSql;
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
			String tPaymode = tResultSet.getString("paymode");
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
			tContext += "\"paymode\":\"" + tPaymode + "\",";
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
