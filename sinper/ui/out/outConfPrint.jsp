<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="outconf_form" method="post">
	<table class="easyui-datagrid" title="出库待确认列表" id="outconf_dg" data-options="url:'./outdataquery.form?qtype=outconf',method:'get',singleSelect:true,toolbar:'#outconf_ft' ">
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
			<th data-options="field:'indate'" width="80">出库日期</th>
			<th data-options="field:'operator'" width="80">操作员</th>
			<th data-options="field:'paymode',hidden:true" width="80">付款方式</th>
		</tr>
	</thead>
	</table>
<div id="outconf_ft" style="padding:2px 5px;">
		车源：<select class="easyui-combobox"  id="outcf_comcode"	name="outcf_comcode" style="width:110px;" 
					data-options="	url:'codequery.form?codetype=comcode',method:'get',valueField:'id',textField:'text',panelHeight:'auto'">
			</select>
		发动机编码：<input class="easyui-textbox"  id="outcf_engineno" name="outcf_engineno" style="width:110px">
		起始日期: <input class="easyui-datebox"  id="outcf_startdate" name="outcf_startdate"  style="width:110px">
		终止日期: <input class="easyui-datebox"  id="outcf_enddate" name="outcf_enddate"  style="width:110px">
		<a href="javascript:outcf_search()" class="easyui-linkbutton"  id="outcf_search" name="outcf_search"  iconCls="icon-search">搜索</a>
		<a href="javascript:outcf_print()" class="easyui-linkbutton"  >打印凭证</a>
		<a href="javascript:outconf_insubmit()" 	id="outcf_in"  name="outcf_in"	class="easyui-linkbutton" >入库确认</a>
		<a href="javascript:outconf_outsubmit()" class="easyui-linkbutton" >出库确认</a>
</div>
<input type="hidden" id="outconf_serialno" name="outconf_serialno" >
</form>
<script>
$(document).ready(function() {
	$('#outconf_dg').datagrid({
		onClickRow: function () {
			var row = $('#outconf_dg').datagrid('getSelected');
			if (row){
				$('#outconf_serialno').val(row.SerialNo);
				if ('1'==row.paymode) {
				$('#outcf_in').linkbutton('disable');
				} else {
					$('#outcf_in').linkbutton('enable');
				}
			} 
		}
	})
});
</script>
