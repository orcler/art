package com.art.ctrl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.art.util.DataSource;

public class CtrlDataQuery implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String tType = request.getParameter("qtype");
		String tSql = " ";
		if ("inuw".equals(tType)) {
			tSql = " select b.SerialNo, b.EngineNo, b.VIN, b.model, b.cost, b.mileage, b.color, b.attn, (SELECT	x.codename FROM	icode x	WHERE	x.codetype = 'comcode' AND x.`code` = b.comcode) as comname, b.indate, a.createoperator"
				  + " from MISSION a,TRAFFIC b  where a.activityid='1000000002' and a.missionprop1=b.SerialNo ";
		}
		DataSource tDataSource = new DataSource();
		Connection tConn = tDataSource.openConn();
		Statement tStatement = tConn.createStatement();
		ResultSet tResultSet = tStatement.executeQuery(tSql);
		int tRowNums = 0;
		String tContext = ", \"rows\" : [ ";
		while (tResultSet.next()) {
			String tSerialNo         = tResultSet.getString("SerialNo");
			String tEngineNo         = tResultSet.getString("EngineNo");
			String tVIN              = tResultSet.getString("VIN");
			String tmodel            = tResultSet.getString("model");
			double tcost             = tResultSet.getDouble("cost");
			double tmileage          = tResultSet.getDouble("mileage");
			String tcolor            = tResultSet.getString("color");
			String tattn             = tResultSet.getString("attn");
			String tcomname          = tResultSet.getString("comname");
			String tindate           = tResultSet.getString("indate");
			String tcreateoperator   = tResultSet.getString("createoperator");
			tContext += "{ \"SerialNo\":\"" + tSerialNo + "\",";
			tContext += "\"EngineNo\":\"" + tEngineNo + "\",";
			tContext += "\"VIN\":\"" + tVIN + "\",";
			tContext += "\"model\":\"" + tmodel + "\",";
			tContext += "\"cost\":\"" + tcost + "\",";
			tContext += "\"mileage\":\"" + tmileage + "\",";
			tContext += "\"color\":\"" + tcolor + "\",";
			tContext += "\"attn\":\"" + tattn + "\",";
			tContext += "\"comname\":\"" + tcomname + "\",";
			tContext += "\"indate\":\"" + tindate + "\",";
			tContext += "\"operator\":\"" + tcreateoperator + "\"},";
			tRowNums++;
		}
		tContext = tContext.substring(0, tContext.length()-1)  +"]}";
		String tData = "{\"total\":" + tRowNums + tContext;
		tDataSource.closeConn(tConn, tStatement, tResultSet);
		request.setAttribute("json", tData);
		return new ModelAndView("json");
	}

}
