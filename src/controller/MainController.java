package controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import area.AreaDAO;
import area.Dong;
import area.Sido;
import area.Sigungu;
import member.MemberDAO;
import member.MemberDTO;

public class MainController extends Action {
	
	// main화면 실행 시 시도 selectBox에 값 생성
	public String main(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		AreaDAO dbpro = new AreaDAO();
		List<Sido> sidoList = dbpro.sidoList();
		System.out.println(sidoList);
		request.setAttribute("sido", sidoList);
		return "/jsp/main.jsp";
	}

	// 지역 selectBox에서 옵션을 받기위한 컨트롤러
	public String mainOption(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		AreaDAO dbpro = new AreaDAO();
		if (request.getParameter("area").equals("sido")) {
			System.out.println("시도 선택!!!");
			List<Sigungu> sigunguList = dbpro.sigunguList((String) request.getParameter("code"));
			System.out.println(sigunguList);
			request.setAttribute("option", sigunguList);
		} else {
			List<Dong> dongList = dbpro.dongList((String) request.getParameter("code"));
			request.setAttribute("option", dongList);
		}
		return "/jsp_nohead/mainOption.jsp";
	}

	public String startupKeyword(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/jsp/startupKeyword.jsp";
	}

	public String startupWeather(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/jsp/startupWeather.jsp";
	}

	public String intro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/jsp_nohead/intro.jsp";
	}
}