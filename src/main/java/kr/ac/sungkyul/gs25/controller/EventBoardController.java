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
import org.springframework.web.multipart.MultipartFile;

import kr.ac.sungkyul.gs25.service.EventBoardService;
import kr.ac.sungkyul.gs25.vo.EventBoardVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

/* 
	2016-10-01  
	작업자 : 최솔빈
	개발 상황 : 완료   
   */


@Controller
@RequestMapping("/Sub_Page")
public class EventBoardController {

	@Autowired
	private EventBoardService eventboardService;
	// 리스팅
	@RequestMapping("/eventlist")
	public String list(Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") Long page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword,
			@RequestParam("store_no") Long store_no){
		
		Map<String, Object> map = eventboardService.list(page, keyword, store_no);
		model.addAttribute("map",map);
		return "/Sub_Page/event_board";
	}
	// 글 보기
	@RequestMapping("/view")
	public String view(@RequestParam("no") Long no,
			Model model){
		EventBoardVo vo = eventboardService.view(no);
		eventboardService.viewcountup(no);
		model.addAttribute("vo", vo);
		return "/Sub_Page/event_view";
	}
	// 글 삭제
	@RequestMapping("/event_delete")
	public String delete(HttpSession session,
			@RequestParam("no") Long no,
			@ModelAttribute EventBoardVo vo,
			@RequestParam("store_no") Long store_no){
		eventboardService.delete(no); // 이벤트 첨부파일 삭제
		eventboardService.delete(vo); // 이벤트 게시글 삭제
		return "redirect:/Sub_Page/eventlist?store_no="+store_no;
	}
	// 글 작성폼으로 이동
	@RequestMapping(value="/event_write", method= RequestMethod.GET)
	public String writeform(HttpSession session){
		if (session == null) {
			return "redirect:/main";
		}
		return "/Sub_Page/event_write";
	}
	// 글 작성
	@RequestMapping(value="/event_write", method= RequestMethod.POST)
	public String write(@ModelAttribute EventBoardVo vo,
			 HttpSession session,
			 @RequestParam("store_no") Long store_no,
			 MultipartFile file)throws Exception {
		if (session == null) {
			return "redirect:/main";
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		vo.setStore_no(store_no);
		vo.setUserNo(authUser.getNo()); // 현재 사용자의 번호를 vo에 삽입
		eventboardService.write(vo, file);
		return "redirect:/Sub_Page/eventlist?store_no="+store_no;
	}
}
