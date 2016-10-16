package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.gs25.service.SubManageService;
import kr.ac.sungkyul.gs25.vo.EventBoardVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/manage")
public class SubManageController {

	@Autowired
	SubManageService submanagerService;
	
	// 이벤트 리스트 관리
	@RequestMapping("/subManage")
	public String subManage(){
		return "/Sub_Page/sub_manage";
	}
	
	@RequestMapping("/eventList")
	public Object eventList(Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") String page,
			HttpSession session,
			@RequestParam("store_no") Long store_no){
		
		if (session == null) {
			return "redirect:/sub/main";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/sub/main";
		}
		// 이벤트 목록 가져오기
		Map<String, Object> map = submanagerService.getEventList(page, store_no);

		map.put("code", "OK");
		model.addAttribute("map", map);
		return map;
	}
	
	// 이벤트 번호를 가지고 삭제
	@RequestMapping("/eventDelete")
	public String eventDelete(@RequestParam("no") Long no, 
			@ModelAttribute EventBoardVo vo,
			@RequestParam("store_no") Long store_no){
		submanagerService.eventDelete(no); // 첨부파일 삭제
		submanagerService.eventDelete(vo); // 게시물 삭제
		return "redirect:/sub/main?store_no="+store_no;
	}
	@RequestMapping("/guestList")
	public String guestbookList(Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") String page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword,
			HttpSession session,
			@RequestParam("store_no") Long store_no){
		if (session == null) {
			return "redirect:/sub/main";
		}
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/sub/main";
		}
		// 방명록 목록 가져오기
		Map<String, Object> map =submanagerService.getGuestbookList(page, keyword, store_no);
		model.addAttribute("map", map);
		
		return "/Sub_Page/sub_manage";
	}
}
