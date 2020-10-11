package com.coderby.myapp.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.coderby.myapp.member.model.MemberVO;

public interface IMemberRepository {
	
	void insertMember(MemberVO mem);
	MemberVO getMember(@Param("userId")String userId);
	String getPassword(@Param("userId")String userId);
	List<MemberVO> getMemberList(@Param("page")int page, @Param("word")String word);
	void updateMember(MemberVO mem);
	void updateMemberEnable(@Param("userId")String userId);
	void updateMemberAuth(@Param("auth")String auth, @Param("userId")String userId);
	void deleteMember(@Param("userId")String userId);
	Integer getMemberCount(@Param("word")String word);
	boolean checkId(@Param("userId")String userId);
	boolean checkEmail(@Param("email")String email);
	boolean checkPhone(@Param("phone")String phone);
	boolean checkNickname(@Param("nickname")String nickname);

}
