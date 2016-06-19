<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="inquery_form" method="post">
	<table class="easyui-datagrid" title="入库清单" id="inquery_dg" data-options="rownumbers:true,url:'./dataquery.form?qtype=inquery',method:'get',singleSelect:true,toolbar:'#inquery_ft'">
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
<div id="inquery_ft" style="padding:2px 5px;">
		车源：<select class="easyui-combobox"  id="incf_comcode"	name="incf_comcode" style="width:110px;" 
					data-options="	url:'codequery.form?codetype=comcode',method:'get',valueField:'id',textField:'text',panelHeight:'auto'">
			</select>
		发动机编码：<input class="easyui-textbox"  id="incf_engineno" name="inquery_engineno" style="width:110px">
		起始日期: <input class="easyui-datebox"  id="incf_startdate" name="inquery_startdate"  style="width:110px">
		终止日期: <input class="easyui-datebox"  id="incf_enddate" name="inquery_enddate"  style="width:110px">
		<a href="javascript:inquery_search()" class="easyui-linkbutton"  id="inquery_search" name="inquery_search"  iconCls="icon-search">搜索</a>
</div>
<input type="hidden" id="inquery_serialno" name="inquery_serialno" >
</form>
<script>
$(document).ready(function() {
	$('#inquery_dg').datagrid({
		onClickRow: function () {
			var row = $('#inquery_dg').datagrid('getSelected');
			if (row){
				$('#inquery_serialno').val(row.SerialNo);
			}
		}
	})
});
</script>
