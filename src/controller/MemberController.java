package controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

public class MemberController extends Action {
	public MemberDAO memberdao = new MemberDAO();
	public MemberDTO memberdto = new MemberDTO();

	public String login(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		System.out.println(userid);
		System.out.println(pwd);

		boolean check = false;
		boolean loginCheck = false;
		check = memberdao.checkId(userid);
		loginCheck = memberdao.loginMember(userid, pwd);

		System.out.println(userid);
		System.out.println(pwd);
		System.out.println(check);
		System.out.println(loginCheck);

		int loginerror = 0;
		if (check == true) {
			if (loginCheck == true) {
				request.setAttribute("userid", userid);
				System.out.println(userid);
				return "/jsp/main.jsp";
			} else {
				loginerror = 2;
				request.setAttribute("loginerror", loginerror);
				return "/jsp/main.jsp";
			}
		} else {
			loginerror = 1;
			request.setAttribute("loginerror", loginerror);
			return "/jsp/main.jsp";
		}
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
		System.out.println("========================================"+result);
		if (result == 1) { // 성공시
			System.out.println("들어오니?");
			//request.getSession().setAttribute("Message", "가입성공!");
		
		} else { // 실패시
			//request.getSession().setAttribute("ErrorMessage", "실패!");
			
		}
		
		return "/jsp/okmain.jsp";
	}

}
