<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String tMsg = (String)request.getAttribute("msg");
	System.out.println(tMsg);
%>
<%=tMsg%>