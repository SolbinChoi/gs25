package kr.ac.sungkyul.gs25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Sub_Page")
public class SubMainController {
	@RequestMapping("/sub_index")
	public String SubMain(){
		
		return "/Sub_Page/sub_index";
	}
}
