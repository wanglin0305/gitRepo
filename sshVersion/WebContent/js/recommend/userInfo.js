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
			var labelId = $(this).attr("name") + "Error";  //通过输入框id找到labelId
			$("#" + labelId).text("");
			showError($(("#")+labelId));
		});
		
//		4、输入框失去焦点进行校验
		$(".inputClass").blur(function(){
			var id = $(this).attr("name");
			var mName = "validate" + id.substring(0,1).toUpperCase() + id.substring(1) + "()";
			eval(mName); //eval中的字符串会被当作代码来处理
		});	
		
//		5、提交表单对数据进行校验
		$("#submitBtn").click(function(){
			var bool = true;
			
			if(!validateAge()){
				bool = false;
			}
			if(!validateWeight()){
				bool = false;
			}
			if(!validateSex()){
				bool=false;
			}
			if(!validateWork()){
				bool = false;
			}
			if(!validatePregnant()){
				bool = false;
			}
			if(!validateLactation()){
				bool = false;
			}
			if(!validateVerifycode()){
				bool = false;
			}
			alert(bool);
			return bool;
		})
});

//年龄校验方法
function validateAge(){
	var id = "age";
	var value = $("#" + id).val();
	if(!value){
		$("#" + id + "Error").text("年龄不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	var ex = /^\d+$/;
	if(!ex.test(value)){
		$("#" + id + "Error").text("年龄请输入正整数！");
		showError($("#" + id + "Error"));
		return false;
	}
	return true;
}
//体重校验方法
function validateWeight(){
	var id = "weight";
	var value = $("#" + id).val();
	if(!value){
		$("#" + id + "Error").text("体重不能为空！");
		showError($("#" + id + "Error"));
		return false;
	}
	var ex = /^\d+$/;
	if(!ex.test(value)){
		$("#" + id + "Error").text("体重请输入正整数！");
		showError($("#" + id + "Error"));
		false;
	}
	return true;
}

//性别校验方法
function validateSex(){
	var id="sex";
	var value = $("input:radio[name='sex']:checked").val();
	if(!value){
		$("#" + id + "Error").text("请选择性别！");
		showError($("#" + id + "Error"));
		return false;
	}
	return true;
}
//工作性质校验方法
function validateWork(){
	var id="work";
	var value = $('input:radio[name="work"]:checked').val();
	if(!value){
		$("#" + id + "Error").text("请选择工作性质！");
		showError($("#" + id + "Error"));
		return false;
	}
	return true;
}

//怀孕校验方法
function validatePregnant(){
	var id="pregnant";
	var value = $('input:radio[name="pregnant"]:checked').val();
	if(!value){
		$("#" + id + "Error").text("请选择是否怀孕！");
		showError($("#" + id + "Error"));
		return false;
	}
	return true;
}

//哺乳期校验方法
function validateLactation(){
	var id="lactation";
	var value = $('input:radio[name="lactation"]:checked').val();
	if(!value){
		$("#" + id + "Error").text("请选择是否处于哺乳期！");
		showError($("#" + id + "Error"));
		return false;
	}
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

