//入库登记
function inRgterUI()
{
	var isExists = $('#DivTab').tabs('exists','入库登记');
	if (isExists){
		$('#DivTab').tabs('select','入库登记');
	} else{
		$('#DivTab').tabs('add',{title: '入库登记', href: './in/in_register.jsp', closable: true});
	}
}

//入库清单
function inUw()
{
	var isExists = $('#DivTab').tabs('exists','入库审核');
	if (isExists){
		$('#DivTab').tabs('select','入库审核');
	} else{
		$('#DivTab').tabs('add',{title: '入库审核', href: './in/in_uw.jsp', closable: true});
	}
}

//入库及打印凭证
function confInPrint()
{
	var isExists = $('#DivTab').tabs('exists','入库及打印凭证');
	if (isExists){
		$('#DivTab').tabs('select','入库及打印凭证');
	} else{
		$('#DivTab').tabs('add',{title: '入库及打印凭证', href: './in/inConPrint.jsp', closable: true});
	}
}

//批量入库及打印凭证
function batchregster()
{
	var isExists = $('#DivTab').tabs('exists','批量入库');
	if (isExists){
		$('#DivTab').tabs('select','批量入库');
	} else{
		$('#DivTab').tabs('add',{title: '批量入库', href: './in/inBatchRegster.jsp', closable: true});
	}
}

//入库登记相关 -------------------------------------------------------------------------------------------
function ir_submit() {
	var tEngineNo = $('#EngineNo').val();
	var tVIN = $('#VIN').val();
	if (tEngineNo== null || tEngineNo == '') {
		$.messager.alert("发动机编码不允许为空");
		return;
	}else	if (tVIN== null || tVIN == '') {
		$.messager.alert("机架号不允许为空");
		return;
	} 
	$(document).ready(function() {
		var options = {
			url : 'in_resiter.form',
			success : function(data) {
				$.messager.alert('入库登记',data);
				initRgsterGrid();
				$('#fm_in_save').clearForm();
			}
		}
		$('#fm_in_save').ajaxSubmit(options);
	});
	
}

function initRgsterGrid() {
	$(document).ready(function() {
	    $('#inrg_dg').datagrid({
	    	method: 'get', 
	    	url:'./dataquery.form?qtype=inRgster'
	    });   
	})
}
//入库登记相关结束

//入库审核相关开始--------------------------------------------------------------------------
function initUwGrid() {
	$(document).ready(function() {
	    $('#inuw_dg').datagrid({
	    	method: 'get', 
	    	url:'./dataquery.form?qtype=inuw'
	    });   
	})
}
function iu_submit() {
	$(document).ready(function() {
		var options = {
			url : 'inuw.form',
			success : function(data) {
				$.messager.alert('入库审核',data);
				initUwGrid();
			}
		}
		$('#inuw_form').ajaxSubmit(options);
	});
	$('#inuw_form').clearForm();
}

//入库审核相关结束

//入库确认相关开始----------------------------------------------------------------------------

function inConfGrid() {
	$(document).ready(function() {
	    $('#inconf_dg').datagrid({
	    	method: 'get', 
	    	url:'./dataquery.form?qtype=inconf'
	    });   
	})
}

function incf_search() {
	var tcomcode =$('#incf_comcode').combobox('getValue');
	var tincf_engineno= $('#incf_engineno').val();
	var tincf_startdate =$('#incf_startdate').datebox('getValue');
	var tincf_enddate = $('#incf_enddate').datebox('getValue');
	var tUrl = " ./dataquery.form?qtype=inconf&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#inconf_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}

function incf_print() {
	var tSerialNo = $('#inconf_serialno').val();
	if (tSerialNo == null || tSerialNo == '') {
		$.messager.alert('入库凭证打印','请选择一条数据');
		return;
	}
	 $('#inconf_form').attr('action','print.form?type=in');
	 $('#inconf_form').attr('target','_blank');
	 $('#inconf_form').submit();
}

function inconf_submit() {
	var tSerialNo = $('#inconf_serialno').val();
	if (tSerialNo == null || tSerialNo == '') {
		$.messager.alert('入库凭证打印','请选择一条数据');
		return;
	}
	$(document).ready(function() {
		var options = {
			url : 'inconf.form',
			success : function(data) {
				$.messager.alert('入库确认',data);
				incf_search();
			}
		}
		$('#inconf_form').ajaxSubmit(options);
	});
	$('#inconf_form').clearForm();
}

//入库确认相关结束----------------------------------------------------------------------------

//批量上传开始--------------------------------------------------------------------------------
function batchupdate() {
	$(document).ready(function() {
		var options = {
			url : 'upload.form',
			 type:'post',  
			 success : function(data) {
				$.messager.alert('批量入库',data);
			 }
		}
		$('#inbatch_form').ajaxSubmit(options);
	});
	$('#inbatch_form').clearForm();
}

//批量上传结束--------------------------------------------------------------------------------