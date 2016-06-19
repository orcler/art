<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.art.util.DataSource"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String tSerialNo = request.getParameter("ivtconf_serialno");
	String tSql = " SELECT b.iurl from MISSION a, Imgs b where a.activityid='3000000003' and a.missionprop1=b.serialno and a.missionid='" + tSerialNo + "' ";
	DataSource dataSource = new DataSource();
	Connection tConn =dataSource.openConn();
	Statement tStatement = tConn.createStatement();
	ResultSet tResult = tStatement.executeQuery(tSql);
	List<String> tList = new ArrayList();
	while (tResult.next()) {
//		String tSrc = "../" +tResult.getString(1);
		String tSrc = ".." + request.getContextPath() + File.separator+tResult.getString(1);
		tSrc = tSrc.replace("\\", "/");
		tList.add(tSrc);
		System.out.println(tSrc);
	}
	dataSource.closeConn(tConn, tStatement, tResult);
%>
<table border="1" width="200px" height="200px">
  <tr>
<%
	for (String src : tList) {
		System.out.println("-------------------------------------" + src);
%>
<td><img alt=""  src="<%=src %>"  style="width:50%,height:100%"/></td>

<%
	}
%>
  	
  </tr>
 </table>
