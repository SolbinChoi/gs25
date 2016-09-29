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
@Controller
@RequestMapping("/Sub_Page")
public class EventBoardController {

	@Autowired
	private EventBoardService eventboardService;
	
	@RequestMapping("/eventlist")
	public String list(Model model,
			@RequestParam(value = "p", required = true, defaultValue = "1") Long page,
			@RequestParam(value = "kwd", required = false, defaultValue = "") String keyword){
		Map<String, Object> map = eventboardService.list(page, keyword);
		model.addAttribute("map",map);
		// System.out.println(map);
		return "/Sub_Page/event_board";
	}
	@RequestMapping("/view")
	public String view(@RequestParam("no") Long no,
			Model model){
		EventBoardVo vo = eventboardService.view(no);
		eventboardService.viewcountup(no);
		model.addAttribute("vo", vo);
		return "/Sub_Page/event_view";
	}
	@RequestMapping("/event_delete")
	public String delete(@RequestParam("no") Long no){
		eventboardService.delete(no);
		return "redirect:/Sub_Page/eventlist";
	}
	@RequestMapping(value="/event_write", method= RequestMethod.GET)
	public String writeform(HttpSession session){
		if (session == null) {
			return "redirect:/main";
		}
		return "/Sub_Page/event_write";
	}
	@RequestMapping("/test")
	public String test(){
		return "/Sub_Page/test";
	}
	@RequestMapping(value="/event_write", method= RequestMethod.POST)
	public String write(@ModelAttribute EventBoardVo vo,
			 HttpSession session,
			 MultipartFile file)throws Exception {
		if (session == null) {
			return "redirect:/main";
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			return "redirect:/main";
		}
		vo.setUserNo(authUser.getNo()); // 현재 사용자의 번호를 vo에 삽입
		eventboardService.write(vo, file);
		return "redirect:/Sub_Page/eventlist";
	}
}
