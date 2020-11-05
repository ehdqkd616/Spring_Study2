<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 ${message}</title>
</head>
<body>
<h1>${mem.userId} 회원 ${message eq "insert" ? "가입" : "정보 수정"}</h1>
<form action="./${message}" method=post>
<table border=1>
<tr>
	<th>ID</th>
	<td><input type=text name=userId required value="${mem.userId}"
	${empty mem ? "" : "readonly"}></td>
</tr>
<tr>
	<th>Password</th>
	<td><input type="password" name=password required value=""></td>
</tr>
<tr>
	<th>Name</th>
	<td><input type=text name=name required value="${mem.name}"></td>
</tr>
<tr>
	<th>Email</th>
	<td><input type=text name=email required value="${mem.email}"
	${empty mem ? "" : "readonly"}></td>
</tr>
<tr>
	<th>Address</th>
	<td><input type=text name=address required value="${mem.address}"></td>
</tr>
<tr>
<th colspan=2><input type=submit value=${message eq "insert" ? "입력" : "수정" }>
<input type=hidden value="${mem.userId}">
<input type=reset value="취소"></th>
</tr>
</table>
</form>
</body>
</html>


<%-- 
<form action="insert" method="post">
<table>
<tr>
<td>아이디</td><td><input type=text name=userId></td>
</tr>
<tr>
<td>이름</td><td><input type=text name=name></td>
</tr>
<tr>
<td>비밀번호</td><td><input type=password name=password></td>
</tr>
<tr>
<td>이메일</td><td><input type=text name=email></td>
</tr>
<tr>
<td>주소</td><td><input type=text name=address></td>
</tr>
</table>
<input type=submit value=가입><input type=reset value=취소>
</form>
</body>
</html>
--%>
