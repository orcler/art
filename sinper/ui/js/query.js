
//入库清单
//出库清单
function inQuery()
{
	alert('abc');
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
		$('#DivTab').tabs('add',{title: '出库查询', href: './query/out_query.html', closable: true});
	}
}

//出库清单
function existsQuery()
{
	var isExists = $('#DivTab').tabs('exists','库存查询');
	if (isExists){
		$('#DivTab').tabs('select','库存查询');
	} else{
		$('#DivTab').tabs('add',{title: '库存查询', href: './query/exists_query.html', closable: true});
	}
}