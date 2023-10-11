package com.itwillbs.controller;

import java.util.Map;

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

}// 클래스 


