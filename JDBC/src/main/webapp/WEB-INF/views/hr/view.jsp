<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보</title>
</head>
<body>
<h1>${emp.employeeId}번 사원 세부정보</h1>
<a href="./index">Home</a>
<a href="./list">목록으로</a>
<table border=1>
<tr>
	<th>Employee_id</th>
	<th>${emp.employeeId}</th>
</tr>
<tr>
	<th>First_name</th>
	<th>${emp.firstName}</th>
</tr>
<tr>
	<th>Last_name</th>
	<th>${emp.lastName}</th>
</tr>
<tr>
	<th>Email</th>
	<th>${emp.email}</th>
</tr>
<tr>
	<th>PhoneNumber</th>
	<th>${emp.phoneNumber}</th>
</tr>
<tr>
	<th>Employee_id</th>
	<th>${emp.employeeId}</th>
</tr>
<tr>
	<th>Hire_date</th>
	<th>${emp.hireDate}</th>
</tr>
<tr>
	<th>Job_id</th>
	<th>${emp.jobTitle}(${emp.jobId})</th>
</tr>
<tr>
	<th>Salary</th>
	<th>${emp.salary}</th>
</tr>
<tr>
	<th>Commission_pct</th>
	<th>${emp.commissionPct}</th>
</tr>
<tr>
	<th>Manager_id</th>
	<th>${emp.managerName}(${emp.managerId})</th>
</tr>
<tr>
	<th>Department_id</th>
	<th>${emp.departmentName}(${emp.departmentId})</th>
</tr>
</table>
<a href="update?empId=${emp.employeeId}">수정하기</a>
<a href="delete?empId=${emp.employeeId}">삭제하기</a>
</body>
</html>


