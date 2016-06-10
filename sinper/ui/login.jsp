<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0019)http://cnolnic.com/ -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>出入库管理系统-登录页面</title>
	<link href="./themes/login/login.css" rel="stylesheet" type="text/css">
	<script src="./js/jquery-1.12.4.js" type="text/javascript"></script>
	<script src="./js/login/core-min.js"></script>
	<script src="./js/login/md5.js"></script>
	<script>
		var   count=0;	
		function ReCode(){ 
			$("#img")[0].src="/cgi-bin/yanzhengma.cgi?"+Math.random();
		}
	function crypto()
	{
		//是否填写了用户名
		var username=$.trim($("[name=username]").val());
		var userpass=$.trim($("[name=userpass]").val());
		if(username=="")
	    {
			alert("请输入用户名！");
			$("[name=username]").focus();
			return false;
		}
		if(userpass=="")
	    {
			alert("请输入密码！");
			$("[name=userpass]").focus();
			return false;
		}
	    var yzm=$.trim($("[name=yanzhengma]").val());
		if(yzm=="")
		{
			alert("请输入验证码！");
			$("[name=yanzhengma]").focus();
			return false;
		}
	//判断验证码是否正确
	}
	
	function ts() {
	var ps = document.getElementById("userpass").value; 	   
	document.getElementById("userpass").disabled="disabled"; 
	document.getElementById("savepass").value = m1;     

	var host = window.location.hostname;
	if(host.indexOf("192.") == -1 && host != "test.cnonic.com" && host != "wtest.cnonic.com")
		$("#loginform").attr("action", "https://www.cnolnic.com/cgi-bin/userlogin.cgi");
}
</script>
		<style type="text/css"></style>
</head>

<body style="background: #fff;" onload="mouserFouce();">
	<div class="cn_login">
		<div class="cn_login_top">
			<img src='./images/logo/logo3.jpg' height='60%'
				data-options="region:'right'"> <img
				src='./images/logo/logo1.jpg' height='60%'
				data-options="region:'left'" />
		</div>
		<form action="login.form" method="post" id="loginform"
			name="loginform">
			<div class="cn_login_bg">
				<div class="bannerlogin">
					<div class="cn_login_con">
						<div class="cn_name">
							<span class="user_icon"> <input class="cn_input01"
								name="userId" id="userId" type="text" placeholder="用户名"></span>
						</div>
						<div class="cn_mima">
							<span class="psw_icon"> <input class="cn_input03"
								name="pwd" id="pwd" type="password" placeholder="密码"><input
								type="hidden" name="savepass" id="savepass"></span>
						</div>
						<div class="cn_authcode" id="yzmshow">
							<span class="yzm_icon"> <input class="cn_input02"
								type="text" name="yanzhengma" id="yanzhengma" placeholder="验证码">
									<img id="img" class="authcode_img" width="70" height="39"
									src="./js/login/yanzhengma.cgi" onclick="javascript:ReCode();"></span>
						</div>
						<div class="login_btn">
							<button class="cn_button" type="submit">登 录</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="cn_login_bottom">Copyright &#169; 2009 CNOLNIC.COM	Inc. All Rights Reserved</div>
	</div>


</body>
</html>