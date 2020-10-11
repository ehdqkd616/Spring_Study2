<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<sec:authorize access="isAnonymous()">
${message}
<form action="loginCheck" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
아이디 : <input type=text name=id><br>
비밀번호 : <input type=password name=pw><br>
<input type=submit value=로그인>
</form>
<form action="member/insert" method="get">
<input type=submit value=회원가입>
</form>
</sec:authorize>
</body>
</html>

