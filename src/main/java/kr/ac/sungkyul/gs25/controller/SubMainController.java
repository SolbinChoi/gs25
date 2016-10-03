package kr.ac.sungkyul.gs25.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.vo.ProductVo;

@Controller
@RequestMapping("/Sub_Page")
public class SubMainController {
	
	@Autowired
	ProductService productservice;
	
	@RequestMapping("/sub_index")
	public String SubMain(Model model){
		
		List<ProductVo> vo = productservice.getSubDate();
		System.out.println("vo: "+vo.toString());
		
//		List<ProductVo> vo2 = productservice.getSubPopular();
//		System.out.println(vo2.toString());
//		model.addAttribute("vo2",vo2);
		
		List<ProductVo> vo3 = productservice.getSubNew();
		System.out.println("vo3: "+vo3.toString());
		
		model.addAttribute("vo",vo);
		model.addAttribute("vo3",vo3);
		
		List<ProductVo> vo4 = productservice.getSubReco();
		System.out.println(vo4.toString());
		model.addAttribute("vo4",vo4);
		
		return "/Sub_Page/sub_index";
	}
}
