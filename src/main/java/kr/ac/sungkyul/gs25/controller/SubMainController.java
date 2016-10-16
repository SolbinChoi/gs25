package kr.ac.sungkyul.gs25.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.CartListService;
import kr.ac.sungkyul.gs25.service.ProductService;
import kr.ac.sungkyul.gs25.vo.ProductVo;

@Controller
@RequestMapping("/sub")
public class SubMainController {
	
	@Autowired
	ProductService productservice;
	@Autowired
	CartListService cartservice;
	
	
	@RequestMapping("/main")
	public String SubMain(Model model, 
			@RequestParam("store_no") Long store_no,
			HttpSession session){
		
		//서브 메인에 상품 번호 추가
		session.setAttribute("store_no", store_no);
		
		//유통기한 정보
		List<ProductVo> vo = productservice.getSubDate();
		model.addAttribute("vo",vo);
		//인기상품
		
		//신상품
		List<ProductVo> vo3 = productservice.getSubNew();
		model.addAttribute("vo3",vo3);
		
		//추천상품
		List<ProductVo> vo4 = productservice.getSubReco();
		model.addAttribute("vo4",vo4);
		
		//찜목록 총 개수
		Integer TotalCount=cartservice.getCount();
		model.addAttribute("TotalCount", TotalCount);
		
		return "/Sub_Page/sub_index";
	}
	@RequestMapping("/test")
	public String test(){
		return "/Sub_Page/test";
	}
}
