<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<span id=ajaxarea>ajax 값</span>
	<br>
	<button id=ajax>ajax 실행</button>
	<script>
	// jQuery 활용
	$("#ajax").click("on", function(){
		$.ajax({ // $.ajax : ajax를 쓰겠다는 뜻 
			url : "json/example",
			type : "post",
			data : {userId : "rudy", "${_csrf.parameterName}" : "${_csrf.token}"},
			dataType : "text",
			success : function(result){
				$("#ajaxarea").text(result);
			},
			error : function(e){
				console.log(e.statusText);
			}
		});
		//.ajaxStart(function(){
			
		//});
		//.ajaxStop(function(){
			
		//});
	});
	
	// DOM 활용
	/*
		$("#ajax").click("on", function(){
			var xhr = new XMLHttpRequest();
			var setArea = function(word){
				document.getElementById("ajaxarea").innerText = word;
			}
			xhr.open('get', 'json/example?userId=rudy');
			xhr.setRequestHeader("content-type", "application/json");
			xhr.setRequestHeader("${_csrf.headerName}",
			"${_csrf.token}");
			xhr.send(); //post 보낼 객체 혹은 데이터가 다 들어가는 곳
			xhr.onreadystatechange = function(){
				if(xhr.readyState === xhr.LOADING){
					setArea("로딩중...");
				}
				if(xhr.readyState === xhr.DONE){
					if(xhr.status === 200 || xhr.sataus === 201){
						setTimeout(function(){setArea(xhr.responseText)},5000);
					}
				}
			}
		});
	*/
	
	/** get 방식
		$("#ajax").click("on", function(){
			var xhr = new XMLHttpRequest();
			var setArea = function(word){
				document.getElementById("ajaxarea").innerText = word;
			}
			xhr.open('get', 'json/example?userId=rudy');
			xhr.setRequestHeader("content-type", "application/json");
			xhr.setRequestHeader("${_csrf.headerName}",
			"${_csrf.token}");
			xhr.send(); //post 보낼 객체 혹은 데이터가 다 들어가는 곳
			xhr.onreadystatechange = function(){
				if(xhr.readyState === xhr.DONE){
					if(xhr.status === 200 || xhr.sataus === 201){
						setArea(xhr.responseText); 
					}
				}
			}
		});
	**/
	</script>
</body>
</html>