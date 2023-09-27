package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;

@RestController  // Ajax 전용 컨트롤러 
public class AjaxController {
	
	// 객체생성 
	@Inject
	private MemberService memberService;
	
	//	가상주소 http://localhost:8080/FunWeb/member/idCheck
//	@RequestMapping(value = "insert", method = RequestMethod.GET)
	@GetMapping("/member/idCheck")
	public ResponseEntity<String> idCheck(HttpServletRequest request) {
		//ResponseEntity:  응답출력결과 저장하는 파일 제공 
		System.out.println("AjaxController idCheck");
		
		String id = request.getParameter("id");
		
		MemberDTO memberDTO = memberService.getMember(id);
		String result ="";
		if(memberDTO != null) {
			// 아이디 있음 -> 아이디 중복
			result = "iddup";
		}else {
			// 아이디 없음 -> 아이디 사용 가능
			result = "idok";
		}
		// 출력한 결과을 가지고 join.jsp이동( 되돌아감)
		ResponseEntity<String> entity = new ResponseEntity<String>(result, HttpStatus.OK);
		
		return entity;
	}//	

	//	가상주소 http://localhost:8080/FunWeb/member/listjson
//	@RequestMapping(value = "insert", method = RequestMethod.GET)
	@GetMapping("/member/listjson")
	public ResponseEntity<List<MemberDTO>> listjson() {
		//ResponseEntity:  응답출력결과 저장하는 파일 제공 
		System.out.println("AjaxController listjson");
				
		List<MemberDTO> memberList = memberService.getMemberList();
		//List<MemberDTO>memberList -> json으로 변경하는 프로그램 설치 (jackson-databind)
		//                  ->자동으로  json으로 변경 
		
		// 출력한 결과을 가지고 join.jsp이동( 되돌아감)
		ResponseEntity<List<MemberDTO>> entity = new ResponseEntity<List<MemberDTO>>(memberList, HttpStatus.OK);
		
		return entity;
	}//		

}
