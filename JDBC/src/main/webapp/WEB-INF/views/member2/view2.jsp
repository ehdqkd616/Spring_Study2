<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
</head>
<body>
<h1>${mem.userId} 회원 세부 정보</h1>
<table border=1>
<tr>
	<th>ID</th>
	<th>${mem.userId}</th>
</tr>
<tr>
	<th>Name</th>
	<th>${mem.name}</th>
</tr>
<tr>
	<th>Email</th>
	<th>${mem.email}</th>
</tr>
<tr>
	<th>Address</th>
	<th>${mem.address}</th>
</tr>
<tr>
	<th>Role</th>
	<th>${mem.auth}</th>
</tr>
</table>
<a href="update?userId=${mem.userId}">정보 수정</a><br>
<br>
<sec:authorize access="isAuthenticated() and hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MASTER')">
<form action="delete" method=post>
비밀번호 입력 : <input type=password name=password>
<input type="hidden" name="userId" value="${mem.userId}">
<input type="hidden" name="principal" value='<sec:authentication property="principal.username"/>'>
<input type="hidden" name="auth" value='${sessionScope.SPRING_SECURITY_CONTEXT.authentication.authorities}'>
<input type="submit" value="회원 탈퇴">
<br>
</form>
</sec:authorize>
<sec:authorize access="isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
<a href="list">회원 목록</a><br>
<br>
<form action="updateEnabled" method=post>
<input type=hidden name=userId value="${mem.userId}">
<input type="hidden" name=enabled value="${mem.enabled}">
<input type="submit" value="${mem.enabled ? '계정 비활성화' : '계정 활성화'}">
</form>
${message}
</sec:authorize>

<br>
<sec:authorize access="isAuthenticated() and hasRole('ROLE_MASTER')">
<h2>권한 변경</h2>
<form action="updateAuth" method=post>
<input type=hidden name=userId value="${mem.userId}">
<select name="role">
	<c:forEach var="auth" items="${authList}">
		<option value="${auth.authority}" ${mem.auth eq auth.authority ? "selected" : 
		"" }>${auth.authority}</option>
	</c:forEach>
</select>
<input type=submit value="변경">
</form>
${message2}
</sec:authorize>

</body>
</html>
