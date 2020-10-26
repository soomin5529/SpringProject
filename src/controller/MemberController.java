package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.MemberDAO;


@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	MemberDAO memberDB;

	@RequestMapping("member")
	public String member(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "main";
	}

	@RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String signIn(HttpServletRequest request) throws Throwable {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String birthdate = request.getParameter("birthdate");
		String gender = request.getParameter("gender");
		String message = null;
		int result = 0;
		System.out.println(userid + ", " + pwd + ", " + name + ", " + email + ", " + birthdate + ", " + gender);
		result = memberDB.insertmember(userid, pwd, name, email, birthdate, gender);
		System.out.println("========================================" + result);
		if (result == 1) { // 성공시
			message = "ok";
		} else { // 실패시
			message = "fail";
		}
		return message;
	}

	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		// 로그인 세션
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		boolean loginCheck = false;

		loginCheck = memberDB.loginMember(userid, pwd);
		System.out.println(loginCheck + "---------> 로그인상태");
		session.setAttribute("userid", userid);
		return "okmain";
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		memberDB.logout(session);
		return "okmain";
	}
	
	
}
