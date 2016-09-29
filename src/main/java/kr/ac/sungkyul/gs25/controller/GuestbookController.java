package kr.ac.sungkyul.gs25.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.GuestbookService;
import kr.ac.sungkyul.gs25.vo.GuestbookVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/Sub_Page")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/list")
	public String list(Model model, 
			@RequestParam(value="p",required=true, defaultValue="1") Long page,
			@RequestParam(value="store_no",required=true, defaultValue="") Long store_no){
		Map<String, Object> map = guestbookService.list(page, store_no); // email이랑 방명록을 추가
		model.addAttribute("map", map);
		
		return "/Sub_Page/guestbook_list";
	}
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@ModelAttribute GuestbookVo vo,
			@RequestParam(value="store_no",required=true, defaultValue="") Long store_no,
			HttpSession session){
		if(session == null){
			return "redirect:/main";
		}
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		vo.setUser_no(authUser.getNo());
		vo.setStore_no(store_no);
		guestbookService.write(vo);
		return "redirect:/Sub_Page/list";
	}
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestbookVo vo){
		guestbookService.delete(vo);
		return "redirect:/Sub_Page/list";		
	}
}
