$(document).ready(function(){
/*	找到需要填充的div，使用id选择器*/
	var fmout = $("#fm");
/*	为每个a 绑定click事件*/
	$("a").each(function(){
		$(this).bind("click",clickf);
	});
/*	click事件的逻辑*/
	function clickf(){
		/*首先清空之前div已经填充的数据*/
		fmout.html("");
		/*发送xhr请求，参数为id=当前a的id中的值*/
		/*返回数据为xml格式，使用jquery的dom解析器，取出数据后拼接成html元素，追加到需要填充的div中*/
		$.post("/sshVersion/recipeAction_listAll",{id:$(this).attr("id")},function(data){
			var jqueryObj = $(data);
			
			var fms = jqueryObj.find("recipe");
			fms.each(function(){
				var fm = $(this);
				$("<div>").html(fm.find("name").text()+":"+fm.find("foodMaterial").text()).appendTo(fmout);
			});

	},"xml");
	}	

});



