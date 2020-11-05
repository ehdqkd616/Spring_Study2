package com.coderby.myapp.member.service;

import java.util.List;

import com.coderby.myapp.member.model.MemberVO;

public interface IMemberService {
	
	void insertMember(MemberVO member);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	List<MemberVO> getMemberList(int page, String word);
	void updateMember(MemberVO mem);
	void updateMemberEnable(String userId);
	void updateMemberAuth(String auth, String userId);
	void deleteMember(String userId);
	Integer getMemberCount(String word);
	boolean checkId(String userId);
	boolean checkEmail(String email);
	boolean checkPhone(String phone);
	boolean checkNickname(String nickname);
	
}
