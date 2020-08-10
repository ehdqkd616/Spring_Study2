<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTP-8">
<title>사원 리스트</title>
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
<h1>사원 목록</h1>
<a href="./index">Home</a>
<%-- <a href='<c:url value="/hr/insert"/>'>사원 정보 입력</a> 절대경로, 상대경로 --%>
<a href='<c:url value="insert"/>'>사원 정보 입력</a>
<table border=1>
<tr>
	<th>Employee_Id</th>
	<th>First_Name</th>
	<th>Last_Name</th>
	<th>Email</th>
	<th>Phone_Number</th>
	<th>Hire_Date</th>
	<th>Job_Id</th>
	<th>Salary</th>
	<th>Commission_Pct</th>
	<th>Manager_Id</th>
	<th>Department_Id</th>
</tr>
<c:forEach var="emp" items="${empList}">
<tr>
	<%--여기서 emp는 EmpController에 있는
	model.addAttribute("emp",empService.getEmpInfo(employeeId))이다.
	firstName, lastName 등등은 VO에서 가져온 것들 --%>
	<td><a href="${emp.employeeId}">${emp.employeeId}</a></td>
	<td>${emp.firstName}</td>
	<td>${emp.lastName}</td>
	<td>${emp.email}</td>
	<td>${emp.phoneNumber}</td>
	<td>${emp.hireDate}</td>
	<td>${emp.jobId}</td>
	<td>${emp.salary}</td>
	<td>${emp.commissionPct}</td>
	<td>${emp.managerId}</td>
	<td>${emp.departmentId}</td>
</tr>
</c:forEach>
</table>
</body>
</html>

