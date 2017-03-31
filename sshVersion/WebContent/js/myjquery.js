$(document).ready(
		function() {
			/* 找到需要填充的div，使用id选择器 */
			// 需要填充的二级分类名称的块
			var fmcOut = $("tr#rightUpSide");
			// 需要填充的食物原料名称的块
			var fmOut = $("#rightDownSide");
			// 需要填充的食物原料详细信息的块
			var fmDetailsOut = $("#rightMiddleContent");

			/* 为每个一级分类名称的a 绑定click事件 clickf */
			$("a.firstCategory").each(function() {
				$(this).bind("click", clickf);
			});
			
			// 为查找模块的submit按钮，绑定click事件 findFm
			$("#findFM").bind("click", findFm);
			function findFm() {
				fmOut.html("");
				$.post("/sshVersion/foodMaterialAction_findFM", {
					name : $("#fmName").val(),
					min : $("#calorieMin").val(),
					max : $("#calorieMax").val()
				}, fmData, "xml");/*返回数据为html格式是否可以？这样就可以直接填充进去了*/
			}/* findFm程序结束 */

			// 为每个食材名称的a，绑定click事件 findFmDetails

			/* clickf事件的逻辑 */
			function clickf() {
				/* 首先清空之前div已经填充的数据 */
				fmcOut.html("");
				fmOut.html("");
				/* 发送xhr请求，参数为id=当前a的id中的值 */
				/* 返回数据为xml格式，使用jquery的dom解析器，取出数据后拼接成html元素，追加到需要填充的div中 */
				$.post("/sshVersion/foodMaterialAction_listSecondCategory", {
					id : $(this).attr("id")
				}, function(data) {
					var jqueryObj = $(data);
					var fms = jqueryObj.find("foodMaterialCategory");
					fms.each(function() {
						var fmc = $(this);
						var td = $("<td>");
						$("<a>").attr("id", fmc.find("id").text()).attr("href",
								"#").attr("class", "fm").html(
								fmc.find("name").text()).appendTo(td);
						td.appendTo(fmcOut);
					});
//					为每个一级分类通过ajax返回的二级分类名称，添加click事件 clickf2
					secondAjax();				
				}, "xml");/*post的回调函数执行完毕*/
			}/* clickf程序结束 */

			function secondAjax() {
				$("a.fm").each(function() {
					$(this).bind("click", clickf2);
				});	
			}/* secondAjax结束 */
			function clickf2() {
				fmOut.html("");
				$.post("/sshVersion/foodMaterialAction_listPage", {
					id : $(this).attr("id"),currentpage:$(this).attr("title")
				}, fmPageData, "xml");
			}/* clickf2结束 */
			
			function fmPageData(data){
				var jqueryObj = $(data);
				var fm = jqueryObj.find("foodMaterial");
				var page= jqueryObj.find("page");
				
				fm.each(function() {
					var fm = $(this);
					$("<li>").append($("<a>").attr("id", fm.find("id").text()).html(
							fm.find("name").text()).attr("class","fmDetail").attr("href","#")).appendTo(fmOut);
				});	
				$("<span>").append($("<td>").html("当前页："+page.find("currentPage").text())).
				append($("<td>").html("总条数："+page.find("totalCount").text())).append($("<td>").
						append($("<a>").html("下一页").attr("id",jqueryObj.find("categoryId").text()).attr("title",page.find("nextPage").text()).
								attr("class","nextPage").attr("href","#")))
				.appendTo(fmOut);
				$("a.nextPage").bind("click",clickf2)
				fmDetailsAjax();
			}

			function fmData(data) {
				var jqueryObj = $(data);
				var fm = jqueryObj.find("foodMaterial");
				fm.each(function() {
					var fm = $(this);
					$("<li>").append($("<a>").attr("id", fm.find("id").text()).html(
							fm.find("name").text()).attr("class","fmDetail").attr("href","#")).appendTo(fmOut);
				});		
				fmDetailsAjax();
			}/* fmData函数定义结束 */
			
//			为每个食材名称的a绑定click事件
			function fmDetailsAjax(){
				$("a.fmDetail").each(function(){
					$(this).bind("click",fmDetail)
				});
			}/*为每个食材绑定点击事件的fmDetailsAjax函数结束*/
				
				function fmDetail(){
					fmDetailsOut.html("");
					$.post("/sshVersion/foodMaterialAction_fMDetail",{id:$(this).attr("id")},
					function(data){
						var jqueryObj = $(data);
						var fm = jqueryObj.find("foodMaterial");
						var table =  $("<table>");
						$("<tr>").append($("<td>").html("name")).append($("<td>").html(fm.find("name").text())).appendTo(table);
						$("<tr>").append($("<td>").html("othername")).append($("<td>").html(fm.find("othername").text())).appendTo(table);
						$("<tr>").append($("<td>").html("calorie")).append($("<td>").html(fm.find("calorie").text())).appendTo(table);
						$("<tr>").append($("<td>").html("carbohydrate")).append($("<td>").html(fm.find("carbohydrate").text())).appendTo(table);
						$("<tr>").append($("<td>").html("protein")).append($("<td>").html(fm.find("protein").text())).appendTo(table);
						$("<tr>").append($("<td>").html("fat")).append($("<td>").html(fm.find("fat").text())).appendTo(table);
						table.appendTo(fmDetailsOut);
					},"xml");
				}/*click绑定的函数fmDetail()结束*/


		}/* 整个页面的function结束 */

);
