<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="outrg_fm" action="in_resiter.form"  method="post" >
	<table class="easyui-datagrid" title="出库清单" id="outrg_dg" data-options="url:'./outdataquery.form?qtype=out_df',method:'get',singleSelect:true,fitColumns:true,autoRowHeight:false,pagination:true,rownumbers:true,pageSize:10,toolbar:'#outr_ft'">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'SerialNo'" width="150">流水号</th>
			<th data-options="field:'EngineNo'" width="80">发动机编码</th>
			<th data-options="field:'VIN'" width="80">机架编码</th>
			<th data-options="field:'model'" width="80">车型</th>
			<th data-options="field:'cert'" width="80">合格证号</th>
			<th data-options="field:'cost',align:'right' " width="80">价值</th>
			<th data-options="field:'mileage',align:'right'" width="80">行驶里程（km）</th>
			<th data-options="field:'color'" width="80">颜色</th>
			<th data-options="field:'attn'" width="80">联系人</th>
			<th data-options="field:'comcode',hidden:true" width="80">车源编码</th>
			<th data-options="field:'comname'" width="80">车源</th>
			<th data-options="field:'indate'" width="80">入库日期</th>
			<th data-options="field:'operator'" width="80">操作员</th>
			<th data-options="field:'remark',hidden:true" width="80">备注</th>
			<th data-options="field:'outSerialNo',hidden:true" width="80">出库流水号</th>
		</tr>
	</thead>
	</table>
	<div id="outr_ft" style="padding:2px 5px;">
		车源：<select class="easyui-combobox"  id="outrg_comcode"	name="outrg_comcode" style="width:110px;" 
					data-options="	url:'codequery.form?codetype=comcode',method:'get',valueField:'id',textField:'text',panelHeight:'auto'">
			</select>
		发动机编码：<input class="easyui-textbox"  id="outrg_engineno" name="outrg_engineno" style="width:110px">
		起始日期: <input class="easyui-datebox"  id="outrg_startdate" name="outrg_startdate"  style="width:110px">
		终止日期: <input class="easyui-datebox"  id="outrg_enddate" name="outrg_enddate"  style="width:110px">
		<a href="javascript:outrg_search()" class="easyui-linkbutton"  id="outrg_search" name="outrg_search"  iconCls="icon-search">搜索</a>
</div>
	<div><h2>入库机型信息</h2></div>
	<table cellpadding="5">
		<tr>
			<td>发动机编码</td>
			<td><input class="easyui-textbox" type="text" id="orEngineNo"  name="orEngineNo" data-options="required:true"></td>
			<td>机架号</td>
			<td><input class="easyui-textbox" type="text" id="orVIN"  name="orVIN" data-options="required:true"></td>
		</tr>
		<tr>
			<td>车型</td>
			<td><input class="easyui-textbox" type="text" id="orModel"  name="orModel"  ></input></td>
			<td>合格证号</td>
			<td><input class="easyui-textbox" type="text"  id="orCert"  name="orCert" ></input></td>
		</tr>
		<tr>
			<td>价值</td>
			<td><input class="easyui-textbox" type="text"  id="orCost" name="orCost" ></input>万</td>
			<td>行驶里程</td>
			<td><input class="easyui-textbox" type="text"  id="orMileage" name="orMileage" data-options="required:false">KM</input></td>
		</tr>
		<tr>
			<td>颜色</td>
			<td><input class="easyui-textbox" type="text" id="orColor"  name="orColor" data-options="required:false"></input></td>
			<td>联系人</td>
			<td><input class="easyui-textbox" type="text"  id="orAttn"  name="orAttn" data-options="required:false"></input></td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td><input class="easyui-textbox" type="text"  id="orPhone"  name="orPhone" data-options="required:false"></input></td>
			<td>车源</td>
			<td><select class="easyui-combobox"  id="orComcode"	name="orComcode" style="width:145px;" data-options="	alueField:'id',textField:'text',panelHeight:'auto'">
			</select></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan='3'><input class="easyui-textbox" id="orRemark"  name="orRemark"  data-options="multiline:true" style="width:360px;height:60px"></input></td>
		</tr>
	</table>
	<div style="text-align:left;padding:5px">
		<span style="margin-left:180px"/><a href="javascript:out_regster_submit()" class="easyui-linkbutton" >保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
	</div>
	<input  type="textbox"  id="ir_wfserialno" name="ir_wfserialno"  hidden="hidden">
	<input  type="textbox"  id="out_tfserialno" name="out_tfserialno"  hidden="hidden">
</form>
<script>
$(document).ready(function() {
	$('#outrg_dg').datagrid({
		onClickRow: function () {
			var row = $('#outrg_dg').datagrid('getSelected');
			if (row){
				$('#ir_wfserialno').val(row.SerialNo);
				$('#out_tfserialno').val(row.outSerialNo);
				$('#orComcode').combobox("setValue", row.comcode);
				$('#orComcode').combobox("setText", row.comname);
/* 				$('#EngineNo').textbox("setValue", row.EngineNo);
				$('#VIN').textbox("setValue", row.VIN);
				$('#model').textbox("setValue", row.model);
				$('#cert').textbox("setValue", row.cert);
				$('#cost').textbox("setValue", row.cost);
				$('#mileage').textbox("setValue", row.mileage);
				$('#color').textbox("setValue", row.color);
				$('#attn').textbox("setValue", row.attn);
				$('#phone').textbox("setValue", row.phone);
				$('#remark').textbox("setValue", row.remark);
				$('#comcode').combobox("setValue", row.comcode);
				$('#comcode').val(row.comcode); */
			}
		}
	})
});
</script>