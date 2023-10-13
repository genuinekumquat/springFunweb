package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.ReBoardDAO;
import com.itwillbs.domain.PageDTO;

@Service
public class ReBoardService {
	
	@Inject
	private ReBoardDAO reBoardDAO;

	public void insertBoard(Map<String, Object> param) {
		System.out.println("ReBoardService insertBoard");
		// name subject content
		// num readcount date
		// re_ref = num , 	    re_lev =0, re_seq =0
		param.put("readcount",0);
		param.put("date", new Timestamp(System.currentTimeMillis()));
		
		if(reBoardDAO.getMaxNum() == null) {
				param.put("num", 1);
				param.put("re_ref", 1);
		}else {
				param.put("num", reBoardDAO.getMaxNum() + 1);
				param.put("re_ref", reBoardDAO.getMaxNum() + 1);
		}
		param.put("re_lev", 0);
		param.put("re_seq", 0);
		
		reBoardDAO.insertBoard(param);
	}//

	public List<Map<String, Object>> getBoardList(PageDTO pageDTO) {
		System.out.println("ReBoardService getBoardList");
		// 10개씩 가져올때 현페이지에 대한 시작하는 행번호 구하기
		int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize() + 1;
		// 끝나는 행번호 구하기
		int endRow = startRow + pageDTO.getPageSize() - 1;
				
		// 디비 startRow - 1
		pageDTO.setStartRow(startRow - 1);
		pageDTO.setEndRow(endRow);
				
		return reBoardDAO.getBoardList(pageDTO);
	}

	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("ReBoardService getBoardCount");
		
		return reBoardDAO.getBoardCount(pageDTO);
	}

	public void updateReadcount(int num) {
		System.out.println("ReBoardService updateReadcount");
		
		reBoardDAO.updateReadcount(num);
	}

	public Map<String, Object> getBoard(int num) {
		System.out.println("ReBoardService getBoard");
		
		return reBoardDAO.getBoard(num);
	}

	public void reInsertBoard(Map<String, Object> param) {
		System.out.println("ReBoardService reInsertBoard");
		// name subject content, 
		// re_ref 같은 그룹 그대로 사용 , re_lev+1, re_seq+1
		// num readcount date
		param.put("readcount",0);
		param.put("date", new Timestamp(System.currentTimeMillis()));
				
		if(reBoardDAO.getMaxNum() == null) {
				param.put("num", 1);
		}else {
				param.put("num", reBoardDAO.getMaxNum() + 1);
		}
		//기존글 밑에 답글을 한칸씩 아래로 순서 재배치 작업
		//조건 : 같은 그룹(re_ref 같고) 이면서 답글을 찾아서(답글순서 큰거찾기)
		//     답글 순서를 1증가(한칸 아래로 순서 재배치)
		reBoardDAO.updateReSeq(param);
		
		// 들여쓰기 1증가
		param.put("re_lev", Integer.parseInt(param.get("re_lev").toString())+1);
		// 답글 순서 1증가 바로 아래 순서 지정되어짐
		param.put("re_seq", Integer.parseInt(param.get("re_seq").toString())+1);
				
		reBoardDAO.insertBoard(param);		
		
	}

}//클래스
