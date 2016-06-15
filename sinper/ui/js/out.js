//出库登记
function outRgterUI()
{
	var isExists = $('#DivTab').tabs('exists','出库登记');
	if (isExists){
		$('#DivTab').tabs('select','出库登记');
	} else{
		$('#DivTab').tabs('add',{title: '出库登记', href: './out/outRegster.jsp', closable: true});
	}
}

//出库审核
function outUw()
{
	var isExists = $('#DivTab').tabs('exists','出库审核');
	if (isExists){
		$('#DivTab').tabs('select','出库审核');
	} else{
		$('#DivTab').tabs('add',{title: '出库审核', href: './out/outUw.jsp', closable: true});
	}
}

//入库及打印凭证
function confOutPrint()
{
	var isExists = $('#DivTab').tabs('exists','出入库及打印凭证');
	if (isExists){
		$('#DivTab').tabs('select','出入库及打印凭证');
	} else{
		$('#DivTab').tabs('add',{title: '出入库及打印凭证', href: './out/conf_print.html', closable: true});
	}
}

//出库登记相关
function outrg_search() {
	var tcomcode =$('#outrg_comcode').combobox('getValue');
	var tincf_engineno= $('#outrg_engineno').val();
	var tincf_startdate =$('#outrg_startdate').datebox('getValue');
	var tincf_enddate = $('#outrg_enddate').datebox('getValue');
	var tUrl = " ./outdataquery.form?qtype=out_df&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#outrg_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}

function out_regster_submit() {
	var tEngineNo = $('#orEngineNo').val();
	var tVIN = $('#orVIN').val();
	if (tEngineNo== null || tEngineNo == '') {
		$.messager.alert("发动机编码不允许为空");
		return;
	}else	if (tVIN== null || tVIN == '') {
		$.messager.alert("机架号不允许为空");
		return;
	} 
	$(document).ready(function() {
		var options = {
			url : 'outRegster.form',
			success : function(data) {
				$.messager.alert('出库登记',data);
				initOutRegsterGrid();
				$('#outrg_fm').clearForm();
			}
		}
		$('#outrg_fm').ajaxSubmit(options);
	});
}

function initOutRegsterGrid() {
	$(document).ready(function() {
	    $('#outrg_dg').datagrid({
	    	method: 'get', 
	    	url:'./outdataquery.form?qtype=out_df'
	    });   
	})
}
 
function ou_submit() {
	$(document).ready(function() {
		var options = {
			url : 'outuw.form',
			success : function(data) {
				$.messager.alert('出库审核',data);
				initOutUwGrid();
			}
		}
		$('#outuw_form').ajaxSubmit(options);
	});
	$('#outuw_form').clearForm();
}

function initOutUwGrid() {
	$(document).ready(function() {
	    $('#outuw_dg').datagrid({
	    	method: 'get', 
	    	url:'./outdataquery.form?qtype=out_uw'
	    });   
	})
}