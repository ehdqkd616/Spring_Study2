package com.coderby.myapp.hr.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmpVO {
	
	@Min(value=207, message="207 이상의 번호를 입력하세요.")
	private int employeeId;
	
	@Pattern(regexp = "^[a-zA-Z가-힣]+[a-zA-Z가-힣]+$", message="영어, 한글만 입력하세요 (숫자 제외)")
	@Size(max=10, message="데이터 베이스 제약조건 위배(20byte)")
	private String firstName;
	
	@Pattern(regexp = "^[a-zA-Z가-힣]+[a-zA-Z가-힣]+$", message="영어, 한글만 입력하세요 (숫자 제외)")
	@Size(max=10, message="데이터 베이스 제약조건 위배(20byte)")
	private String lastName;
	
	@Pattern(regexp="[\\w]+@[a-zA-Z]+\\..*$", message="이메일 형식에 맞춰주세요.")
	private String email;
	
	@Pattern(regexp="^[0][1][0-9](-|\\s)\\d{3,4}(-|\\s)\\d{4}$", message="전화번호 형식이 아닙니다.")
	private String phoneNumber;
	
	@Past(message="현재 날짜보다 미래의 날짜입니다.")
	private java.sql.Date hireDate;
	
	private String jobId;
	
	@Digits(integer=6, fraction=2, message="잘못된 급여액(6자리 까지만 가능.)")
	private double salary;
	
	@DecimalMax(value="0.99", message="보너스율은 1미만입니다.")
	@DecimalMin(value="0.00", message="보너스율은 0이상입니다.")
	private double commissionPct;
	
	private int managerId;
	private int departmentId;
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public java.sql.Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getCommissionPct() {
		return commissionPct;
	}
	public void setCommissionPct(double commissionPct) {
		this.commissionPct = commissionPct;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
//	public String getJobTitle() {
//		return jobTitle;
//	}
//	public void setJobTitle(String jobTitle) {
//		this.jobTitle = jobTitle;
//	}
//	public String getManagerName() {
//		return managerName;
//	}
//	public void setManagerName(String managerName) {
//		this.managerName = managerName;
//	}
//	public String getDepartmentName() {
//		return departmentName;
//	}
//	public void setDepartmentName(String departmentName) {
//		this.departmentName = departmentName;
//	}


}
