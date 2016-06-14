<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String tJson = (String)request.getAttribute("json");
	System.out.println(tJson);
%>
<%=tJson%>