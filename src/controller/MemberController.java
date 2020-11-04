package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import member.MemberDTO;
import service.MemberDAO;

@Controller
@RequestMapping("/member/")
public class MemberController {
	public String userid = "";
	public String pwd = "";

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
	public String login(HttpServletRequest request) throws Throwable {
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
		return "redirect:/view/main";
	}

	// 로그아웃
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/view/main";
	}

	// 회원탈퇴 폼
	@RequestMapping("mypageDelete")
	public String mypageDeleteForm(MemberDTO dto, Model m) throws Exception {
		m.addAttribute("userid", userid);
		m.addAttribute("pwd", pwd);
		return "mypage/mypageDelete";
	}

	// 회원탈퇴
	@RequestMapping("deletemember")
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
		return "redirect:/view/main";
	}

	// 회원정보 수정
	@RequestMapping("updateMember")
	public String updateMember(MemberDTO dto, HttpServletRequest request, HttpSession session) throws Exception {
		String userid = (String) session.getAttribute("userid");

		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String birthdate = request.getParameter("birthdate");
		String gender = request.getParameter("gender");
		String message = null;

		int result = 0;
		System.out.println(userid + ", " + name + ", " + email + ", " + birthdate + ", " + gender);
		result = memberDB.updateMember(userid, pwd, name, email, birthdate, gender);
		System.out.println("========================================" + result);
		if (result == 1) { // 성공시
			message = "ok";
		} else { // 실패시
			message = "fail";
		}
		return "redirect:/view/main";
	}

	// 회원정보
	@RequestMapping(value = "/myPageModify", method = RequestMethod.GET)
	public String mypage(HttpSession session, Model m) throws Exception {
		MemberDTO dto = new MemberDTO();
		String userid = (String) session.getAttribute("userid");
		dto = memberDB.userInfo(userid);
		m.addAttribute("dto", dto);
		System.out.println(dto);

		return "mypage/mypageModify";
	}
}