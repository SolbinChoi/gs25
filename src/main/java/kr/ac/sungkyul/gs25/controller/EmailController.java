package kr.ac.sungkyul.gs25.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.sungkyul.gs25.service.SenderEmail;
import kr.ac.sungkyul.gs25.service.UserService;
import kr.ac.sungkyul.gs25.vo.EmailVo;
import kr.ac.sungkyul.gs25.vo.UserVo;

@Controller
@RequestMapping("/email")
public class EmailController {
	@Autowired
	UserService userService;
	
	@Autowired
    private SenderEmail senderEmail;
     
	public String random(){
		StringBuffer buffer = new StringBuffer();
		for(int i =0;i<=6;i++){
			int n = (int)(Math.random()*10);
			buffer.append(n);
		}
		System.out.println(buffer.toString());
		return buffer.toString();
	}
	
    @RequestMapping("/send")
    public String sendEmailAction(@ModelAttribute UserVo vo, HttpSession session) throws Exception {
//    	System.out.println("emailController: "+vo.toString());;
    	
    	String id = vo.getEmail();
    	String ranNum= random();
    	
    	EmailVo email = new EmailVo();
         
        String receiver = "csb6225@naver.com"; //받을사람의 이메일입니다.
        String subject = "GS25편의점 회원님의 임시 비밀번호입니다.";
        String content = "안녕하세요. GS25편의점입니다. 회원님의 임시 비밀번호는 "+ranNum+" 입니다. \n"
        				+ "http://localhost:8088/gs25/user/repassword"+"?ranNum="+ranNum+"&userid="+id;
        
        //random, id 값 session 전송
        
        session.setAttribute("rannum",ranNum);
        session.setAttribute("userid",id);
        
        email.setReceiver(receiver);
        email.setSubject(subject);
        email.setContent(content);
        senderEmail.SendEmail(email);

//	    return new ModelAndView("success");
        return "redirect:/user/passresult";
    }
}
