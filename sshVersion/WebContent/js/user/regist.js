$(function(){
//     1、找到所有的错误信息，循环遍历，调用showError方法确定是否显示错误信息；
		$(".errorClass").each(function(){
			showError($(this));
		});
		
//		2、切换注册按钮的图片
		$("#submitBtn").hover(
				function(){
					$("#submitBtn").attr("src","/sshVersion/images/regist2.jpg");
				},/*第一个参数进入时被调用*/
				function(){
					$("#submitBtn").attr("src","/sshVersion/images/regist1.jpg");
				}/*第二个参数鼠标离开时被调用*/
		);
		
//		3、输入框得到焦点隐藏错误信息
		$(".inputClass").focus(function(){
			var labelId = $(this).attr("id") + "Error";  //通过输入框id找到labelId
			$("#" + labelId).text("");
			showError($(("#")+labelId));
		});
		
//		4、输入框失去焦点进行校验
		$(".inputClass").blur(function(){
			var id = $(this).attr("id");
			var mName = "validate" + id.substring(0,1).toUpperCase() + id.substring(1) + "()";
			eval(mName); //eval中的字符串会被当作代码来处理
		});	
		
//		5、提交表单对数据进行校验
		$("#registForm").submit(function(){
			var bool = true;
			
			if(!validateLoginname()){
				bool = false;
			}
			if(!validateLoginpass()){
				bool = false;
			}
			if(!validateReloginpass()){
				bool=false;
			}
			if(!validateEmail()){
				bool = false;
			}
			if(!validateVerifycode()){
				bool = false;
			}
			return bool;
		})
});
//登录名校验方法
function validateLoginname(){
		var id = "loginname";
		var value = $("#" + id).val();
		if(!value){
			$("#" + id + "Error").text("用户名不能为空！");
			showError($("#" + id + "Error"));
			return false;/*return false 表示在此拦住页面？*/
		}
		if(value.length < 2 || value.length > 20){
			$("#" + id + "Error").text("用户名长度必须在3~20之间！");
			showError($("#" + id + "Error"));
			return false;
		}
		$.ajax({url:"/sshVersion/userAction_ajaxValidateLoginName",
			data:{loginname:value},
			type:"POST",
			dataType:"json",
			async:false,
			cache:false,
			success:function(result){
				if(!result){
					$("#" + id + "Error").text("用户名已被注册！");
					showError($("#" + id + "Error"));
					return false;
				}
			}
		});
		return true;
}
//登录密码校验方法
function validateLoginpass(){
	var id = "loginpass";
	var value = $("#" + id).val();
	if(!value){
		$("#" + id + "Error").text("密码不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	if(value.length < 6 || value.length > 20){
		$("#" + id + "Error").text("密码长度必须在6~20之间！");
		showError($("#" + id + "Error"));
		false;
	}
	return true;
}
//确认密码校验方法
function validateReloginpass(){
	var id = "reloginpass";
	var value = $("#" + id).val();
	if(!value){
		$("#" + id + "Error").text("确认密码不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	if(value != $("#loginpass").val()){
		$("#" + id + "Error").text("确认密码必须同登录密码一致！");
		showError($("#" + id + "Error"));
		false;
	}
	return true;
}

//Email校验方法
function validateEmail(){
	var id = "email";
	var value = $("#" + id).val();
	if(!value){
		$("#" + id + "Error").text("邮箱不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	if(!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value)){
		$("#" + id + "Error").text("错误的Email格式！");
		showError($("#" + id + "Error"));
		return false;
	}
	
	$.ajax({url:"/sshVersion/userAction_ajaxValidateEmail",
		data:{email:value},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(result){
			if(!result){
				$("#" + id + "Error").text("邮箱已被注册！");
				showError($("#" + id + "Error"));
				false;
			}
		}
	});
	return true;
}

//验证码校验方法
function validateVerifycode(){
	var id = "verifycode";
	var value = $("#" + id).val();
	if(!value){
		$("#" + id + "Error").text("验证码不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	if(value.length != 4){
		$("#" + id + "Error").text("验证码位数不对！");
		showError($("#" + id + "Error"));
		return false;
	}
	$.ajax({url:"/sshVersion/userAction_ajaxValidateVerifyCode",
		data:{verifyCode:value},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(result){
			if(!result){
				$("#" + id + "Error").text("验证码不正确！");
				showError($("#" + id + "Error"));
				false;
			}
		}
	});
	return true;
}

//判断元素中有内容，显示此元素，没有内容，不显示
function showError(ele){
	var text = ele.text();
	if(!text){
		ele.css("display","none");
	}else{
		ele.css("display","");
	}
}

//换一张验证码
function _hyz(){
	$("#imgVerifyCode").attr("src","/sshVersion/verifyCodeAction?a=" + new Date().getTime());
}

