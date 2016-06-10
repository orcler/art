<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<form id="fm_in_save" action="in_resiter.form"  method="post" >
	<table cellpadding="5">
		<tr>
			<td>发动机编码</td>
			<td><input class="easyui-textbox" type="text" id="EngineNo"  name="EngineNo" data-options="required:true"></td>
			<td>机架号</td>
			<td><input class="easyui-textbox" type="text" id="VIN"  name="VIN" data-options="required:true"></td>
		</tr>
		<tr>
			<td>车型</td>
			<td><input class="easyui-textbox" type="text" id="model"  name="model"  ></input></td>
			<td>合格证号</td>
			<td><input class="easyui-textbox" type="text"  id="cert"  name="cert" ></input></td>
		</tr>
		<tr>
			<td>价值</td>
			<td><input class="easyui-textbox" type="text"  id="cost" name="cost" ></input></td>
			<td>行驶里程</td>
			<td><input class="easyui-textbox" type="text"  id="mileage" name="mileage" data-options="required:false"></input></td>
		</tr>
		<tr>
			<td>颜色</td>
			<td><input class="easyui-textbox" type="text" id="color"  name="color" data-options="required:false"></input></td>
			<td>联系人</td>
			<td><input class="easyui-textbox" type="text"  id="attn"  name="attn" data-options="required:false"></input></td>
		</tr>
		<tr>
			<td>联系电话</td>
			<td><input class="easyui-textbox" type="text"  id="phone"  name="phone" data-options="required:false"></input></td>
			<td>入机经销商</td>
			<td><input class="easyui-textbox" type="text"  id="comcode" name="comcode" data-options="required:false"></input></td>
		</tr>
		<tr>
			<td>单位名称</td>
			<td><input class="easyui-textbox" type="text"  id="group"  name="group" data-options="required:false"></input></td>
		</tr>
		<tr>
			<td>备注</td>
			<td colspan='3'><input class="easyui-textbox" id="remark"  name="remark"  data-options="multiline:true" style="width:360px;height:60px"></input></td>
		</tr>
	</table>
	<div style="text-align:left;padding:5px">
		<span style="margin-left:180px"/><a href="javascript:submitForm()" class="easyui-linkbutton" >保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
	</div>
</form>