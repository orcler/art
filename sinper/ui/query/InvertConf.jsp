<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="ivtconf_form" method="post">
	<table class="easyui-datagrid" title="库存清单" id="ivtconf_dg" data-options="url:'./invertdata.form?qtype=conf',method:'get',singleSelect:true,pagination:true,rownumbers:true,pageSize:10,toolbar:'#ivtconf_ft'">
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
<div id="ivtconf_ft" style="padding:2px 5px;">
		车源：<select class="easyui-combobox"  id="ivtconf_comcode"	name="ivtconf_comcode" style="width:110px;" 
					data-options="	url:'codequery.form?codetype=comcode',method:'get',valueField:'id',textField:'text',panelHeight:'auto'">
			</select>
		发动机编码：<input class="easyui-textbox"  id="ivtconf_engineno" name="ivtconf_engineno" style="width:110px">
		起始日期: <input class="easyui-datebox"  id="ivtconf_startdate" name="ivtconf_startdate"  style="width:110px">
		终止日期: <input class="easyui-datebox"  id="ivtconf_enddate" name="ivtconf_enddate"  style="width:110px">
		<a href="javascript:ivtconf_search()" class="easyui-linkbutton"  id="ivtconf_search" name="ivtconf_search"  iconCls="icon-search">搜&nbsp;&nbsp;&nbsp;&nbsp;索</a>
		<span style="margin-left:10px"/></span><a href="javascript:ivtconf_submit()"  class="easyui-linkbutton" >提&nbsp;&nbsp;&nbsp;&nbsp;交</a>
</div>
<div id="showimg" title="图片查看"  style="width:100%,height:100%"></div>
<input type="hidden" id="ivtconf_serialno" name="ivtconf_serialno" >
</form>
<script>
$(document).ready(function() {
	$('#ivtconf_dg').datagrid({
		onClickRow: function () {
			var row = $('#ivtconf_dg').datagrid('getSelected');
			if (row){
				$('#ivtconf_serialno').val(row.SerialNo);
				ivtconf_show();
			}
		}
	})
});
</script>