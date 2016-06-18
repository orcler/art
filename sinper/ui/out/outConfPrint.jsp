<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="inconf_form" method="post">
	<table class="easyui-datagrid" title="出库待确认列表" id="inconf_dg" data-options="rownumbers:true,url:'./dataquery.form?qtype=inconf',method:'get',singleSelect:true,toolbar:'#inconf_ft'">
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
			<th data-options="field:'operator'" width="80">操作员</th>
		</tr>
	</thead>
	</table>
<div id="inconf_ft" style="padding:2px 5px;">
		车源：<select class="easyui-combobox"  id="incf_comcode"	name="incf_comcode" style="width:110px;" 
					data-options="	url:'codequery.form?codetype=comcode',method:'get',valueField:'id',textField:'text',panelHeight:'auto'">
			</select>
		发动机编码：<input class="easyui-textbox"  id="incf_engineno" name="incf_engineno" style="width:110px">
		起始日期: <input class="easyui-datebox"  id="incf_startdate" name="incf_startdate"  style="width:110px">
		终止日期: <input class="easyui-datebox"  id="incf_enddate" name="incf_enddate"  style="width:110px">
		<a href="javascript:incf_search()" class="easyui-linkbutton"  id="incf_search" name="incf_search"  iconCls="icon-search">搜索</a>
		<a href="javascript:incf_print()" class="easyui-linkbutton"  >打印凭证</a>
		<a href="javascript:inconf_submit()" class="easyui-linkbutton" >入库确认</a>
</div>
<input type="hidden" id="inconf_serialno" name="inconf_serialno" >
</form>
<script>
$(document).ready(function() {
	$('#inconf_dg').datagrid({
		onClickRow: function () {
			var row = $('#inconf_dg').datagrid('getSelected');
			if (row){
				$('#inconf_serialno').val(row.SerialNo);
			}
		}
	})
});
</script>
