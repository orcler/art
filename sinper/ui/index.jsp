<%@page import="java.util.Enumeration"%>
<%@page import="com.art.schema.MenuSchema"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%
String tUserId = request.getParameter("userId");
String tPwd = request.getParameter("index - pwd");
List <MenuSchema>tMenus = (List<MenuSchema>)session.getAttribute("amenu");
System.out.println("index - " + tUserId);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>出入库管理系统</title>
	<link rel="stylesheet" type="text/css" href="./themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="./themes/icon.css">
	<link rel="stylesheet" type="text/css" href="./demo.css">
	<script type="text/javascript" src="./js/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="./js/jquery.form.js"></script>
	<script type="text/javascript" src="./js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./js/in.js"></script>
	<script type="text/javascript" src="./js/out.js"></script>
	<script type="text/javascript" src="./js/query.js"></script>
	<script type="text/javascript" src="./js/mager.js"></script>
</head>
<body class="easyui-layout">
		<div data-options="region:'north'" style="height:100px">
			</p>
			<span style="margin-left:10px;"/>
			<img src='./images/logo/logo3.jpg' height='60%' data-options="region:'right'">	
			<img src='./images/logo/logo1.jpg' height='60%' data-options="region:'left'"/>
		</div>
		<div data-options="region:'west',split:true" title="菜单" style="width:280px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
<!-- 				<div title="入库管理" data-options="selected:false" style="padding:10px;">
 					<a href="javascript:inRgterUI()"><font size='3'>入库登记</font></a></br>
					<a href="javascript:inUw()"><font size='3'>入库审核</font></a></br>
					<a href="javascript:confInPrint()"><font size='3'>打印凭证和入库确认</font></a></br>
					<a href="javascript:batchregster()"><font size='3'>批量入库</font></a></br>
				</div>
				<div title="出库管理" data-options="selected:false" style="padding:10px;">
					<a href="javascript:outRgterUI()"><font size='3'>经销商出库申请</font></a></br>
					<a href="javascript:outUw()"><font size='3'>出库审核</font></a></br>
					<a href="javascript:confOutPrint()"><font size='3'>打印凭证和出入库确认</font></a></br>
				</div>
				<div title="库存管理" data-options="selected:false" style="padding:10px;">
					<a href="javascript:inQuery()"><font size='3'>入库查询</font></a></br>
					<a href="javascript:outQuery()"><font size='3'>出库查询</font></a></br>
					<a href="javascript:existsQuery()"><font size='3'>库存查询</font></a></br>
					<a href="javascript:invertApply()"><font size='3'>库存盘点</font></a></br>
					<a href="javascript:invertUpload()"><font size='3'>盘点上传</font></a></br>
					<a href="javascript:invertConf()"><font size='3'>盘点确认</font></a></br>
				</div>
				<div title="系统管理" style="padding:10px">
					<a href='javascript:addRole()'><font size='3'>权限管理</font></a></br>
					<a href='javascript:addUser()'><font size='3'>用户管理</font></a></br>
				</div> -->
				<%
					for (MenuSchema menu : tMenus) {
					    %>
					    <div title=<%=menu.getNodeName() %> style="padding:10px">
					    	<% 
					    		for (MenuSchema submenu : menu.getChildren()) {
					    		System.out.println(submenu.getNodeName());
					    		    %>
					    		    <a href='javascript:<%=submenu.getRunScript()%>'><font size='3'><%=submenu.getNodeName()%></font></a></br>
					    		    <%
					    		}
					    	%>
					    	</div>
					    <%
					}
				%>
		</div>
		</div>
		<!-- Context-->
		<div data-options="region:'center',title:'登录人员：<%=tUserId %>',iconCls:'icon-ok'">
			<div id="DivTab" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div id="SysInfo" title="系统介绍" data-options="href:'_content.html'" style="padding:10px"></div>
			</div>
		</div>
</body>
</html>