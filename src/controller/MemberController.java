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
		//System.out.println("userInfo============" + memberDB.userInfo(userid, pwd, name, email, birthdate, gender));
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

	@RequestMapping(value = "/updatemember", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String updatemember(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
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
		return "okmain";

	}
	/*
	 * @RequestMapping("updatepassword") public String
	 * updatepassword(HttpServletRequest request, HttpServletResponse response)
	 * throws Throwable { HttpSession session = request.getSession(); MemberDTO dto
	 * = (MemberDTO) session.getAttribute("userid"); String pwd =
	 * request.getParameter("pwd"); String oldpwd = dto.getPwd();
	 * 
	 * if() return null;
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(value = "/userInfo", method = RequestMethod.POST, produces =
	 * "application/text; charset=utf8") public String userInfo(HttpServletRequest
	 * request, HttpServletResponse response) throws Throwable { HttpSession session
	 * = request.getSession();
	 * 
	 * String userid = request.getParameter("userid"); String pwd =
	 * request.getParameter("pwd"); String name = request.getParameter("name");
	 * String email = request.getParameter("email"); String birthdate =
	 * request.getParameter("birthdate"); String gender =
	 * request.getParameter("gender"); String message = null; int result = 0;
	 * System.out.println(userid + ", " + pwd + ", " + name + ", " + email + ", " +
	 * birthdate + ", " + gender); result = memberDB.userInfo(userid, pwd, name,
	 * email, birthdate, gender);
	 * System.out.println("========================================" + result); if
	 * (result == 1) { // 성공시 message = "ok"; } else { // 실패시 message = "fail"; }
	 * return message; }
	 */
}
