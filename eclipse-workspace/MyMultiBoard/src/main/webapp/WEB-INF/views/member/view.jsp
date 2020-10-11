<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Info</title>
</head>
<body>
<h2>회원 상세 정보</h2>
<table border=1>
<tr>
<td>아이디</td><td>${mem.userId}</td>
</tr>
<tr>
<td>이름</td><td>${mem.userName}</td>
</tr>
<tr>
<td>이메일</td><td>${mem.email}</td>
</tr>
<tr>
<td>전화번호</td><td>${mem.phone}</td>
</tr>
<tr>
<td>닉네임</td><td>${mem.nickname}</td>
</tr>
<tr>
<td>신고횟수</td><td>${mem.reportCount}</td>
</tr>
<tr>
<td>프로필 사진</td>
<td>
	<c:choose>
		<c:when test="${(fileType eq '.JPG') or (fileType eq 'JPEG') or (fileType eq '.PNG') or (fileType eq '.GIF')}">
			<!-- <img src='<c:url value="/img/${file.fileId}"/>' width="100" class="profilePic"><br>  -->
			<img src='<c:url value="/resources/images/profileDefault.png"/>' width="100" class="profilePic"><br>
		</c:when>
		<c:otherwise>
			<img src='<c:url value="/resources/images/profileDefault.png"/>' width="100" class="profilePic"><br>
		</c:otherwise>
	</c:choose>
</td>

<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
<tr>
<td>권한</td><td>${mem.authorityName}</td>
</tr>
</sec:authorize>
</table>
<a href="update/${mem.userId}">정보 수정</a><br><br>
<form action="delete" method=post>
<sec:csrfInput/>
<input type=hidden name=userId value="${mem.userId}">
비밀번호 입력 : <input type=password name=password>
<input type=submit value="회원 탈퇴">
</form>
<br>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MASTER')">
<form action="enable" method="post">
<sec:csrfInput/>
<input type=hidden name=userId value="${mem.userId}">
<a href="list">회원 목록</a><br><br>
<input type=checkbox name=enable value=1>
<input type=submit value="${mem.isEnabled() ? '계정 비활성' : '계정 활성' }">
</form>
</sec:authorize>
<br>
<sec:authorize access="hasRole('ROLE_MASTER')">
<form action="updateAuth" method="post">
<sec:csrfInput/>
<input type=hidden name=userId value="${mem.userId}">
<h3>권한 변경</h3>
${authMessage}
<select name=auth>
<option value="ROLE_USER">USER</option>
<option value="ROLE_ADMIN">ADMIN</option>
<option value="ROLE_MASTER">MASTER</option>
</select>
<input type=submit value=변경>
</form>
</sec:authorize>
</body>
</html>