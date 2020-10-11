<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
<style>
  table {
    width: 100%;
    border-top: 3px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
    text-align: center;
  }
  thead tr {
    background-color: #0d47a1;
    color: #ffffff;
  }
  tbody tr:nth-child(2n) {
    background-color: #bbdefb;
  }
  tbody tr:nth-child(2n+1) {
    background-color: #e3f2fd;
  }
</style>
</head>
<body>
<h2>회원 목록</h2>
<form action="list">
아이디 또는 이름 : <input type="search" name=word>
</form>
<a href="/myapp">Home</a>
<table border=1>
<tr>
	<th>ID</th>
	<th>Name</th>
	<th>Email</th>
	<th>Phone</th>
	<th>Nickname</th>
	<th>Enabled</th>
	<th>ReportCount</th>
	<th>ProfilePic</th>
	<th>JoinDate</th>
	<th>Role</th>
</tr>
<c:forEach var="member" items="${list}">
<tr>
<td><a href="${member.userId}">${member.userId}</a></td>
<td>${member.userName}</td>
<td>${member.email}</td>
<td>${member.phone}</td>
<td>${member.nickname}</td>
<td>${member.enabled}</td>
<td>${member.reportCount}</td>

<td>
	<c:choose>
		<c:when test="${member.profilePic eq null}">
			<img src='<c:url value="/resources/images/profileDefault.png"/>' width="100" class="profilePic"><br>
		</c:when>
		<c:otherwise>
			<img src='<c:url value="/resources/images/profileDefault.png"/>' width="100" class="profilePic"><br>
		</c:otherwise>
	</c:choose>
</td>

<td>${member.joinDate}</td>
<c:set var="len" value="${fn:length(member.authorityName)}"/>
<td>${fn:substring(member.authorityName, 5, len)}</td>
</tr>
</c:forEach>
</table>
<table>
<tr>
<td colspan=5>
<h6>
[<a href="list?page=1">처음</a>]
<c:if test="${pageManager.nowBlock gt 1}">
	[<a href="list?page=${pageManager.startPage-1}">이전</a>]
</c:if>
<c:forEach var="i" begin="${pageManager.startPage}" end="${pageManager.endPage}">
[<a href="list?page=${i}">${i}</a>]
</c:forEach>
<c:if test="${pageManager.nowBlock < pageManager.totalBlock}">
	[<a href="list?page=${pageManager.endPage+1}">다음</a>]
</c:if>
</h6>
</td></tr>
</table>
</body>
</html>