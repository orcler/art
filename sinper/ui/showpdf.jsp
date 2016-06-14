<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String tFilePath = (String)request.getAttribute("pdffile");
	System.out.println(tFilePath);
	try
	{
	   FileInputStream fis = new FileInputStream(tFilePath);  
	   ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	   byte[] b = new byte[1024];  
	   int n;  
	   while ((n = fis.read(b)) != -1) 
	   {
	        bos.write(b, 0, n);  
	   }  
	   fis.close();  
	   bos.close();  
	   byte[] buffer = bos.toByteArray(); 
	   
	   response.setContentType("application/pdf"); 
	   ServletOutputStream ouputStream = response.getOutputStream(); 
	   ouputStream.write(buffer,0,buffer.length); 
	   ouputStream.flush(); 
	   ouputStream.close();
	   
	   out.clear();   
	   out = pageContext.pushBody(); 
   }catch(Exception ex)
   {
   		ex.printStackTrace();
   }
%>