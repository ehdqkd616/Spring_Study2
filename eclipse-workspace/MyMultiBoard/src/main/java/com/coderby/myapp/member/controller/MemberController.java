package com.coderby.myapp.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.member.model.MemberVO;
import com.coderby.myapp.member.service.IMemberService;
import com.coderby.myapp.util.PagingManager;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	IMemberService memberService;
	
	//1순위 타입, 2순위 변수 이름
	@Autowired
	BCryptPasswordEncoder bpe;
	
	@GetMapping("/insert")
	public void insert(Model model) {
		model.addAttribute("message","insert");
	}
	
	@PostMapping("/insert")
	public String insert(Model model, MemberVO mem, RedirectAttributes redirectAttributes) {
		mem.setPassword(bpe.encode(mem.getPassword()));
		mem.setEnabled(1);
		mem.setReportCount(0);
		mem.setProfilePic(null);
		mem.setAuthority(1);
		memberService.insertMember(mem);
		redirectAttributes.addFlashAttribute("message","회원 가입 완료");
		return "redirect:/login";
	}
	
	@PreAuthorize("isAuthenticated() and #userId==principal.userId or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@GetMapping("/update/{userId}")
	public String update(Model model, @PathVariable String userId) {
		MemberVO mem = memberService.getMember(userId);
		model.addAttribute("mem",mem);
		model.addAttribute("message","update");
		return "member/insert";
	}
	
	@PreAuthorize("isAuthenticated() and #userId==principal.userId or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@PostMapping("/update")
	public String update(MemberVO mem, Authentication auth) {
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
				|| auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))) {
			
		}else {
			if(bpe.matches(mem.getPassword(), memberService.getPassword(mem.getUserId()))) {
				
			}else {
				throw new RuntimeException("비밀번호가 다릅니다.");
			}
		}
		memberService.updateMember(mem);
		return "redirect:/member/"+mem.getUserId();
	}
	
	@PreAuthorize("isAuthenticated() and #userId==principal.userId or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@GetMapping("/{userId}")
	public String getMember(@PathVariable String userId, Model model) {
		model.addAttribute("mem",memberService.getMember(userId));
		return "member/view";
	}
	
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@PostMapping("/enable")
	public String memberEnable(@RequestParam(required=false, defaultValue="0")int enable, String userId) {
		if(enable==0) {
			throw new RuntimeException("계정 활성 여부를 체크해 주세요.");
		}else {
			memberService.updateMemberEnable(userId);
			return "redirect:/member/"+userId;
		}
	}
	
	@PreAuthorize("isAuthenticated() and hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	@GetMapping("/list")
	public void getMemberList(@RequestParam(required=false, defaultValue="1")int page, @RequestParam(required=false)String word, Model model) {
		model.addAttribute("list",memberService.getMemberList(page, word));
		model.addAttribute("pageManager",new PagingManager(memberService.getMemberCount(word),page));
	}
	
	@PostMapping("/delete")
	@PreAuthorize("isAuthenticated() and #userId==principal.userId or hasAnyRole('ROLE_ADMIN','ROLE_MASTER')")
	public String delete(Authentication auth, String userId, String password, HttpSession session) {
		if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))
				|| auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MASTER"))) {
			memberService.deleteMember(userId);
			return "redirect:/member/list";
		}else {
			if(bpe.matches(password, memberService.getPassword(userId))) {
				memberService.deleteMember(userId);
				session.invalidate();
				return "redirect:/";
			}else {
				throw new RuntimeException("비밀번호가 다릅니다.");
			}
		}
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MASTER')")
	@PostMapping("/updateAuth")
	public String updateMemberAuth(String userId, String auth, Model model, RedirectAttributes redi) {
		memberService.updateMemberAuth(auth, userId);
		redi.addFlashAttribute("authMessage","권한이 변경되었습니다.");
		return "redirect:/member/"+userId;
	}
	
	@RequestMapping("/insert/idCheck")
	@ResponseBody
	public boolean idCheck(String userId) {
		return memberService.checkId(userId);
	}
	
	@RequestMapping("/insert/emailCheck")
	@ResponseBody
	public boolean emailCheck(String email) {
		return memberService.checkEmail(email);
	}
	
	@RequestMapping("/insert/phoneCheck")
	@ResponseBody
	public boolean phoneCheck(String phone) {
		return memberService.checkPhone(phone);
	}
	
	@RequestMapping("/insert/nicknameCheck")
	@ResponseBody
	public boolean nicknameCheck(String nickname) {
		return memberService.checkNickname(nickname);
	}
	
}

