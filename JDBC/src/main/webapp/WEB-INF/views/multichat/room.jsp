<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chatting room</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
</head>
<body>
	<input type=text id=nickname placeholder="닉네임" required autofocus>
	<button id=inputnickname>확인</button>
	방 번호 : ${room.roomId}<br>
	방 이름 : ${room.roomName}<br>
	<span id=size>방 인원 : ${size}</span><br>
	<div id="chatroom" style="width:500px; height:600px; border:1px solid; border : 1px solid black"></div>
	<input type=text id=message style="width:200px; height:60px" placeholder="채팅을 입력하세요." autofocus>
	<button id=send>전송</button><br>
	<button id=exit>나가기</button>
	
	<script>
		var ws;
		var nickname;
		/* <![CDATA[*/
		var roomId = ${room.roomId};
		/*]]> */
		
		$("#inputnickname").on("click", function(){
			nickname = $("#nickname").val();
			$("#nickname").hide();
			$("#inputnickname").hide();
			ws = new WebSocket("ws://"+location.host+"<c:url value='/multichat/chat.do/websocket' />");
		})
		
	</script>
	
	
</body>
</html>