package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.ReBoardDAO;

@Service
public class ReBoardService {
	
	@Inject
	private ReBoardDAO reBoardDAO;

	public void insertBoard(Map<String, Object> param) {
	System.out.println("ReBoardService insertBoard");	
	
	// num subject content 
	// num readcount date 
	// re_ref =num, 	re_lev =0, re_seq =0
	param.put("readcount", 0);
	param.put("date", new Timestamp(System.currentTimeMillis()));
	
	if(reBoardDAO.getMaxNum()== null) {
		//글 없는 경우			
		param.put("num", 1);
		param.put("re_ref", 1);
	}else {
		// 글 있는 경우 // max(num) +1
		param.put("num", reBoardDAO.getMaxNum()+1);
		param.put("re_ref", reBoardDAO.getMaxNum()+1);
	}
	param.put("re_lev", 0);
	param.put("re_seq", 0);
	
	reBoardDAO.insertBoard(param);
	}

}// 클래스 
