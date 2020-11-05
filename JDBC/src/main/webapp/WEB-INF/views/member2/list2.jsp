<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTP-8">
<title>회원 목록</title>
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
<h1>회원 목록</h1>
<a href="/emp">Home</a>
<table border=1>
<tr>
	<th>ID</th>
	<th>Name</th>
	<th>Email</th>
	<th>Address</th>
	<th>Role</th>
	<th>Enabled</th>
</tr>
<c:forEach var="mem" items="${list}">
<tr>
	<td><a href="${mem.userId}">${mem.userId}</a></td>
	<td>${mem.name}</td>
	<td>${mem.email}</td>
	<td>${mem.address}</td>
	<td>${mem.auth}</td>
	<td>${mem.enabled}</td>
</tr>
</c:forEach>
</table>
</body>
</html>

