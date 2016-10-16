package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.ManageService;

@Controller
@RequestMapping("/manage")
public class ManageController {
	
	
	@Autowired
	ManageService manageService;
	
	@RequestMapping("/userManage")
	public String userManage(Model model, 
			@RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword){
		// 회원 목록 가져오기
		Map<String, Object> map = manageService.userManage(page, keyword);
		model.addAttribute("map", map);
		return "/Main_Page/userManage";
	}
	
}
