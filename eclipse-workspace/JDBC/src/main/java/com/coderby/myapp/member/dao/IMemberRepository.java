package com.coderby.myapp.member.dao;

import java.util.List;

import com.coderby.myapp.member.model.MemberVO;

public interface IMemberRepository {
	
	void insertMember(MemberVO mem);
	void insertAuth(String userId);
	MemberVO getMember(String userId);
	String getPassword(String userId);
	List<MemberVO> getMemberList(int page, String word);
	void updateMember(MemberVO mem);
	void updateMemberEnable(String userId);
	void updateMemberAuth(String auth, String userId);
	void deleteMember(String userId);
	void deleteAuth(String userId);
	Integer getMemberCount(String word);
	boolean checkId(String userId);

//	List<MemberAuthVO> getAuthList();
//	void updateAuth(String role, String userId);
//	List<MemberVO> getMemList();
//	void updateEnabled(String userId);

}
