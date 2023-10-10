package com.itwillbs.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwillbs.dao.BoardDAO;
import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;

@Service
public class BoardService {
	
	@Inject
	private BoardDAO boardDAO;
	
	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardService insertBoard()");
		// num subject content 
		// num readcount date 
		boardDTO.setReadcount(0);
		boardDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		if(boardDAO.getMaxNum()== null) {
			//글 없는 경우			
			boardDTO.setNum(1);
		}else {
			// 글 있는 경우 // max(num) +1
			boardDTO.setNum(boardDAO.getMaxNum()+1);
		}
		
		boardDAO.insertBoard(boardDTO);
		
	}

	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardService getBoardList()");
		// 10개씩 가져올때 현페이지에 대한 시작하는 행번호 구하기
		int startRow = (pageDTO.getCurrentPage()-1)*pageDTO.getPageSize() + 1;
		// 끝나는 행번호 구하기
		int endRow = startRow + pageDTO.getPageSize() - 1;
		
		// 디비 startRow - 1
		pageDTO.setStartRow(startRow - 1);
		pageDTO.setEndRow(endRow);
		
		return boardDAO.getBoardList(pageDTO);
	}

	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("BoardService getBoardCount()");
		
		return boardDAO.getBoardCount(pageDTO);
	}

	public BoardDTO getBoard(int num) {
		System.out.println("BoardService getBoard()");
		
		return boardDAO.getBoard(num);
	}

	public void updateReadcount(int num) {
		System.out.println("BoardService updateReadcount()");
		
		boardDAO.updateReadcount(num);
	}

	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardService updateReadcount()");
		
		boardDAO.updateBoard(boardDTO);
	}

	public void fupdateBoard(BoardDTO boardDTO) {
		System.out.println("BoardService fupdateBoard()");
		
		boardDAO.fupdateBoard(boardDTO);
	}

	public void deleteBoard(int num) {
		System.out.println("BoardService deleteBoard()");
		
		boardDAO.deleteBoard(num);
	}

	

}//클래스
