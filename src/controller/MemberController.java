package controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;

public class MemberController extends Action {
	public MemberDAO memberdao = new MemberDAO();
	public MemberDTO memberdto = new MemberDTO();

	public String login(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");

		// 로그인 세션
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		boolean loginCheck = false;

		loginCheck = memberdao.loginMember(userid, pwd);
		System.out.println(loginCheck + "---------> 로그인상태" );
		if (loginCheck == true) {
			session.setAttribute("userid", userid);
			return "/jsp/okmain.jsp";
		} else {
			request.setAttribute("msg", "아이디나 비밀번호가 올바르지 않습니다.");
			return "/jsp_nohead/idCheck.jsp";
		}
	}
	
	public String loginCheck(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		System.out.println("--------------->쿼리 부르기 전");
		boolean loginCheck = false;
		loginCheck = memberdao.loginMember(userid, pwd);
		if (loginCheck == true) {
			request.setAttribute("msg", "ok");
		} else {
			request.setAttribute("msg", "아이디나 비밀번호가 올바르지 않습니다.");
		}
		System.out.println("--------------->쿼리 부른 후");
		return "/jsp_nohead/idCheck.jsp";
	}
	
	public String checkId(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		boolean result = memberdao.checkId(request.getParameter("userid"));
		if(result) {
			request.setAttribute("msg", "이미 존재하는 아이디입니다.");
		}else {
			request.setAttribute("msg", "사용가능합니다.");
		}
		return "/jsp_nohead/idCheck.jsp";
	}

	public String member(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/jsp/main.jsp";
	}

	public String signIn(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String birthdate = request.getParameter("birthdate");
		String gender = request.getParameter("gender");

		int result = 0;
		result = memberdao.insertmember(userid, pwd, name, email, birthdate, gender);
		System.out.println("========================================" + result);
		if (result == 1) { // 성공시
			request.setAttribute("Message", "가입성공!");
		} else { // 실패시
			request.setAttribute("Message", "실패!");
		}
		return "/jsp/okmain.jsp";
	}

}
