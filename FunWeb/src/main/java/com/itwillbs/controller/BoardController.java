package com.itwillbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.domain.BoardDTO;
import com.itwillbs.domain.PageDTO;
import com.itwillbs.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	//BoardService 객체생성 
	@Inject
	private BoardService boardService;
	
	//upload 폴더 파일경로 객체생성
		// name = "uploadPath"  => servlet-context.xml 에 가져옴
		@Resource(name = "uploadPath")
		private String uploadPath;

//	가상주소 http://localhost:8080/myweb/board/write
	@GetMapping("/write")
	public String write() {
		// board/write.jsp
		// WEB-INF/views/center/write.jsp
		return "center/write";
	}//
	
//	가상주소 http://localhost:8080/myweb/board/writePro
	@PostMapping("/writePro")
	public String writePro(BoardDTO boardDTO) {
		System.out.println("BoardController writePro()");
		
		boardService.insertBoard(boardDTO);
		
		// board/writePro.jsp
		// WEB-INF/views/center/writePro.jsp
		return "redirect:/board/list";
	}//	
	
	//	가상주소 http://localhost:8080/FunWeb/board/list
	//    가상주소 http://localhost:8080/FunWeb/board/list?pageNum=2
	//@RequestMapping(value = "/list", method = RequestMethod.GET)
	@GetMapping("/list")
	public String list(HttpServletRequest request,Model model) {
		System.out.println("BoardController list()");
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
	
		List<BoardDTO> boardList= boardService.getBoardList(pageDTO);

		// 전체 글개수 가져오기
		int count = boardService.getBoardCount();
		// 한화면에 보여줄 페이지 개수 설정
		int pageBlock = 10;
		// 시작하는 페이지 번호
		int startPage=(currentPage-1)/pageBlock*pageBlock+1;
		// 끝나는 페이지 번호
		int endPage = startPage + pageBlock -1;
		// 전체페이지 개수
		int pageCount = count/pageSize+(count%pageSize==0?0:1);
		// 끝나느페이지 번호 전체페이지개수 비교
		// 끝나는 페이지 번호가 크면 전체페이지 개수로 변경하는 작업 필요 
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
		// WEB-INF/views/center/notice.jsp
		return "center/notice";
	}//
	
//	가상주소 http://localhost:8080/myweb/board/content?num=
	@GetMapping("/content")
	public String content(HttpServletRequest request, Model model) {
		System.out.println("BoardCondroller content");
		int num = Integer.parseInt(request.getParameter("num"));
		//조회수 증가 
		boardService.updateReadcount(num);
		// 글가져오기 
		BoardDTO boardDTO = boardService.getBoard(num);
		
		model.addAttribute("boardDTO", boardDTO);
		
		// center/content.jsp
		// WEB-INF/views/center/content.jsp
		return "center/content";
	}//
	
//	가상주소 http://localhost:8080/myweb/board/update?num=
	@GetMapping("/update")
	public String update(HttpServletRequest request, Model model) {
		System.out.println("BoardCondroller update");
		int num = Integer.parseInt(request.getParameter("num"));
		// 글가져오기 
		BoardDTO boardDTO = boardService.getBoard(num);
		
		model.addAttribute("boardDTO", boardDTO);
		
		// center/update.jsp
		// WEB-INF/views/center/update.jsp
		return "center/update";
	}//
	
	
//	가상주소 http://localhost:8080/myweb/board/updatePro?num=
	@PostMapping("/updatePro")
	public String updatePro(BoardDTO boardDTO) {
		System.out.println("BoardCondroller updatePro");
		// num name subject content
		// 글수정
		boardService.updateBoard(boardDTO);
		
		// center/updatePro.jsp
		// WEB-INF/views/center/updatePro.jsp
		return "redirect:/board/list";
	}//	
	
	
//   ------------------파일글쓰기-------------------	
	
//	가상주소 http://localhost:8080/myweb/board/write
	@GetMapping("/fwrite")
	public String fwrite() {
		// center/fwrite.jsp
		// WEB-INF/views/center/fwrite.jsp
		return "center/fwrite";
	}//
	
	//	가상주소 http://localhost:8080/FunWeb/board/fwritePro	
//	@RequestMapping(value = "/fwritePro", method = RequestMethod.POST)
	@PostMapping("/fwritePro")
	public String fwritePro(HttpServletRequest request, MultipartFile file) throws Exception {
		System.out.println("BoardController fwritePro()");
		// name="file" -> MultipartFile file 이름 동일하게 
		
		BoardDTO boardDTO =new BoardDTO();
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		
		// 첨부파일 업로드 -> pom.xml에 프로그램 설치 
		// servlet-context.xml에 설정 
		// 파일이름 랜덤문자_첨부파일이름
		UUID uuid = UUID.randomUUID();
		String filename=uuid.toString()+"_"+file.getOriginalFilename();
		// file.getBytes()원본파일 => upload 첨부파일 복사(업로드)
		FileCopyUtils.copy(file.getBytes(), new File(uploadPath,filename) );
		
		boardDTO.setFile(filename);
		
		// boardDTO에 첨부파일 이름 저장 
		boardService.insertBoard(boardDTO);
		
		// board/fwritePro.jsp
		// WEB-INF/views/center/fwritePro.jsp
		return "redirect:/board/list";
	}//		
	
	
//  ------------------파일글수정-------------------	
//	가상주소 http://localhost:8080/myweb/board/fupdate?num=
	@GetMapping("/fupdate")
	public String fupdate(HttpServletRequest request, Model model) {
		System.out.println("BoardCondroller fupdate");
		int num = Integer.parseInt(request.getParameter("num"));
		// 글가져오기 
		BoardDTO boardDTO = boardService.getBoard(num);
		
		model.addAttribute("boardDTO", boardDTO);
		
		// center/fupdate.jsp
		// WEB-INF/views/center/update.jsp
		return "center/fupdate";
	}//	
	
	
//	가상주소 http://localhost:8080/myweb/board/fupdatePro?num=
	@PostMapping("/fupdatePro")
	public String fupdatePro(HttpServletRequest request, MultipartFile file)throws Exception{  // multi는 DTO에바로못담음 
		System.out.println("BoardCondroller fupdatePro");
		// num name subject content file oldfile
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(Integer.parseInt(request.getParameter("num")));
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		// 업로드 파일 있는지 없닌지 확인
		if(file.isEmpty()){
			// 첨부파일 없는경우 = oldfile 저장
			boardDTO.setFile(request.getParameter("oldfile"));
		}else{
			// 첨부파일있는경우
			//업로드파일이름 랜덤문자_파일이름 
			UUID uuid = UUID.randomUUID();
			String filename=uuid.toString()+"_"+file.getOriginalFilename();
			// 원본파일 -> upload 폴더 복사 
			FileCopyUtils.copy(file.getBytes(), new File(uploadPath,filename) );
			
			// boardDTO 저장
			boardDTO.setFile(filename);
		}
		
		// 글수정
		boardService.fupdateBoard(boardDTO);
		
		// center/fupdatePro.jsp
		// WEB-INF/views/center/fupdatePro.jsp
		return "redirect:/board/list";
	}//		
	
//	가상주소 http://localhost:8080/myweb/board/delete
	@GetMapping("/delete")
	public String delete(HttpServletRequest request) {
		System.out.println("BoardCondroller delete");
		int num = Integer.parseInt(request.getParameter("num"));
		// 글삭제
		boardService.deleteBoard(num);
		
		// center/fupdate.jsp
		// WEB-INF/views/center/update.jsp
		return "redirect:/board/list";
	}//		
	
}//클래스 
