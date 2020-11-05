<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<span id=ajaxarea>ajax 값</span>
	<br>
	<button id=btn1>XMLHttpRequest(get) json 전송</button>
	<button id=btn2>jQuery(get) json 전송</button>
	<button id=btn3>XMLHttpRequest(post) json 전송</button>
	<button id=btn4>jQuery(post) 전송</button>
	<button id=btn5>jQuery2(post) 전송</button>

	<div id=result></div>

	<script>

// XMLHTTPRequest 이용 get방식
$("#btn1").on("click",function(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState === xhr.DONE){
			if(xhr.status === 200 || xhr.status === 201){
				document.getElementById("result").innerText=xhr.responseText;
			}
		}
	}
	xhr.open('get','json/ex?name=EFG&age=20');
	xhr.setRequestHeader('content-type', 'application/json');
	xhr.send();
});

// jQuery 이용 get방식
var jsonVO = {name:"Rudy",age:24};
$("#btn2").on("click",function(){
	$.ajax({
		url : "json/ex",
		type : "get",
		data : jsonVO,
		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		success : function(result){
			$("#result").text(result);
		},
		error : function(error){
			alert(error.statusText);
		}
	});
});

// XMLHTTPRequest 이용 post방식
var jsonVO = {name:"김연우",age=24};
$("#btn3").on("click",function(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState === xhr.DONE){
			if(xhr.status === 200 || xhr.status === 201){
				document.getElementById("result").innerText=xhr.responseText;
			}
		}
	}
	xhr.open('post');
	xhr.setRequestHeader('content-type', 'application/json');
	xhr.send(JSON.stringify(jsonVO));
});

// jQuery 이용 post방식
$("#btn4").click("on", function(){
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
});

// jQuery 이용 post방식2
$("#btn5").click("on", function(){
	$.ajax({ // $.ajax : ajax를 쓰겠다는 뜻 
		url : "json/example",
		type : "post",
		data : jsonVO,
		dataType : "text",
		success : function(result){
			$("#ajaxarea").text(result);
		},
		error : function(e){
			console.log(e.statusText);
		}
	});
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

/**
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