package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import area.AreaNoticeDTO;
import service.AreaNoticeDAO;
import service.MemberDAO;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	MemberDAO memberDB;
	@Autowired
	AreaNoticeDAO areanoticeDB;

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
		String name = memberDB.nameMember(userid);
		System.out.println(name);
		System.out.println(loginCheck + "---------> 로그인상태");
		System.out.println(name + "----------> name 상태");
		session.setAttribute("userid", userid);
		session.setAttribute("name", name);
		return "view/okmain";
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		session.invalidate();
		return "view/okmain";
	}

	// 회원 탈퇴
	@RequestMapping(value = "/deletemember", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	public String deletemember(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		String pwd = request.getParameter("pwd");
		boolean result = memberDB.loginMember(userid, pwd);
		String message = null;

		if (result) { // 성공시
			memberDB.deleteMember(userid, pwd);
			session.invalidate();
			message = "ok";
		} else { // 실패시
			message = "fail";
		}
		return "view/okmain";

	}

	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String updatepassword(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		String oldpwd = request.getParameter("oldpwd");
		String pwd = request.getParameter("pwd");
		System.out.println("=====================" + oldpwd);
		System.out.println("=========================비번" + pwd);
		String message = null;

		int result = 0;
		if (result == 1) { // 성공시
			memberDB.updatePassword(pwd);
			message = "수정완료";
		} else { // 실패시
			message = "실패";
		}
		return message;
	}

	
}