package com.coderby.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import com.coderby.myapp.hr.model.EmpDetailVO;
import com.coderby.myapp.hr.model.EmpVO;
import com.coderby.myapp.hr.model.JobVO;

public interface IEmpRepository {
	
	int getEmpCount();
	int getEmpCount(int deptId);
	List<EmpVO> getEmpList();
	EmpVO getEmpInfo(int empId);
	EmpDetailVO getEmpDetailInfo(int empId);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteEmp(int empId);
	void deleteJobHistory(int empId);
	void updateManagers(int empId);
	List<EmpVO> getEmpListByName(String name);
	List<Map<String,Object>> getAllDeptId();
//	List<Map<String,Object>> getAllJobId();
	List<JobVO> getAllJobId();
	List<Map<String,Object>> getAllManagerId();
	List<EmpVO> getEmpByMaxSalary();
	Map<String, Integer> getUpdateCount(int empId);
	
//	public List<DeptVO> deptList();
//	public List<Map<String, Object>> maxSalary();
//	Map<String,Object> getDeptName();
//	void deleteMan(int empId);
//	void deleteDept(int empId);
//	int getManCount(int empId);
//	int getDeptCount(int empId);
//	List<EmpVO> getMaxSalaryEmpList();
	List<EmpVO> getAvgOverSalaryEmpList();
	
}
