package kr.ac.sungkyul.gs25.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.sungkyul.gs25.service.TestService;
import kr.ac.sungkyul.gs25.vo.TestVo;

@Controller
public class MainController {
	
	@Autowired
	TestService testService;
	
	@RequestMapping("/main")
	public String main(){
		
		return "/Main_Page/index";
	}
	
	@RequestMapping("/membership")
	public String membership(){
		return "/Main_Page/membership";
	}
	
	@RequestMapping("/companyintro")
	public String companyintro(){
		return "/Main_Page/companyintro";
	}
	
	@RequestMapping("/test")
	public String test(){
		return "/Main_Page/test";
	}
	@RequestMapping("/testwrite")
	public String testwrite(@ModelAttribute TestVo testvo){
		testService.insert(testvo);
		return "redirect:/main";
	}
}
