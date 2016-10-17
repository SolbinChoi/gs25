package kr.ac.sungkyul.gs25.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.sungkyul.gs25.service.UserService;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired UserService userService;
	
	// 회원가입 폼 이동
	@RequestMapping("/joinform")
	public String joinform(){
		return "user/joinform";
	}
	
	// 회원가입
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){
		System.out.println("join: "+vo.toString());
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	// 회원가입 성공
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "user/joinsuccess";
	}
	// 로그인 폼 이동
	@RequestMapping("/loginform")
	public String loginform(){
		return "user/loginform";
	}
	//1. Ajax 사용 시 - DB (로그인 정보 비교)
	@ResponseBody
	@RequestMapping(value = "checkLogin", method = RequestMethod.POST)
	public String checkId(String email, String password, HttpSession session) {	//Request 객체받음, script or DB 객체 분별

		UserVo authUser =  userService.login(email,  password);
		// System.out.println(authUser);

		String result = "true";
		
		if(authUser == null){
			result = "false";
		}
		else {
			//인증성공
			session.setAttribute("authUser",authUser);
			result = "true";
		}
		return result;
		
	}
	
	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("authUser");
		session.invalidate();	//로그아웃 처리 시 세션을 지워줌
		
		return "redirect:/main";
	}
	
	// 회원정보 수정 폼
	@RequestMapping("/modifyform")
	public String modifyform(HttpSession session, Model model){
		UserVo temp = (UserVo)session.getAttribute("authUser");
		
		Long no = temp.getNo(); // authUser의 no를 가져옴
		
		UserVo nvo = userService.get(no); // 회원정보 가져오기
		model.addAttribute("userVo", nvo);
		
		return "user/modifyform";
	}
	
	// 회원정보 수정
	@RequestMapping("/modify")
	public String modify(HttpSession session, @ModelAttribute UserVo vo){
		
		UserVo temp = (UserVo)session.getAttribute("authUser");
		Long no = temp.getNo();
		
		vo.setNo(no);
		
		userService.update(vo); // 사용자의 번호를 가지고 업데이트
		return "user/modifysuccess";
	}
	
	// 회원정보 찾기 폼
	@RequestMapping("/findInfo")
	public String findInfo(){
		return "/user/findinfo";
	}
	
	// 아이디 찾기
	@RequestMapping("/idFind")
	public String idFind(@ModelAttribute UserVo vo, Model model){
		String email = userService.idfind(vo); // 받아온 정보를 이용하여 이메일 값을 가져옴
		System.out.println(email);
		if(email == null){	// 계정 정보가 없을 경우
			Boolean result = false;
			model.addAttribute("result", result);
			return "/user/findInfo";
		}
		
		model.addAttribute("email",email);
		return "/user/idresult";
	}
	//비밀번호 찾기 검사
	@ResponseBody
	@RequestMapping(value = "checkPass", method = RequestMethod.POST)
	public String checkPass(@RequestBody UserVo userVo, HttpSession session) {	//Request 객체받음, script or DB 객체 분별
		String email = userService.checkPass(userVo);
		String result = "true";
		
		if(email == null){
			result = "false";
		}
		else {
			try {
				result = userService.sendEmail(email);
				// System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	// URL에서 직접 파라미터를 사용할 수 있음. URL 요청시 {}태그를 사용하여 파라미터를 입력 
	@RequestMapping(value = "/{domain}/repassword", method = RequestMethod.GET)
	public String abc(@PathVariable String domain, Model model){
		Long no = userService.passlink(domain);
//		비밀번호 변경창으로 보냄 (어떤 멤버인지는 알아야 함)
		model.addAttribute("userno", no);
		
		return "user/repassword";
	}
	
	@ResponseBody
	@RequestMapping(value ="/setPass", method = {RequestMethod.GET, RequestMethod.POST})	//재설정 비번 저장
	public String setPassword(Long no, String password){
		//state 1로 변경
		String result = userService.setpass(no,password);
		// System.out.println("controller: "+result);
		return result;
	}
	
	@RequestMapping("/passresult")	//메일 전송 완료
	public String passResult(){
		return "user/passresult";
	}
	
	@RequestMapping("/repasswordSuccess")	//비번 재설정 완료
	public String repasswordSuccess(){
		return "user/repasswordSuccess";
	}
	
	@ResponseBody	// 아이디 중복 검사
	@RequestMapping(value = "CheckEmail", method = RequestMethod.POST)
	public Map<String, Object> checkEmail(String email) {	//Request 객체받음, script or DB 객체 분별
		
		Map<String, Object> map = userService.checkEmail(email);
		
		return map;
	}
}
