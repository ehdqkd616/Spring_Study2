<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Delete</title>
</head>
<body>
<a href="./index">Home</a>
<a href="./list">목록으로</a>
<h1>${emp.firstName} ${emp.lastName}(${emp.employeeId}) 사원 삭제</h1>
<form action="./delete" method=post>
삭제하려는 ${emp.firstName} ${emp.lastName}은</br>
<h2>${manCount}</h2></br>
명의 매니저이고</br>
<h2>${deptCount}</h2></br>
개의 부서를 책임지고 있습니다.</br>
<h2>정말 삭제하시겠습니까?</h2></br>
<input type="submit" value="삭제">
<input type="hidden" name="empId" value="${emp.employeeId}">
<input type="button" value="취소">
</form>
</body>
</html>