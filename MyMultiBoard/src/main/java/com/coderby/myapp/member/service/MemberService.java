package com.coderby.myapp.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderby.myapp.member.dao.IMemberRepository;
import com.coderby.myapp.member.model.MemberVO;

@Service
public class MemberService implements IMemberService {
	
	@Autowired
	@Qualifier("IMemberRepository")
	IMemberRepository memberRepository;
	
	@Override
	public void insertMember(MemberVO mem) {
		memberRepository.insertMember(mem);
	}

	@Override
	public MemberVO getMember(String userId) {
		return memberRepository.getMember(userId);
	}

	@Override
	public String getPassword(String userId) {
		return memberRepository.getPassword(userId);
	}

	@Override
	public List<MemberVO> getMemberList(int page, String word) {
		return memberRepository.getMemberList(page, word);
	}
	
	@Override
	public void updateMember(MemberVO mem) {
		memberRepository.updateMember(mem);
	}
	
	@Override
	public void updateMemberEnable(String userId) {
		memberRepository.updateMemberEnable(userId);
	}
	
	@Override
	public void updateMemberAuth(String auth, String userId) {
		memberRepository.updateMemberAuth(auth, userId);
	}
	
	@Override
	public void deleteMember(String userId) {
		memberRepository.deleteMember(userId);
	}
	
	@Override
	public Integer getMemberCount(String word) {
		return memberRepository.getMemberCount(word);
	}

	@Override
	public boolean checkId(String userId) {
		return memberRepository.checkId(userId);
	}

	@Override
	public boolean checkEmail(String email) {
		return memberRepository.checkId(email);
	}

	@Override
	public boolean checkPhone(String phone) {
		return memberRepository.checkId(phone);
	}

	@Override
	public boolean checkNickname(String nickname) {
		return memberRepository.checkId(nickname);
	}
	
	
	
}

