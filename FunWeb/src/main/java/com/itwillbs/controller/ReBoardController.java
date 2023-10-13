package com.itwillbs.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.ReBoardService;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.service.ReBoardService;

@Controller
@RequestMapping("/reboard/*")
public class ReBoardController {
	
	@Inject
	private ReBoardService reBoardService;	
	
//	가상주소 http://localhost:8080/myweb/reboard/write
	@GetMapping("/write")
	public String write() {
		// board/write.jsp
		// WEB-INF/views/center/write.jsp
		return "recenter/write";
	}//
		
//	가상주소 http://localhost:8080/myweb/reboard/writePro
	@PostMapping("/writePro")
	public String writePro(@RequestParam Map<String,Object> param) {
		System.out.println("ReBoardController writePro()");
		
		System.out.println(param);
		reBoardService.insertBoard(param);
		
		// board/writePro.jsp
		// WEB-INF/views/center/writePro.jsp
		return "redirect:/reboard/list";
	}//		

	//	가상주소 http://localhost:8080/FunWeb/reboard/list
//  가상주소 http://localhost:8080/FunWeb/reboard/list?pageNum=2
//@RequestMapping(value = "/list", method = RequestMethod.GET)
@GetMapping("/list")
public String list(HttpServletRequest request,Model model) {
	System.out.println("ReBoardController list()");
	
	//한 화면에 보여줄 글개수 설정
	int pageSize = 10;
	// 현 페이지 번호 가져오기
	String pageNum=request.getParameter("pageNum");
	// 페이지 번호가 없을 경우 => "1"로 설정
	if(pageNum == null) {
		pageNum = "1";
	}
	
	// 페이지 번호 => 정수형 변경
	int currentPage = Integer.parseInt(pageNum);
	
	PageDTO pageDTO =new PageDTO();
	pageDTO.setPageSize(pageSize);
	pageDTO.setPageNum(pageNum);
	pageDTO.setCurrentPage(currentPage);
	
	
List<Map<String, Object>> boardList 
                  = reBoardService.getBoardList(pageDTO);

	// 전체 글개수 가져오기
	int count = reBoardService.getBoardCount(pageDTO);
	// 한화면에 보여줄 페이지 개수 설정
	int pageBlock = 10;
	// 시작하는 페이지 번호
	int startPage=(currentPage-1)/pageBlock*pageBlock+1;
	// 끝나는 페이지 번호
	int endPage = startPage + pageBlock -1;
	// 전체페이지 개수
	int pageCount = count/pageSize+(count%pageSize==0?0:1);
	// 끝나는 페이지 번호  전체페이지 개수 비교 
	//=> 끝나는 페이지 번호가 크면 전체페이지 개수로 변경
	if(endPage > pageCount) {
		endPage = pageCount;
	}
	
	pageDTO.setCount(count);
	pageDTO.setPageBlock(pageBlock);
	pageDTO.setStartPage(startPage);
	pageDTO.setEndPage(endPage);
	pageDTO.setPageCount(pageCount);

	
	model.addAttribute("boardList", boardList);
	model.addAttribute("pageDTO", pageDTO);
	
	// center/notice.jsp
	// WEB-INF/views/recenter/notice.jsp
	return "recenter/notice";
}//

//	가상주소 http://localhost:8080/FunWeb/reboard/content?num=
//@RequestMapping(value = "/content", method = RequestMethod.GET)
@GetMapping("/content")
public String content(@RequestParam("num") int num,Model model) {
	System.out.println("ReBoardController content()");
//int num = Integer.parseInt(request.getParameter("num"));
	//조회수 증가
  reBoardService.updateReadcount(num);
	//글가져오기
	Map<String, Object> boardDTO = reBoardService.getBoard(num);
	
	model.addAttribute("boardDTO", boardDTO);
	
	// center/content.jsp
	// WEB-INF/views/recenter/content.jsp
	return "recenter/content";
}//


//	가상주소 http://localhost:8080/FunWeb/reboard/rewrite?num=1&re_ref=1&re_lev=0&re_seq=0
//@RequestMapping(value = "/write", method = RequestMethod.GET)
@GetMapping("/rewrite")
public String rewrite(@RequestParam Map<String,Object> param, Model model) {
	System.out.println("ReBoardController rewrite()");
	
	System.out.println(param);
	
	model.addAttribute("param", param);
	
	// recenter/rewrite.jsp
	// WEB-INF/views/recenter/rewrite.jsp
	return "recenter/rewrite";
}//

//	가상주소 http://localhost:8080/FunWeb/reboard/rewritePro
//@RequestMapping(value = "/rewritePro", method = RequestMethod.POST)
@PostMapping("/rewritePro")
public String rewritePro(@RequestParam Map<String,Object> param) {
	System.out.println("ReBoardController rewritePro()");
	
	System.out.println(param);
	
	reBoardService.reInsertBoard(param);
	
	return "redirect:/reboard/list";
}//

	
}//클래스