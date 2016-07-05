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
		$('#DivTab').tabs('add',{title: '出入库及打印凭证', href: './out/outConfPrint.jsp', closable: true});
	}
}

//出库登记相关
function outrg_search() {
	var tcomcode =$('#outrg_comcode').combobox('getValue');
	var tincf_engineno= $('#outrg_engineno').val();
	var tincf_startdate =$('#outrg_startdate').datebox('getValue');
	var tincf_enddate = $('#outrg_enddate').datebox('getValue');
	var tUrl = " ./outdataquery.form?qtype=out_rs&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#outrg_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}

function out_regster_submit() {
	if($('#or_paymode').is(':checked')) {
		var orMoney = $('#orMoney').val();
		if (orMoney == null || ''==orMoney) {
			$.messager.alert('出库登记','付款金额不允许为空');
			return;
		}
	} else {
		var tEngineNo = $('#orEngineNo').val();
		var tVIN = $('#orVIN').val();
		if (tEngineNo== null || tEngineNo == '') {
			$.messager.alert('出库登记','发动机编码不允许为空');
			return;
		}else	if (tVIN== null || tVIN == '') {
			$.messager.alert('出库登记','机架号不允许为空');
			return;
		} 
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
 
function payMode(chbox) {
	if($(chbox).is(':checked')) {
		$("#or_paymoney" ).css("display", "block");
		$("#or_paycar" ).css("display", "none"); 
	} else {
		$("#or_paymoney" ).css("display", "none");
		$("#or_paycar" ).css("display", "block"); 
	}
}

//出库审核相关开始--------------------------------------------------------------
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
//出库审核相关结束--------------------------------------------------------------

//入库确认相关开始----------------------------------------------------------------------------

function outConfGrid() {
	$(document).ready(function() {
	    $('#outconf_dg').datagrid({
	    	method: 'get', 
	    	url:'./outdataquery.form?qtype=outconf'
	    });   
	})
}

function outcf_search() {
	var tcomcode =$('#outcf_comcode').combobox('getValue');
	var tincf_engineno= $('#outcf_engineno').val();
	var tincf_startdate =$('#outcf_startdate').datebox('getValue');
	var tincf_enddate = $('#outcf_enddate').datebox('getValue');
	var tUrl = " ./outdataquery.form?qtype=outconf&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#outconf_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}

//out print
function outcf_print() {
	var tSerialNo = $('#outconf_serialno').val();
	if (tSerialNo == null || tSerialNo == '') {
		$.messager.alert('出库凭证打印','请选择一条数据');
		return;
	}
	 $('#outconf_form').attr('action','print.form?prttype=out');
	 $('#outconf_form').attr('target','_blank');
	 $('#outconf_form').submit();
}
//out in conf
function outconf_insubmit() {
	var tSerialNo = $('#outconf_serialno').val();
	if (tSerialNo == null || tSerialNo == '') {
		$.messager.alert('入库确认','请选择一条数据');
		return;
	}
	$(document).ready(function() {
		var options = {
			url : 'outconf.form?conftype=in',
			success : function(data) {
				$.messager.alert('入库确认',data);
				outcf_search();
			}
		}
		$('#outconf_form').ajaxSubmit(options);
	});
	$('#outconf_form').clearForm();
}
//out out conf
function outconf_outsubmit() {
	var tSerialNo = $('#outconf_serialno').val();
	if (tSerialNo == null || tSerialNo == '') {
		$.messager.alert('出库确认','请选择一条数据');
		return;
	}
	$(document).ready(function() {
		var options = {
			url : 'outconf.form?conftype=out',
			success : function(data) {
				$.messager.alert('出库确认',data);
				outcf_search();
			}
		}
		$('#outconf_form').ajaxSubmit(options);
	});
	$('#outconf_form').clearForm();
}

//入库确认相关结束----------------------------------------------------------------------------
