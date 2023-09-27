package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.domain.MemberDTO;

@Service
public class MemberService {
	
	@Inject
	private MemberDAO memberDAO;

	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberService insertMember()");
		// 가입날짜 처리 
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		memberDAO.insertMember(memberDTO);
	}//

	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberService userCheck()");
		
		return memberDAO.userCheck(memberDTO);
	}


	public MemberDTO getMember(String id) {
		System.out.println("MemberService insertMember()");
		
		return memberDAO.getMember(id);
	}

	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberService insertMember()");
		
		memberDAO.updateMember(memberDTO);
	}

	public void deleteMember(MemberDTO memberDTO) {
		System.out.println("MemberService deleteMember()");
		
		memberDAO.deleteMember(memberDTO);
	}

	public List<MemberDTO> getMemberList() {
		System.out.println("MemberService getMemberList()");
		
		return memberDAO.getMemberList();
	}

}//
