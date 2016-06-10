//出库登记
function outRgterUI()
{
	var isExists = $('#DivTab').tabs('exists','出库登记');
	if (isExists){
		$('#DivTab').tabs('select','出库登记');
	} else{
		$('#DivTab').tabs('add',{title: '出库登记', href: './out/out_register.html', closable: true});
	}
}

//出库审核
function outUw()
{
	var isExists = $('#DivTab').tabs('exists','出库审核');
	if (isExists){
		$('#DivTab').tabs('select','出库审核');
	} else{
		$('#DivTab').tabs('add',{title: '出库审核', href: './out/out_uw.html', closable: true});
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