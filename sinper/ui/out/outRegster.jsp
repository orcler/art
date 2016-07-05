<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="outrg_fm" action="in_resiter.form"  method="post" >
	<table class="easyui-datagrid" title="出库清单" id="outrg_dg" data-options="url:'./outdataquery.form?qtype=out_df',method:'get',singleSelect:true,fitColumns:true,autoRowHeight:false,pagination:true,rownumbers:true,pageSize:10,toolbar:'#outr_ft'">
	<thead>
		<tr>
			<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'SerialNo',hidden:true" width="150">流水号</th>
			<th data-options="field:'activityId',hidden:true" width="80">工作流节点</th>
			<th data-options="field:'oSerialNo'" width="150">出库流水号</th>
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
	<span style="margin-left:10px"></span>付款: <input type="checkbox" id="or_paymode"  name="or_paymode" onchange="payMode(this)" />
	<div id="or_paymoney" style="display:none">
		<span style="margin-left:10px">交付金额：<input class="easyui-textbox"  type="text" id="orMoney"  name="orMoney" readonly="readonly" style="width:130px">&nbsp;万</span>
	</div>
	<div id="or_paycar" >
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
				<td><input class="easyui-textbox" type="text"  id="orMileage" name="orMileage" data-options="required:false" />KM</td>
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
	</div>
	
	<div style="text-align:left;padding:5px">
		<span style="margin-left:180px"></span><a href="javascript:out_regster_submit()" class="easyui-linkbutton" >保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
	</div>
	<input  type="text"  id="or_wfserialno" name="or_wfserialno"  hidden="hidden">
	<input  type="text"  id="out_tfserialno" name="out_tfserialno"  hidden="hidden">
	<input  type="text"  id="or_activityId" name="or_activityId"  hidden="hidden">
</form>
<script>
$(document).ready(function() {
	$('#outrg_dg').datagrid({
		onClickRow: function () {
			var row = $('#outrg_dg').datagrid('getSelected');
			if (row){
				$('#orMoney').textbox('setValue',row.cost);//付款
				$('#or_wfserialno').val(row.SerialNo);
				$('#out_tfserialno').val(row.oSerialNo);
				$('#or_activityId').val(row.activityId);
				$('#orComcode').combobox("setValue", row.comcode);
				$('#orComcode').combobox("setText", row.comname);
 				$('#orEngineNo').textbox("setValue", row.iEngineNo);
				$('#orVIN').textbox("setValue", row.iVIN);
				$('#orModel').textbox("setValue", row.iModel);
				//$('#orCert').textbox("setValue", row.cert);
				$('#orCost').textbox("setValue", row.iCost);
				$('#orMileage').textbox("setValue", row.iMileage);
				$('#orColor').textbox("setValue", row.iColor);
				/*$('#orAttn').textbox("setValue", row.attn);
				$('#orPhone').textbox("setValue", row.phone); */
				$('#orRemark').textbox("setValue", row.remark);
			}
		}
	})
});
</script>