package com.itwillbs.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.PageDTO;

@Repository
public class ReBoardDAO {
	
	//마이바티스 객체생성
	@Inject
	private SqlSession sqlSession;
		
	private static final String namespace="com.itwillbs.mappers.reboardMapper";

	public Integer getMaxNum() {
		System.out.println("ReBoardDAO getMaxNum()");
		
		return sqlSession.selectOne(namespace+".getMaxNum");
	}

	public void insertBoard(Map<String, Object> param) {
		System.out.println("ReBoardDAO insertBoard()");
		
		sqlSession.insert(namespace+".insertBoard", param);
	}

	public List<Map<String, Object>> getBoardList(PageDTO pageDTO) {
		System.out.println("ReBoardDAO getBoardList()");
		
		return sqlSession.selectList(namespace+".getBoardList", pageDTO);
	}

	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("ReBoardDAO getBoardList()");
		
		return sqlSession.selectOne(namespace+".getBoardCount",pageDTO);
	}

	public void updateReadcount(int num) {
		System.out.println("ReBoardDAO updateReadcount()");
		
		sqlSession.update(namespace+".updateReadcount", num);
	}

	public Map<String, Object> getBoard(int num) {
		System.out.println("ReBoardDAO getBoard()");
		
		return sqlSession.selectOne(namespace+".getBoard", num);
	}

	public void updateReSeq(Map<String, Object> param) {
		System.out.println("ReBoardDAO updateReSeq()");
		
		sqlSession.update(namespace+".updateReSeq", param);
	}

}//클래스
