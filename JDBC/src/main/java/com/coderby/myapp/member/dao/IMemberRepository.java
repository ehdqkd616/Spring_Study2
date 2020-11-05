package com.coderby.myapp.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coderby.myapp.member.model.MemberVO;

public interface IMemberRepository {
	
	void insertMember(MemberVO mem);
	void insertAuth(@Param("userId")String userId);
	MemberVO getMember(@Param("userId")String userId);
	String getPassword(@Param("userId")String userId);
	List<MemberVO> getMemberList(@Param("page")int page, @Param("word")String word);
	void updateMember(MemberVO mem);
	void updateMemberEnable(@Param("userId")String userId);
	void updateMemberAuth(@Param("auth")String auth, @Param("userId")String userId);
	void deleteMember(@Param("userId")String userId);
	void deleteAuth(@Param("userId")String userId);
	Integer getMemberCount(@Param("word")String word);
	boolean checkId(@Param("userId")String userId);

//	List<MemberAuthVO> getAuthList();
//	void updateAuth(String role, String userId);
//	List<MemberVO> getMemList();
//	void updateEnabled(String userId);

}
