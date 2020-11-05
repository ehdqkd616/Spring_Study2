<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Home</title>
</head>
<body>
<h1>Welcome Member Home!</h1><br>
<sec:authorize access="isAnonymous()">
<a href="member/insert">회원 가입</a><br>
<a href="login">로그인</a><br>
<a href="/hr/index">인사 관리</a><br>
</sec:authorize>
<sec:authorize access="isAuthenticated() and hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MASTER')">
<sec:authentication property="principal.username"/>님 환영합니다 *^^*<br>
<a href="file">파일 업/다운</a><br>
</sec:authorize>
<sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
<a href="member/<sec:authentication property='principal.username'/>">마이 페이지</a><br>
<a href="hr/index">인사 관리</a><br>
</sec:authorize>
<sec:authorize access="isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
<a href="member/list">회원 목록 관리</a><br>
</sec:authorize>
<sec:authorize access="isAuthenticated() and hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MASTER')">
<form action="${pageContext.request.contextPath}/logout" method="post">
<input type=submit value="로그아웃">
</form>
</sec:authorize>
</body>
</html>