//入库登记
function addUser()
{
	var isExists = $('#DivTab').tabs('exists','新增用户');
	if (isExists){
		$('#DivTab').tabs('select','新增用户');
	} else{
		$('#DivTab').tabs('add',{title: '新增用户', href: './mager/add_user.html', closable: true});
	}
}

//入库清单
function addRole()
{
	var isExists = $('#DivTab').tabs('exists','赋于权限');
	if (isExists){
		$('#DivTab').tabs('select','赋于权限');
	} else{
		$('#DivTab').tabs('add',{title: '赋于权限', href: './mager/grant_role.html', closable: true});
	}
}

/* $(function(){
	$('.easyui-combobox').combo();
	changeAnimation('show');
}) */
/*权限管理*/
