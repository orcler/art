<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="ivtupload_submit_form" method="post">
	<table class="easyui-datagrid" title="盘点确认" id="ivtupload_dg" data-options="url:'./invertdata.form?qtype=upload',method:'get',singleSelect:true,pagination:true,rownumbers:true,pageSize:10,toolbar:'#ivtupload_ft'">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'SerialNo'" width="150">流水号</th>
			<th data-options="field:'EngineNo'" width="100">发动机编码</th>
			<th data-options="field:'VIN'" width="100">机架编码</th>
			<th data-options="field:'model'" width="100">车型</th>
			<th data-options="field:'cost',align:'right' " width="100">价值（万）</th>
			<th data-options="field:'mileage',align:'right'" width="100">行驶里程（km）</th>
			<th data-options="field:'color'" width="80">颜色</th>
			<th data-options="field:'attn'" width="80">联系人</th>
			<th data-options="field:'comname'" width="100">车源</th>
			<th data-options="field:'indate'" width="80">入库日期</th>
		</tr>
	</thead>
	</table>
<div id="ivtupload_ft" style="padding:2px 5px;">
		车源：<select class="easyui-combobox"  id="ivtupload_comcode"	name="ivtupload_comcode" style="width:110px;" 
					data-options="	url:'codequery.form?codetype=comcode',method:'get',valueField:'id',textField:'text',panelHeight:'auto'">
			</select>
		发动机编码：<input class="easyui-textbox"  id="ivtupload_engineno" name="ivtupload_engineno" style="width:110px">
		起始日期: <input class="easyui-datebox"  id="ivtupload_startdate" name="ivtupload_startdate"  style="width:110px">
		终止日期: <input class="easyui-datebox"  id="ivtupload_enddate" name="ivtupload_enddate"  style="width:110px">
		<a href="javascript:ivtupload_search()" class="easyui-linkbutton"  id="ivtupload_search" name="ivtupload_search"  iconCls="icon-search">搜索</a>
</div>
<input type="hidden" id="ivtupload_serialno" name="ivtupload_serialno" >
</form>
<div>
	<h2>图片上传</h2>
	<div style="margin:20px 0;"></div>
<form id="ivtupload_form"  action="ivtupload.form" method="post"  enctype="multipart/form-data">
		图片上传：<input class="easyui-filebox" id="upload"  name="upload" style="width:30%"/>
		<span style="margin-left:10px"/></span><a href="javascript:ivtupload()"  class="easyui-linkbutton" >上传图片</a>
		<span style="margin-left:10px"/></span><a href="javascript:ivtupload_submit()"  class="easyui-linkbutton" >提&nbsp;&nbsp;&nbsp;&nbsp;交</a>
</form>
</div>
<script>
$(document).ready(function() {
	$('#ivtupload_dg').datagrid({
		onClickRow: function () {
			var row = $('#ivtupload_dg').datagrid('getSelected');
			if (row){
				$('#ivtupload_serialno').val(row.SerialNo);
			}
		}
	})
});
</script>