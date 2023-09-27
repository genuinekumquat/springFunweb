package com.itwillbs.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	//MemberService 객체생성 
	@Inject
	private MemberService memberService;
	
	
	//	가상주소 http://localhost:8080/FunWeb/member/insert
//	@RequestMapping(value = "insert", method = RequestMethod.GET)
	@GetMapping("/insert")
	public String memberInsert() {
		
		// member/join.jsp
		// WEB-INF/views/member/join.jsp
		return "member/join"; // forwaaard 이동방식 
	}//	

	//	가상주소 http://localhost:8080/FunWeb/member/insertPro
//	@RequestMapping(value = "/insertPro", method = RequestMethod.POST)
	@PostMapping("/insertPro")
	public String insertPro(MemberDTO memberDTO) {
		System.out.println("MemeberController insertPro");
		// 회원가입처리 
		System.out.println(memberDTO);
		memberService.insertMember(memberDTO);
		
		//주소변경하면서 이동 /member/login
		return "redirect:/member/login"; 
	}//	
	
//	가상주소 http://localhost:8080/myweb/member/login
//	-> member/login.jsp 이동
	@GetMapping("/login")
	public String login() {
		System.out.println("MemeberController login");

		// member/login.jsp
		// WEB-INF/views/member/login.jsp
		return "member/login";
	}//		


	//	가상주소 http://localhost:8080/FunWeb/member/loginPro
//	@RequestMapping(value = "/loginPro", method = RequestMethod.POST)
	@PostMapping("/loginPro")
	public String loginPro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemeberController loginPro");
		//  로그인처리
		System.out.println("받은 아이디 : " + memberDTO.getId());
		System.out.println("받은 비밀번호 : "+ memberDTO.getPass());
		
		memberDTO = memberService.userCheck(memberDTO);
		
		if(memberDTO!=null) {
			// 세션값 생성 
		   session.setAttribute("id",memberDTO.getId());
		  	
		   //주소변경하면서 이동 /member/main
		   return "redirect:/member/main";    
	    }else {
	    	return "member/msg";
	    }
		
	}//	
	//	가상주소 http://localhost:8080/FunWeb/main/main	
	@GetMapping("/main")
	public String main() {
		
		// member/join.jsp
		// WEB-INF/views/main/main.jsp
		return "main/main"; 
	}//	
	
	//	가상주소 http://localhost:8080/FunWeb/member/logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		// WEB-INF/views/member/logout
		return "redirect:/member/main";
	}//	
	
	//	가상주소 http://localhost:8080/FunWeb/member/update
	@GetMapping("/update")
	public String update(HttpSession session, Model model) {
		// session에 로그인정보의 id값 가져오기 
		String id = (String)session.getAttribute("id");
		
		MemberDTO memeberDTO = memberService.getMember(id);
		
		model.addAttribute("memberDTO", memeberDTO);
		// WEB-INF/views/member/update
		return "member/update"; 
	}//	
	
	//	가상주소 http://localhost:8080/FunWeb/member/updatePro	
	@PostMapping("/updatePro")
	public String updatePro(MemberDTO memberDTO) {
		System.out.println("MemeberController updatePro");
		// memberDTO 수정할 내용 
		System.out.println("받은 아이디 : " + memberDTO.getId());
		System.out.println("받은 비밀번호 : "+ memberDTO.getPass());
		
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		// memberDTO2 DB내용
		
		if(memberDTO2 != null) {
			// 수정 
			memberService.updateMember(memberDTO);
			// 주소변경하면서 이동 /main/main
			return "redirect:/member/main";
		}else {
			//아이디 비밀번호 틀림 => member/msg.jsp 이동
			return "member/msg";
		}
	}//
	
	//	가상주소 http://localhost:8080/FunWeb/member/delete
	@GetMapping("/delete")
	public String delete() {
		// session에 로그인정보의 id값 가져오기 
		
		// WEB-INF/views/member/delete
		return "member/delete";
	}//	
	
	//	가상주소 http://localhost:8080/FunWeb/member/deletePro	
	@PostMapping("/deletePro")
	public String deletePro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemeberController updatePro");
		// memberDTO 수정할 내용 
		System.out.println("받은 아이디 : " + memberDTO.getId());
		System.out.println("받은 비밀번호 : "+ memberDTO.getPass());
		
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		// memberDTO2 DB내용
		
		if(memberDTO2 != null) {
			// 일치하면 삭제 
			memberService.deleteMember(memberDTO);
			//세션값 삭제해야함
			session.invalidate();
			// 주소변경하면서 이동 /main/main
			return "redirect:/member/main";
		}else {
			//아이디 비밀번호 틀림 => member/msg.jsp 이동
			return "member/msg";
		}
	}//
	
	
	//	가상주소 http://localhost:8080/FunWeb/member/delete
	@GetMapping("/list")
	public String list(Model model) {
		List<MemberDTO> memberList = memberService.getMemberList();
		model.addAttribute("memberList",memberList);
		
		// WEB-INF/views/member/list
		return "member/list";
	}//	

}//클래스