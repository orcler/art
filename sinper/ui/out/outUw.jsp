<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="outuw_form" method="post">
	<table class="easyui-datagrid" title="出库待审核列表" id="outuw_dg" data-options="url:'./outdataquery.form?qtype=out_uw',method:'get',singleSelect:true,fitColumns:true,autoRowHeight:false,pagination:true,rownumbers:true,pageSize:10">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'SerialNo'" width="150">流水号</th>
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
			<th data-options="field:'oEngineNo',hidden:true" width="80">发动机编码</th>
			<th data-options="field:'oVIN',hidden:true" width="80">机架编码</th>
			<th data-options="field:'omodel',hidden:true" width="80">车型</th>
			<th data-options="field:'ocost',align:'right' ,hidden:true" width="80">价值</th>
			<th data-options="field:'omileage',align:'right',hidden:true" width="80">行驶里程（km）</th>
			<th data-options="field:'ocolor',hidden:true" width="80">颜色</th>
		</tr>
	</thead>
	</table>
	<div><h2>出库明细</h2></div>
	<table cellpadding="5" >
		<tr>
			<td>出库发动机编码</td>
			<td><input class="easyui-textbox" type="text"  id="ouoEngineNo"  name="ouoEngineNo"  ></td>
			<td>入库发动机编码</td>
			<td><input class="easyui-textbox" type="text" id="ouiEngineNo" name="ouiEngineNo"  ></td>
			
		</tr>
		<tr>
			<td>出库车辆大架号</td>
			<td><input class="easyui-textbox" type="text" id="ouoVIN"  name="ouoVIN"   ></td>
			<td>入库车辆大架号</td>
			<td><input class="easyui-textbox" type="text" id="ouiVIN"  name="ouiVIN"  ></input></td>
		</tr>
		<tr>
			<td>出库价值</td>
			<td><input class="easyui-textbox" type="text"  id="ouoCost"  name="ouoCost"  ></input>万</td>
			<td>入库价值</td>
			<td><input class="easyui-textbox" type="text"  id="ouiCost"  name="ouiCost"  ></input>万</td>
			
		</tr>
		<tr>
			<td>出库车型</td>
			<td><input class="easyui-textbox" type="text" id="ouoModel"  name="ouoModel"  ></input></td>
			<td>入库车型</td>
			<td><input class="easyui-textbox" type="text"  id="ouiModel"  name="ouiModel" " ></input></td>
		</tr>
		<tr>
			<td>出库行驶里程</td>
			<td><input class="easyui-textbox" type="text"  id= "ouoMileage" name="ouoMileage" ></input></td>
			<td>入库行驶里程</td>
			<td><input class="easyui-textbox" type="text"  id="ouiMileage" name="ouiMileage" ></input>公里</td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan='3'><input class="easyui-textbox"  id="outuw_remark"  name="outuw_remark" data-options="multiline:true" style="width:360px;height:60px"></input></td>
		</tr>
	</table>
	<div style="text-align:left;padding:5px">
		<span style="margin-left:20px"/>
		审核结论<select class="easyui-combobox" name="outuw_uwstate" style="width:200px;">
			<option value="1">通过</option>
			<!-- <option value="2">退回</option> -->
			<option value="3">拒绝</option>
		</select>
		<br>
		<span style="margin-left:180px"/>
		<a href="javascript:ou_submit()" class="easyui-linkbutton" >保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a></br>
		<input  type="textbox"  id="outuw_serialno" name="outuw_serialno"  hidden="hidden">
	</div>
</form>
<script>
$(document).ready(function() {
	$('#outuw_dg').datagrid({
		onClickRow: function () {
			var row = $('#outuw_dg').datagrid('getSelected');
			if (row){
				$('#outuw_serialno').val(row.SerialNo);
				$('#ouoEngineNo').textbox("setValue", row.oEngineNo);
				$('#ouoVIN').textbox("setValue", row.oVIN);
				$('#ouoModel').textbox("setValue", row.omodel);
				$('#ouoCost').textbox("setValue", row.ocost);
				$('#ouoMileage').textbox("setValue", row.omileage);
				
				$('#ouiEngineNo').textbox("setValue", row.EngineNo);
				$('#ouiVIN').textbox("setValue", row.VIN);
				$('#ouiModel').textbox("setValue", row.model);
				$('#ouiCost').textbox("setValue", row.cost);
				$('#ouiMileage').textbox("setValue", row.mileage);
				
				$('#inuw_remark').textbox("setValue", row.remark);
			}
		}
	})
});
</script>