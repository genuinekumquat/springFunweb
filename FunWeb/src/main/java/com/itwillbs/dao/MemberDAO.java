package com.itwillbs.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberDTO;

@Repository
public class MemberDAO {
	
	// 마이바티스 객체생성 
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.itwillbs.mappers.memberMapper";

	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO insertMember");
		
		sqlSession.insert(namespace+".insertMember",memberDTO);
	}//

	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberDAO userCheck");

		return sqlSession.selectOne(namespace+".userCheck", memberDTO);
	}

	public MemberDTO getMember(String id) {
		System.out.println("MemberDAO getMember()");
		return sqlSession.selectOne(namespace+".getMember", id);
	}//getMember

	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO updateMember");
		
		sqlSession.update(namespace+".updateMember",memberDTO);
	}

	public void deleteMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO deleteMember");
		
		sqlSession.delete(namespace+".deleteMember",memberDTO);
	}

	public List<MemberDTO> getMemberList() {
		System.out.println("MemberDAO getMemberList()");
		
		return sqlSession.selectList(namespace+".getMemberList");
	}

}// 클래스
