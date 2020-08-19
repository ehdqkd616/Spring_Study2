package com.coderby.myapp.hr.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.coderby.myapp.hr.model.EmpDetailVO;
import com.coderby.myapp.hr.model.EmpVO;
import com.coderby.myapp.hr.model.JobVO;

public interface IEmpRepository {
	
	int getEmpCount();
	int getEmpCount(@Param("deptId") int deptId);
	List<EmpVO> getEmpList();
	EmpVO getEmpInfo(@Param("empId")int empId);
	EmpDetailVO getEmpDetailInfo(@Param("empId")int empId);
	void updateEmp(EmpVO emp);
	void insertEmp(EmpVO emp);
	void deleteEmp(@Param("empId") int empId);
	void deleteJobHistory(@Param("empId")int empId);
	void updateManagers(@Param("empId")int empId);
	List<EmpVO> getEmpListByName(@Param("name")String name);
	List<Map<String,Object>> getAllDeptId();
//	List<Map<String,Object>> getAllJobId();
	List<JobVO> getAllJobId();
	List<Map<String,Object>> getAllManagerId();
	List<EmpVO> getEmpByMaxSalary();
	Map<String, Integer> getUpdateCount(@Param("empId")int empId);
	
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
