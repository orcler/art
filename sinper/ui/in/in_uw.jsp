<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="ff" method="post">
	<table class="easyui-datagrid" title="入库待审核列表" id="inuw_dg" data-options="rownumbers:true,url:'./dataquery.form?qtype=inuw',method:'post',singleSelect:true,fitColumns:true">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'SerialNo'" width="80">流水号</th>
			<th data-options="field:'EngineNo'" width="80">发动机编码</th>
			<th data-options="field:'VIN'" width="80">机架编码</th>
			<th data-options="field:'model'" width="80">车型</th>
			<th data-options="field:'cost',align:'right' " width="80">价值</th>
			<th data-options="field:'mileage',align:'right'" width="80">行驶里程（km）</th>
			<th data-options="field:'color'" width="80">颜色</th>
			<th data-options="field:'attn'" width="80">联系人</th>
			<th data-options="field:'comname'" width="80">车源</th>
			<th data-options="field:'indate'" width="80">入库日期</th>
			<th data-options="field:'operator'" width="80">操作员</th>
		</tr>
	</thead>
	</table>
	<div><h2>入库机型信息</h2></div>
	<table cellpadding="5" >
		<tr>
			<td>入库发动机编码</td>
			<td><input class="easyui-textbox" type="text" id="inuw_engineno"  readonly="readonly" name="inuw_engineno" /></td>
			<td>机架号</td>
			<td><input class="easyui-textbox" type="text" id="inuw_vin"  name="inuw_vin"  readonly="readonly" ></td>
		</tr>
		<tr>
			<td>车型</td>
			<td><input class="easyui-textbox" type="text" id="inuw_model"  name="inuw_model"  readonly="readonly" ></input></td>
			<td>车源</td>
			<td><input class="easyui-textbox" type="text"  id="inuw_comname"  name="inuw_comname"  readonly="readonly" ></input></td>
		</tr>
		<tr>
			<td>价值</td>
			<td><input class="easyui-textbox" type="text"  id="inuw_cost" name="inuw_cost" readonly="readonly"  ></input>万</td>
			<td>行驶里程</td>
			<td><input class="easyui-textbox" type="text"  id="inuw_mileage" name="inuw_mileage" readonly="readonly" >KM</input></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan='3'><input class="easyui-textbox"  id="inuw_remark" name="inuw_remark" data-options="multiline:true" style="width:400px;height:60px"/></td>
		</tr>
	</table>
	<div style="text-align:left;padding:5px">
		<span style="margin-left:20px"/>
		审核结论<select class="easyui-combobox" name="inuw_uwstate" style="width:200px;">
			<option value="1">通过</option>
			<option value="2">退回</option>
			<option value="3">拒绝</option>
		</select>
		<br>
		<span style="margin-left:180px"/>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a></br>
		<input  type="textbox"  id="inuw_serialno" name="inuw_serialno"  hidden="hidden">
	</div>
</form>
<script>
$(document).ready(function() {
	$('#inuw_dg').datagrid({
		onClickRow: function () {
			var row = $('#inuw_dg').datagrid('getSelected');
			if (row){
				
				$('#inuw_serialno').val(row.EngineNo);
				$('#inuw_engineno').textbox("setValue", row.EngineNo);
				$('#inuw_vin').textbox("setValue", row.VIN);
				$('#inuw_model').textbox("setValue", row.model);
				$('#inuw_comname').textbox("setValue", row.comname);
				$('#inuw_cert').textbox("setValue", row.cert);
				$('#inuw_cost').textbox("setValue", row.cost);
				$('#inuw_mileage').textbox("setValue", row.mileage);
			}
		}
	})
});
</script>