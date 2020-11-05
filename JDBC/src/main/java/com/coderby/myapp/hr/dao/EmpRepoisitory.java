package com.coderby.myapp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.hr.model.EmpDetailVO;
import com.coderby.myapp.hr.model.EmpVO;
import com.coderby.myapp.hr.model.JobVO;
import com.coderby.myapp.util.PropertyEnc;

@Repository
public class EmpRepoisitory implements IEmpRepository {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PropertyEnc propertyEnc;
	
//	RowMapper<DeptVO> deptMapper = new RowMapper<DeptVO>() {
//		@Override
//		public DeptVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//			DeptVO dept = new DeptVO();
//			dept.setDepartmentid(rs.getInt("department_id"));
//			dept.setDepartmentName(rs.getString("department_name"));
//			dept.setManagerId(rs.getInt("manager_id"));
//			dept.setLocationId(rs.getInt("location_id"));
//			return dept;
//		}
//	}; //필드에 선언해서 변수명으로 사용이 가능하다
	
	private class EmpMapper implements RowMapper<EmpVO>{
		public EmpVO mapRow(ResultSet rs, int count) throws
		SQLException {
			EmpVO emp = new EmpVO();
			emp.setEmployeeId(rs.getInt("employee_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString("email"));
			emp.setPhoneNumber(rs.getString("phone_number"));
			emp.setHireDate(rs.getDate("hire_date"));
			emp.setJobId(rs.getString("job_id"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setCommissionPct(rs.getDouble("commission_pct"));
			emp.setManagerId(rs.getInt("manager_id"));
			emp.setDepartmentId(rs.getInt("department_id"));
			return emp;
		}
	}
	
	RowMapper<EmpDetailVO> empDetailMapper = new RowMapper<EmpDetailVO>() {
		@Override
		public EmpDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmpDetailVO emp = new EmpDetailVO();
			emp.setEmployeeId(rs.getInt("employee_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString("email"));
			emp.setPhoneNumber(rs.getString("phone_number"));
			emp.setHireDate(rs.getDate("hire_date"));
			emp.setJobId(rs.getString("job_id"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setCommissionPct(rs.getDouble("commission_pct"));
			emp.setManagerId(rs.getInt("manager_id"));
			emp.setDepartmentId(rs.getInt("department_id"));
			emp.setJobTitle(rs.getString("job_title"));
			emp.setDepartmentName(rs.getString("department_name"));
			emp.setManagerName(rs.getString("manager_name"));
			return emp;
		}
	};
	
	@Override
	public int getEmpCount() {
		String sql = "select count(*) from employees";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	@Override
	public int getEmpCount(int deptId) {
		String sql = "select count(*) from employees where department_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, deptId);
	}
	
	@Override
	public List<EmpVO> getEmpList() {
		String sql = "select * from employees";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	@Override
	public EmpVO getEmpInfo(int empId) {
		String sql = "select * from employees where employee_id=?";
	return jdbcTemplate.queryForObject(sql, new EmpMapper(), empId);
	}
	
	public EmpDetailVO getEmpDetailInfo(int empId) {
		String sql = "select employee_id, first_name, last_name, "
				+ "email, phone_number, hire_date, e.job_id, job_title, "
				+ "salary, commission_pct, e.manager_id, manager_name, "
				+ "e.department_id, department_name " 
				+ "from employees e "
				+ "left join departments d "
				+ "on e.department_id=d.department_id "
				+ "left join jobs j "
				+ "on e.job_id=j.job_id "
				+ "left join (select employee_id as manager_id, "
				+ "first_name||' '||last_name as manager_name "
				+ "from employees where employee_id in "
				+ "(select distinct manager_id from employees)) m "
				+ "on e.manager_id = m.manager_id "
				+ "where employee_id=?";
		return jdbcTemplate.queryForObject(sql, empDetailMapper, empId);
	}

	@Override
	public void updateEmp(EmpVO emp) {
		String sql = "update employees set first_name=?, last_name=?,"
				+ "email=?, phone_number=?, hire_date=?, job_id=?,"
				+ "salary=?, commission_pct=?, manager_id=?,"
				+ "department_id=? where employee_id=?";
		jdbcTemplate.update(sql, emp.getFirstName(),
				emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(),
				emp.getHireDate(), emp.getJobId(), emp.getSalary(),
				emp.getCommissionPct(), emp.getManagerId(), emp.getDepartmentId(),
				emp.getEmployeeId());
	}

	@Override
	public void insertEmp(EmpVO emp) {
		String sql = "insert into employees "
				+ "values(?,?,?,?,?,sysdate,?,?,?,?,?)";
		jdbcTemplate.update(sql,emp.getEmployeeId(), emp.getFirstName(), 
				emp.getLastName(), emp.getEmail(), emp.getPhoneNumber(),
				emp.getJobId(), emp.getSalary(), emp.getCommissionPct(), 
				emp.getManagerId(), emp.getDepartmentId());		
	}
	
	@Override
	public void deleteEmp(int empId) {
		String sql = "delete from employees where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}
	
//	@Override
//	public void deleteMan(int empId) {
//		String sql = "update employees set manager_id=null "
//				+ "where manager_id=?";
//		jdbcTemplate.update(sql,empId);
//	}
	
//	@Override
//	public void deleteDept(int empId) {
//		String sql = "update departments set manager_id=null "
//				+ "where manager_id=?";
//		jdbcTemplate.update(sql,empId);
//	}

	@Override
	public void deleteJobHistory(int empId) {
		String sql = "delete from job_history where employee_id=?";
		jdbcTemplate.update(sql, empId);
	}
	
//	@Override
//	public int getManCount(int empId) {
//		String sql = "select count(*) from employees where manager_id=?";
//		return jdbcTemplate.queryForObject(sql, Integer.class, empId);
//	}

//	@Override
//	public int getDeptCount(int empId) {
//		String sql = "select count(*) from departments where manager_id=?";
//		return jdbcTemplate.queryForObject(sql, Integer.class, empId);
//	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		String sql = "select department_id as departmentId," 
				+ "department_name as departmentName from departments";
		return jdbcTemplate.queryForList(sql);
	}

//	@Override
//	public List<Map<String, Object>> getAllJobId() {
//		String sql = "select job_id as jobId, job_title as jobTitle from jobs";
//		return jdbcTemplate.queryForList(sql);
//	}
	
	@Override
	public List<JobVO> getAllJobId() {
		String sql = "select job_id as jobId, job_title as jobTitle "
				+ "from jobs";
		return jdbcTemplate.query(sql, new RowMapper<JobVO>() {
			@Override
			public JobVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				JobVO job = new JobVO();
				job.setJobId(rs.getString(1));
				job.setJobTitle(rs.getString(2));
				return job;
			}
		});
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		String sql = "select employee_id as managerId, "
				+ "first_name||' '||last_name as managerName "
				+ "from employees "
				+ "where employee_id in "
				+ "(select distinct manager_id from employees)";
		return jdbcTemplate.queryForList(sql);
	}
	
	@Override
	public void updateManagers(int empId) {
		String sql = "update (select * from employees where manager_id=?) set manager_id=null";
		jdbcTemplate.update(sql,empId);
		sql = "update (select * from departments where manager_id=?) set manager_id=null";
		jdbcTemplate.update(sql,empId);
	}

//	public List<DeptVO> deptList() {
//		String sql = "select * from departments";
//		return jdbcTemplate.query(sql,new RowMapper<DeptVO>() {
//			@Override
//			public DeptVO mapRow(ResultSet rs, int count) throws SQLException {
//				DeptVO dept = new DeptVO();
//				dept.setDepartmentid(rs.getInt("department_id"));
//				dept.setDepartmentName(rs.getString("department_name"));
//				dept.setManagerId(rs.getInt("manager_id"));
//				dept.setLocationId(rs.getInt("location_id"));
//				return dept;
//			}
//		});
//		return jdbcTemplate.query(sql, deptMapper);
//	}
	
	@Override
	public List<EmpVO> getEmpListByName(String name) {
		String sql = "select * from employees where first_name like '%"+name+"%'";
		return jdbcTemplate.query(sql, new EmpMapper());
	}
	
	@Override
	public List<EmpVO> getEmpByMaxSalary() {
		String sql = "select * from employees where "
				+ "(department_id, salary) in (select department_id, max(salary) from "
				+ "employees group by department_id)";
		return jdbcTemplate.query(sql, new EmpMapper());
	}

	@Override
	public Map<String, Integer> getUpdateCount(int empId) {
		String sql = "select (select count(*) from employees where manager_id=?) as empCount,"
				+ "(select count(*) from departments where manager_id=?) as deptCount from dual";
		return jdbcTemplate.queryForObject(sql, new RowMapper<Map<String,Integer>>(){
			@Override
			public Map<String, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String,Integer> count = new HashMap<String,Integer>();
				count.put("empCount", rs.getInt(1));
				count.put("deptCount", rs.getInt(2));
				return count;
			}
		} ,empId, empId);
	}
	
//	public List<Map<String, Object>> maxSalary(){
//		String sql = "select job_id, max_salary from JOBS";
//		return jdbcTemplate.queryForList(sql);
//	}
	
//	@Override
//	public List<EmpVO> getMaxSalaryEmpList() {
//		String sql = "select * from employees where (department_id, salary) in"
//				+ "(select department_id, max(salary) from employees "
//				+ "group by department_id)";
//		return jdbcTemplate.query(sql, new EmpMapper());
//	}

	//join 문을 쓰는게 훨씬 효율적임
	@Override
	public List<EmpVO> getAvgOverSalaryEmpList() {
		String sql = "select * from employees e "  
				+ "where salary > (select round(avg(salary)) from employees " 
				+ "where department_id = e.department_id) " 
				+ "order by department_id";
		//select * from employees e
		//join (
		//select department_id, avg(salary)
		//from employees group by department_id) d
		//on e.department_id = d.department_id;
		return jdbcTemplate.query(sql, new EmpMapper());
	}


	
//	숙제
//	@Override
//	public List<EmpVO> Search(String m) {
//		//
//		String sql = "select * from EMPLOYEES where UPPER(FIRST_NAME || LAST_NAME)like UPPER('%"+m+"%')";
//		return jdbcTemplate.query(sql,new EmpMapper());
//	}
//	public EmpVO Searchs(String m) {
//		//
//		String sql = "select * from EMPLOYEES where UPPER(FIRST_NAME || LAST_NAME)like UPPER(?)";
//		return jdbcTemplate.queryForObject(sql,new EmpMapper(),("%"+m+"%"));
////		String sql = "select * from EMPLOYEES where UPPER(FIRST_NAME || LAST_NAME)like UPPER('%?%')";
////		return jdbcTemplate.queryForObject(sql,new EmpMapper(),m);
//	}
	
}
