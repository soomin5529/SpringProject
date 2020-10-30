package controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import area.AreaDTO;
import area.DongDTO;
import area.SidoDTO;
import area.SigunguDTO;
import board.BoardDTO;
import comment.CommentDTO;
import industry.MainCategoryDTO;
import service.AreaDAO;
import service.BoardDAO;
import service.CommentDAO;
import service.IndustryDAO;
import service.MemberDAO;

@Controller
@RequestMapping("/view/")
public class MainController {
	public String userid = "";

	public ModelAndView mv = new ModelAndView();

	@Autowired
	AreaDAO areaDB;
	@Autowired
	IndustryDAO industryDB;
	@Autowired
	MemberDAO memberDB;
	@Autowired
	BoardDAO boardDB;
	@Autowired
	CommentDAO commentDB;

	@ModelAttribute
	public void headProcess(HttpServletRequest request, HttpServletResponse res) {

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();

		if (request.getParameter("userid") != null) {
			session.setAttribute("userid", request.getParameter("userid"));
		}

		userid = (String) session.getAttribute("userid");

	}

	// main화면 실행 시 시도 selectBox에 값 생성
	@RequestMapping("main")
	public String main(Model model) throws Exception {

		List<SidoDTO> sidoList = areaDB.sidoList();
		model.addAttribute("sido", sidoList);
		List<AreaDTO> sigunguList = areaDB.sigunguList("11");
		model.addAttribute("sigunguList", sigunguList);
		System.out.println(sigunguList + "------------> 시군구 리스트");

		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		model.addAttribute("main", MainList);

		List<SigunguDTO> sigungu = areaDB.sigungu("11680");
		List<DongDTO> dongList = areaDB.dong("1168010100");
		model.addAttribute("sigungu", sigungu);
		model.addAttribute("dong", dongList);
		String dong_code = "";
		for (DongDTO d : dongList) {
			dong_code = d.getCode();
		}

		int count = 0;
		List<BoardDTO> articles = null;
		// board 개수 count
		count = boardDB.getBoardCount(dong_code);
		if (count > 0) {
			// board list
			articles = boardDB.getArticles(dong_code);
			System.out.println("article" + articles);
			model.addAttribute("articleList", articles);
		}
		model.addAttribute("count", count);
		int boardid = 0;
		int cnt = 0;
		List<CommentDTO> comment = null;
		// List<BoardDTO> boardidList = boardDB.getBoardid(dong_code);
		System.out.println("boardid 값은???" + articles);
		Map<Integer, List<CommentDTO>> map = new HashMap<Integer, List<CommentDTO>>();
		// 댓글 list
		for (BoardDTO b : articles) {

			boardid = b.getBoardid();
			System.out.println("boardid 값은?=====" + boardid);
			System.out.println("===========================여기냐?");
			cnt = commentDB.getCommentCount(boardid);
			System.out.println(cnt + "-----cnt count 수");
			model.addAttribute("cnt", cnt);

			comment = commentDB.getComments(boardid);
			map.put(boardid, comment);
		}
		System.out.println(map.values());
		System.out.println("map:" + map);
		model.addAttribute("map", map);
		return "main";
	}

	// main화면 실행 시 카테고리 selectBox에 값 생성
	@RequestMapping("mainCategory")
	public String mainCategory(Model model) throws Throwable {
		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		System.out.println(MainList);
		model.addAttribute("main", MainList);
		return "main";
	}

	@RequestMapping("startupKeyword")
	public String startupKeyword() throws Throwable {
		return "startupKeyword";
	}

	@RequestMapping("startupWeather")
	public String startupWeather() throws Throwable {
		return "startupWeather";
	}

	@RequestMapping("intro")
	public String intro() throws Throwable {
		return "/jsp_nohead/intro.jsp";
	}

}