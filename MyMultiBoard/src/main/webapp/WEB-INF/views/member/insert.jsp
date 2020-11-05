<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member ${message eq 'insert' ? 'insert' : 'update'}</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
	#loadingbar{
		position : absolut;
		left : 50%;
		top : 50%;
		background : #ffffff;
	}
</style>
</head>
<body>
<h2>회원 정보 ${message eq 'insert' ? '입력' : '수정'}</h2>
<form action="/myapp/member/${message eq 'insert' ? 'insert' : 'update'}" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table>
<tr>
<td>아이디</td>
<td><input type=text id="userId" name=userId value="${mem.userId}" ${!empty mem ? 'readonly' : ''}></td>
<td><button type="submit" id="idCheck">중복검사</button></td>
</tr>
<tr>
<td>비밀번호</td><td><input type=password name=password></td>
</tr>
<tr>
<td>이름</td><td><input type=text name=userName value="${mem.userName}"></td>
</tr>
<tr>
<td>이메일</td><td><input type=text id="email" name=email value="${mem.email}" ${!empty mem ? 'readonly' : ''}></td>
<td><button type="submit" id="emailCheck">중복검사</button></td>
</tr>
<tr>
<td>전화번호</td><td><input type=text id="phone" name=phone value="${mem.phone}" ${!empty mem ? 'readonly' : ''}></td>
<td><button type="submit" id="phoneCheck">중복검사</button></td>
</tr>
<tr>
<td>닉네임</td><td><input type=text id="nickname" name=nickname value="${mem.nickname}" ${!empty mem ? 'readonly' : ''}></td>
<td><button type="submit" id="nicknameCheck">중복검사</button></td>
</tr>
</table>
<input type=submit value="${message eq 'insert' ? '입력' : '수정'}" onclick="return check();"><input type=reset value=취소>
</form>
<div id="loadingbar"><img src="../resources/images/loading.gif"/></div>

<script>
	var ck = false;
	var emailCk = false;
	var phoneCk = false;
	var nicknameCk = false;
	
	$(function() {
		$("#loadingbar").hide();
		$("#idCheck").on("click", function() {
			if ($("#userId").val()) {
				$.ajax({
					async : 'true',
					url : "insert/idCheck",
					type : "post",
					data : {
						userId : $("#userId").val(),
						"${_csrf.parameterName}" : "${_csrf.token}"
					},
					dataType : "json",
					success : function(check) {
						if (!check) {
							alert("중복되지 않는 아이디입니다.");
							$("#idCheck").remove();
							$("#userId").attr("readonly", true);
							ck = !ck;
						} else {
							alert("중복됩니다. 아이디를 다시 입력해 주세요.");
						}
						return false;
					},
					error : function() {
						alert("시간이 너무 오래 걸립니다. 다시 시도해 주세요.");
						return false;
					}
				});
			} else{
				alert("아이디를 입력하세요.");
			}
			return false;
		});
		
		$("#emailCheck").on("click", function() {
			if ($("#email").val()) {
				$.ajax({
					async : 'true',
					url : "insert/emailCheck",
					type : "post",
					data : {
						email : $("#email").val(),
						"${_csrf.parameterName}" : "${_csrf.token}"
					},
					dataType : "json",
					success : function(check) {
						if (!check) {
							alert("중복되지 않는 이메일입니다.");
							$("#emailCheck").remove();
							$("#email").attr("readonly", true);
							emailCk = !emailCk;
						} else {
							alert("중복됩니다. 이메일을 다시 입력해 주세요.");
						}
						return false;
					},
					error : function() {
						alert("시간이 너무 오래 걸립니다. 다시 시도해 주세요.");
						return false;
					}
				});
			} else{
				alert("이메일을 입력하세요.");
			}
			return false;
		});
		
		$("#phoneCheck").on("click", function() {
			if ($("#phone").val()) {
				$.ajax({
					async : 'true',
					url : "insert/phoneCheck",
					type : "post",
					data : {
						phone : $("#phone").val(),
						"${_csrf.parameterName}" : "${_csrf.token}"
					},
					dataType : "json",
					success : function(check) {
						if (!check) {
							alert("중복되지 않는 전화번호입니다.");
							$("#phoneCheck").remove();
							$("#phone").attr("readonly", true);
							phoneCk = !phoneCk;
						} else {
							alert("중복됩니다. 전화번호를 다시 입력해 주세요.");
						}
						return false;
					},
					error : function() {
						alert("시간이 너무 오래 걸립니다. 다시 시도해 주세요.");
						return false;
					}
				});
			} else{
				alert("전화번호를 입력하세요.");
			}
			return false;
		});
		
		$("#nicknameCheck").on("click", function() {
			if ($("#nickname").val()) {
				$.ajax({
					async : 'true',
					url : "insert/nicknameCheck",
					type : "post",
					data : {
						nickname : $("#nickname").val(),
						"${_csrf.parameterName}" : "${_csrf.token}"
					},
					dataType : "json",
					success : function(check) {
						if (!check) {
							alert("중복되지 않는 아이디입니다.");
							$("#nicknameCheck").remove();
							$("#nickname").attr("readonly", true);
							nicknameCk = !nicknameCk;
						} else {
							alert("중복됩니다. 닉네임을 다시 입력해 주세요.");
						}
						return false;
					},
					error : function() {
						alert("시간이 너무 오래 걸립니다. 다시 시도해 주세요.");
						return false;
					}
				});
			} else{
				alert("닉네임을 입력하세요.");
			}
			return false;
		});
		
		
	})
	
	.ajaxStart(function(){
		$("#loadingbar").show();
	})
	.ajaxStop(function(){
		$("#loadingbar").hide();
	})
	
	function check(){
		if(!ck){
			alert("아이디 중복검사를 실행해 주세요.");
			return false;
		} else if(!emailCk){
			alert("이메일 중복검사를 실행해 주세요.");
			return false;
		} else if(!phoneCk){
			alert("전화번호 중복검사를 실행해 주세요.");
			return false;
		} else if(!emailCk){
			alert("닉네임 중복검사를 실행해 주세요.");
			return false;
		}else{
			return true;
		}
	}
		
</script>

</body>
</html>

