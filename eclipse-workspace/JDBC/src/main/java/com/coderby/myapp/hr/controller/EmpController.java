package com.coderby.myapp.hr.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.hr.dao.IEmpService;
import com.coderby.myapp.hr.model.EmpVO;

//@Controller("/hr") //컨트롤러 자체에 맵핑 가능
@Controller
//@RequestMapping("/hr") -> RequestMapping은 Controller에서만 사용 가능
public class EmpController {
	
	@Autowired
	IEmpService empService;
	
//	public String empInsert(EmpVO emp) { 
//		//name=employeeId과 VO의 employeeId 이름이 같으면 알아서 맵핑, 형변환 해줌 
//		
//		empService.insertEmp(emp);
//		return "redirect:/hr/list";
//	}
	
	//@Requestmapping(value="/count", method=RequestMethod.GET)과 같은 어노테이션
	//RequestParam의 value를 int deptId에 맵핑
	@GetMapping(value="/hr/count")
	public String empCount(@RequestParam(value="deptId", required=false, defaultValue="0")
	int deptId, Model model) {
		if(deptId==0) {
			model.addAttribute("count",empService.getEmpCount());
		}else {
			model.addAttribute("count",empService.getEmpCount(deptId));
		}
		return "hr/count";
	}
	
	@RequestMapping(value="/hr/list")
	public void getAllEmployees(Model model) {
		List<EmpVO> empList = empService.getEmpList();
		model.addAttribute("empList",empList);
	}
	
	@GetMapping(value="/hr/{employeeId}")
	//{employeeId}를 int employeeId에 맵핑
	public String empView(Model model, @PathVariable int employeeId) {
		model.addAttribute("emp",empService.getEmpDetailInfo(employeeId));
		return "hr/view";
	}
	
	@GetMapping(value="/hr/insert")
//	public void empInsert(Model model) {
	public String empInsert(Model model) {
		model.addAttribute("emp", new EmpVO());
		model.addAttribute("jobList",empService.getAllJobId());
		model.addAttribute("manList",empService.getAllManagerId());
		model.addAttribute("deptList",empService.getAllDeptId());
		model.addAttribute("message", "insert");
		return "hr/insert";
	}//맵핑 주소(요청 주소)와 리턴 주소(응답 주소)가 같은 경우
	//리턴값을 void로 선언하면 자동으로 리턴값이 요청 주소로 맵핑된다

	@PostMapping(value="/hr/insert")
	public String empInsert(@ModelAttribute("emp") @Valid EmpVO emp, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("jobList",empService.getAllJobId());
			model.addAttribute("manList",empService.getAllManagerId());
			model.addAttribute("deptList",empService.getAllDeptId());
			model.addAttribute("message", "insert");
			return "hr/insert";
		}
			empService.insertEmp(emp);
			redirectAttributes.addFlashAttribute("message", "회원 저장 완료");
		return "redirect:/hr/list";
	}
	
	@GetMapping(value="/hr/update")
	public String empUpdate(@RequestParam(value="empId")
	int employeeId, Model model) {
		model.addAttribute("emp",empService.getEmpDetailInfo(employeeId));
		model.addAttribute("jobList",empService.getAllJobId());
		model.addAttribute("manList",empService.getAllManagerId());
		model.addAttribute("deptList",empService.getAllDeptId());
		model.addAttribute("message", "update");
		return "hr/insert";
	}
	
	@PostMapping(value="/hr/update")
	public String updateEmp(EmpVO emp, Model model) {
		empService.updateEmp(emp);
		return "redirect:/hr/"+emp.getEmployeeId();
	}
	
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@GetMapping(value="/hr/delete")
	public String deleteEmp(int empId, Model model) {
		model.addAttribute("emp",empService.getEmpInfo(empId));
		model.addAttribute("count",empService.getUpdateCount(empId));
		return "hr/delete";
	}

//	@GetMapping(value="/hr/delete")
//	public String empDelete(@RequestParam(value="empId")
//	int empId, Model model) {
//		model.addAttribute("emp",empService.getEmpInfo(empId));
//		model.addAttribute("manCount",empService.getManCount(empId));
//		model.addAttribute("deptCount",empService.getDeptCount(empId));
//		return "hr/delete";
//	}
	
//	1.jobHistory를 먼저 제거	
//	2.n명의 매니저를 Null로 바꾼다
//	3.n개 부서의 매니저를 Null로 바꾼다
//	4.사원 삭제
//	*트렌잭션을 반드시 적용한다. EmpService에서 트랜젝션 적용!!
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@PostMapping(value="/hr/delete")
	public String empDelete(@RequestParam(value="empId")
			int empId) { 
		empService.deleteEmp(empId);
		return "redirect:/hr/list";
	}
	
//	RequestMapping에 method를 정해주지 않으면 기본으로 GET방식이다.
//	@RequestMapping(value="/list")
//	@GetMapping(value="/hr/list")
//	public String empList(@RequestParam(value="param", 
//	required=false, defaultValue="0")
//	int param, Model model) {
//		if(param==0) {
//			model.addAttribute("list",empService.getEmpList());
//		}else if(param==1) {
//			model.addAttribute("list",empService.getMaxSalaryEmpList());
//		}else if(param==2)
//			model.addAttribute("list",empService.getAvgOverSalaryEmpList());
//		return "hr/list";	
//	}//리턴 값에 '/'가 붙지 않는 이유는 servlet-context에서 뷰리졸버 경로가 
	//<beans:property name="prefix" value="/WEB-INF/views/" /> 로 잡혀있기 때문
	
//    @ExceptionHandler(RuntimeException.class)
//    public String runtimeException
//    (HttpServletRequest request, Exception ex, Model model) {
//       model.addAttribute("url", request.getRequestURI());
//       model.addAttribute("exception", ex);
//       return "error/runtime";
//    }
    
	@GetMapping(value="/hr/index")
	public String empIndex() {
		return "hr/index";
	}	
	
	@GetMapping(value="/hr/getMaxSalary")
	public String getMaxSalaryByDept(Model model) {
		model.addAttribute("empList",empService.getEmpByMaxSalary());
		return "hr/list";
	}
	
	@GetMapping(value="/hr/getAvgOverSalary")
	public String getAvgOverSalaryEmpList(Model model) {
		model.addAttribute("empList",empService.getAvgOverSalaryEmpList());
		return "hr/list";
	}
	
    @RequestMapping(value="/hr/json/list")
    public @ResponseBody List<EmpVO> getAllEmployees() {
    	List<EmpVO> empList = empService.getEmpList();
    	return empList;
    }
    
    @RequestMapping(value="/hr/json/{employeeId}")
    public @ResponseBody EmpVO getEmployees(@PathVariable int employeeId) {
    	EmpVO emp = empService.getEmpInfo(employeeId);
    	return emp;
    }
    
}
