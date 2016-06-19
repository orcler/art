<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="ivtapp_form" method="post">
	<table class="easyui-datagrid" title="库存清单" id="ivtapp_dg" data-options="url:'./invertdata.form?qtype=app',method:'get',singleSelect:true,pagination:true,rownumbers:true,pageSize:10,toolbar:'#ivtapp_ft'">
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
<div id="ivtapp_ft" style="padding:2px 5px;">
		车源：<select class="easyui-combobox"  id="ivtapp_comcode"	name="ivtapp_comcode" style="width:110px;" 
					data-options="	url:'codequery.form?codetype=comcode',method:'get',valueField:'id',textField:'text',panelHeight:'auto'">
			</select>
		发动机编码：<input class="easyui-textbox"  id="ivtapp_engineno" name="ivtapp_engineno" style="width:110px">
		起始日期: <input class="easyui-datebox"  id="ivtapp_startdate" name="ivtapp_startdate"  style="width:110px">
		终止日期: <input class="easyui-datebox"  id="ivtapp_enddate" name="ivtapp_enddate"  style="width:110px">
		<a href="javascript:ivtapp_search()" class="easyui-linkbutton"  id="ivtapp_search" name="ivtapp_search"  iconCls="icon-search">搜索</a>
		<a href="javascript:ivtapp_submit()" class="easyui-linkbutton"  >盘点申请</a>
</div>
<input type="hidden" id="ivtapp_serialno" name="ivtapp_serialno" >
</form>
<script>
$(document).ready(function() {
	$('#ivtapp_dg').datagrid({
		onClickRow: function () {
			var row = $('#ivtapp_dg').datagrid('getSelected');
			if (row){
				$('#ivtapp_serialno').val(row.SerialNo);
			}
		}
	})
});
</script>