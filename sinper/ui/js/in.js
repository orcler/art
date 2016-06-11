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
		$('#DivTab').tabs('add',{title: '入库及打印凭证', href: './in/conf_print.html', closable: true});
	}
}

//in_register subimt
function submitForm() {
	var tEngineNo = $('#EngineNo').val();
	var tVIN = $('#VIN').val();
	if (tEngineNo== null || tEngineNo == '') {
		alert("发动机编码不允许为空");
		return;
	}else	if (tVIN== null || tVIN == '') {
		alert("机架号不允许为空");
		return;
	} 
	$(document).ready(function() {
		var options = {
			url : 'in_resiter.form',
			success : function(data) {
				$.messager.alert(data);
			}
		}
		$('#fm_in_save').ajaxSubmit(options);
	});
	$('#fm_in_save').clearForm();
}


