<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<h2>批量入库登记数据上传</h2>
	<div style="margin:20px 0;"></div>
	<span style="margin-left:20px;"/><p>批量入库&nbsp;<a href='./data/in_templete.xls'>模板下载</a></p>
	<form id="inbatch_form"  action="upload.form" method="post"  enctype="multipart/form-data">
		批量入库导入：<input class="easyui-filebox" id="upload"  name="upload" style="width:30%"><span style="margin-left:10px;"/>
		<a href="javascript:batchupdate()"  class="easyui-linkbutton" >上传数据</a></span>
	</form>