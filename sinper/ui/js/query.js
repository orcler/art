
//入库清单
function inQuery()
{
	var isExists = $('#DivTab').tabs('exists','入库查询');
	if (isExists){
		$('#DivTab').tabs('select','入库查询');
	} else{
		$('#DivTab').tabs('add',{title: '入库查询', href: './query/inQuery.jsp', closable: true});
	}
}

//出库清单
function outQuery()
{
	var isExists = $('#DivTab').tabs('exists','出库查询');
	if (isExists){
		$('#DivTab').tabs('select','出库查询');
	} else{
		$('#DivTab').tabs('add',{title: '出库查询', href: './query/outQuery.jsp', closable: true});
	}
}
//库存查询
function existsQuery()
{
	var isExists = $('#DivTab').tabs('exists','库存查询');
	if (isExists){
		$('#DivTab').tabs('select','库存查询');
	} else{
		$('#DivTab').tabs('add',{title: '库存查询', href: './query/existsQuery.jsp', closable: true});
	}
}
//库存盘点申请
function invertApply()
{
	var isExists = $('#DivTab').tabs('exists','库存盘点');
	if (isExists){
		$('#DivTab').tabs('select','库存盘点');
	} else{
		$('#DivTab').tabs('add',{title: '库存盘点', href: './query/InvertApply.jsp', closable: true});
	}
}
//库存照片上传
function invertUpload()
{
	var isExists = $('#DivTab').tabs('exists','盘点上传');
	if (isExists){
		$('#DivTab').tabs('select','盘点上传');
	} else{
		$('#DivTab').tabs('add',{title: '盘点上传', href: './query/InvertUpload.jsp', closable: true});
	}
}
//盘点确认
function invertConf()
{
	var isExists = $('#DivTab').tabs('exists','盘点确认');
	if (isExists){
		$('#DivTab').tabs('select','盘点确认');
	} else{
		$('#DivTab').tabs('add',{title: '盘点确认', href: './query/InvertConf.jsp', closable: true});
	}
}
//入库查询开始--------------------------------------------------------------------------------
function inquery_search() {
	var tcomcode =$('#inquery_comcode').combobox('getValue');
	var tincf_engineno= $('#inquery_engineno').val();
	var tincf_startdate =$('#inquery_startdate').datebox('getValue');
	var tincf_enddate = $('#inquery_enddate').datebox('getValue');
	var tUrl = "./dataquery.form?qtype=inquery&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#inquery_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}
//入库查询结束----------------------------------------------------------------------------
//出库查询开始--------------------------------------------------------------------------------
function outquery_search() {
	var tcomcode =$('#inquery_comcode').combobox('getValue');
	var tincf_engineno= $('#outquery_engineno').val();
	var tincf_startdate =$('#outquery_startdate').datebox('getValue');
	var tincf_enddate = $('#outquery_enddate').datebox('getValue');
	var tUrl = "./dataquery.form?qtype=outquery&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#outquery_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}
//出库查询结束----------------------------------------------------------------------------
//库存查询开始--------------------------------------------------------------------------------
function extquery_search() {
	var tcomcode =$('#extquery_comcode').combobox('getValue');
	var tincf_engineno= $('#extquery_engineno').val();
	var tincf_startdate =$('#extquery_startdate').datebox('getValue');
	var tincf_enddate = $('#extquery_enddate').datebox('getValue');
	var tUrl = "./dataquery.form?qtype=extquery&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#extquery_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}
//库存查询结束----------------------------------------------------------------------------
//库存盘点申请开始------------------------------------------------------------------------
function ivtapp_search() {
	var tcomcode =$('#ivtapp_comcode').combobox('getValue');
	var tincf_engineno= $('#ivtapp_engineno').val();
	var tincf_startdate =$('#ivtapp_startdate').datebox('getValue');
	var tincf_enddate = $('#ivtapp_enddate').datebox('getValue');
	var tUrl = "./invertdata.form?qtype=app&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#ivtapp_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}

function ivtapp_submit() {
	$(document).ready(function() {
		var options = {
			url : 'invertapp.form?type=app',
			success : function(data) {
				$.messager.alert('库存盘点',data);
				ivtapp_search();
			}
		}
		$('#ivtapp_form').ajaxSubmit(options);
	});
	$('#ivtapp_form').clearForm();
}
//库存盘点申请结束------------------------------------------------------------------------

//库存盘点上传开始------------------------------------------------------------------------
function ivtupload_search() {
	var tcomcode =$('#ivtupload_comcode').combobox('getValue');
	var tincf_engineno= $('#ivtupload_engineno').val();
	var tincf_startdate =$('#ivtupload_startdate').datebox('getValue');
	var tincf_enddate = $('#ivtupload_enddate').datebox('getValue');
	var tUrl = "./invertdata.form?qtype=upload&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#ivtupload_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}

function ivtupload() {
	var tSerialno = $('#ivtupload_serialno').val();
	$(document).ready(function() {
		var options = {
			url : 'invertupload.form?ivtupload_serialno=' + tSerialno,
			 type:'post',  
			 success : function(data) {
				$.messager.alert('盘点上传',data);
			 }
		}
		$('#ivtupload_form').ajaxSubmit(options);
	});
	$('#ivtupload_form').clearForm();
}

function ivtupload_submit() {
	$(document).ready(function() {
		var options = {
			url : 'invertapp.form?type=upload',
			success : function(data) {
				$.messager.alert('库存盘点',data);
				ivtupload_search();
			}
		}
		$('#ivtupload_submit_form').ajaxSubmit(options);
	});
	$('#ivtupload_submit_form').clearForm();
}
//库存盘点上传结束------------------------------------------------------------------------

//库存盘点确认开始------------------------------------------------------------------------
function ivtconf_search() {
	var tcomcode =$('#ivtconf_comcode').combobox('getValue');
	var tincf_engineno= $('#ivtconf_engineno').val();
	var tincf_startdate =$('#ivtconf_startdate').datebox('getValue');
	var tincf_enddate = $('#ivtconf_enddate').datebox('getValue');
	var tUrl = "./invertdata.form?qtype=conf&comcode=" + tcomcode + "&enginno=" + tincf_engineno + "&startdate=" + tincf_startdate + "&enddate=" + tincf_enddate;
	$(document).ready(function() {
	    $('#ivtconf_dg').datagrid({
	    	method: 'get', 
	    	url:tUrl
	    });   
	})
}

function ivtconf_show() {
	$(document).ready(function() {
		var options = {
			url : 'invertapp.form?type=show',
			success : function(data) {
				$('#showimg').html(data);
			}
		}
		$('#ivtconf_form').ajaxSubmit(options);
	});
	$('#ivtconf_form').clearForm();
}

function ivtconf_submit() {
	$(document).ready(function() {
		var options = {
			url : 'invertapp.form?type=conf',
			success : function(data) {
				$.messager.alert('库存盘点',data);
				ivtconf_search();
			}
		}
		$('#ivtconf_form').ajaxSubmit(options);
	});
	$('#ivtconf_form').clearForm();
	$('#showimg').html('');
}
//库存盘点确认结束------------------------------------------------------------------------