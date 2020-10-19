package controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

public class MainController extends Action {
	
	public void headProcess(HttpServletRequest request, HttpServletResponse res) {

	}
	public String main(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/jsp/main.jsp";
	}

	
}