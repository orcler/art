<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="outuw_form" method="post">
	<table class="easyui-datagrid" title="出库待审核列表" id="outuw_dg" data-options="url:'./outdataquery.form?qtype=out_uw',method:'get',singleSelect:true,fitColumns:true,autoRowHeight:false,pagination:true,rownumbers:true,pageSize:10">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'SerialNo'" width="150">流水号</th>
			<th data-options="field:'oEngineNo'" width="80">发动机编码</th>
			<th data-options="field:'oVIN'" width="80">机架编码</th>
			<th data-options="field:'oModel'" width="80">车型</th>
			<th data-options="field:'oCost',align:'right' " width="80">价值</th>
			<th data-options="field:'oMileage',align:'right'" width="80">行驶里程（km）</th>
			<th data-options="field:'oColor'" width="80">颜色</th>
			<th data-options="field:'oAttn'" width="80">联系人</th>
			<th data-options="field:'comname'" width="80">车源</th>
			<th data-options="field:'opdate'" width="80">操作日期</th>
			<th data-options="field:'operator'" width="80">操作员</th>
			<th data-options="field:'pay',hidden:true" width="80">付款金额</th>
			<th data-options="field:'iEngineNo',hidden:true" width="80">发动机编码</th>
			<th data-options="field:'iVIN',hidden:true" width="80">机架编码</th>
			<th data-options="field:'iModel',hidden:true" width="80">车型</th>
			<th data-options="field:'iCost',hidden:true" width="80">价值</th>
			<th data-options="field:'iMileage',hidden:true" width="80">行驶里程（km）</th>
			<th data-options="field:'remark',hidden:true" width="80">备注</th>
			<th data-options="field:'iColor',hidden:true" width="80">颜色</th>
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
			<td>付款金额</td>
			<td colspan='3'><input class="easyui-textbox"  id="ou_pay"  name="ou_pay"  style="width:140px"></input></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan='3'><input class="easyui-textbox"  id="ou_remark"  name="ou_remark" data-options="multiline:true" style="width:360px;height:60px"></input></td>
		</tr>
	</table>
	<div style="text-align:left;padding:5px">
		<span style="margin-left:20px"></span>
		审核结论<select class="easyui-combobox"  id="ou_uwstate"  name="ou_uwstate" style="width:200px;">
			<option value="1">通过</option>
			<!-- <option value="2">退回</option> -->
			<option value="3">拒绝</option>
		</select>
		<br>
		<span style="margin-left:180px"></span>
		<a href="javascript:ou_submit()" class="easyui-linkbutton" >保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a></br>
		<input  type="text"  id="ou_serialno" name="ou_serialno"  hidden="hidden">
	</div>
</form>
<script>
$(document).ready(function() {
	$('#outuw_dg').datagrid({
		onClickRow: function () {
			var row = $('#outuw_dg').datagrid('getSelected');
			if (row){
				$('#ou_serialno').val(row.SerialNo);
				$('#ouoEngineNo').textbox("setValue", row.oEngineNo);
				$('#ouoVIN').textbox("setValue", row.oVIN);
				$('#ouoModel').textbox("setValue", row.oModel);
				$('#ouoCost').textbox("setValue", row.oCost);
				$('#ouoMileage').textbox("setValue", row.oMileage);
				if ('1'==row.pay) {
					$('#ou_pay').textbox("setValue", row.oCost);
					$('#ouiEngineNo').textbox("setValue", '');
					$('#ouiVIN').textbox("setValue", '');
					$('#ouiModel').textbox("setValue", '');
					$('#ouiCost').textbox("setValue", '');
					$('#ouiMileage').textbox("setValue", '');
				}else {
					$('#ou_pay').textbox("setValue", '');
					$('#ouiEngineNo').textbox("setValue", row.iEngineNo);
					$('#ouiVIN').textbox("setValue", row.iVIN);
					$('#ouiModel').textbox("setValue", row.iModel);
					$('#ouiCost').textbox("setValue", row.iCost);
					$('#ouiMileage').textbox("setValue", row.iMileage);
				}
				$('#ou_remark').textbox("setValue", row.remark);
			}
		}
	})
});
</script>