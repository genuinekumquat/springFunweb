package com.itwillbs.dao;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ReBoardDAO {
	
	// 마이바티스 객체생성		
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.itwillbs.mappers.reboardMapper";

	
	
	public Integer getMaxNum() {
		System.out.println("ReBoardDAO getMaxNum");	
		return sqlSession.selectOne(namespace+".getMaxNum");
	}

	public void insertBoard(Map<String, Object> param) {
		System.out.println("ReBoardDAO insertBoard");	
		
		sqlSession.insert(namespace+".insertBoard",param);
	}


}
