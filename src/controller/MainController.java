package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import area.AreaDTO;
import area.DongDTO;
import area.SidoDTO;
import area.SigunguDTO;
import board.BoardDTO;
import comment.CommentDTO;
import industry.MainCategoryDTO;
import member.MemberDTO;
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

		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		model.addAttribute("main", MainList);

		List<SigunguDTO> sigunguList = areaDB.sigungu("11680");
		List<DongDTO> dongList = areaDB.dong("1168010100");
		model.addAttribute("sigungu", sigunguList);
		model.addAttribute("dong", dongList);

		// dong코드 받아오기
		String dong_code = "";
		for (DongDTO d : dongList) {
			dong_code = d.getCode();
		}

		int count = 0;
		List<BoardDTO> article = null;
		// board 개수 count
		count = boardDB.getBoardCount(dong_code);
		if (count > 0) {
			// board list
			article = boardDB.getArticles(dong_code);
			System.out.println("article" + article);
			model.addAttribute("articleList", article);
		}
		model.addAttribute("count", count);

		// boardid 받아오기
		//int []boardid = new int[article.size()];
		// 댓글 수 count
		int cnt = 0;
		int boardid = 0;
		List<CommentDTO> comment = null;
		BoardDTO b = new BoardDTO();
		for(BoardDTO bd : article) {
		boardid =	bd.getBoardid();
		}

		/*
		 * for (int i = 0; i < boardid.length; i++) { boardid[i] = b.getBoardid(); }
		 */
		//	int boardid=111111;
			System.out.println("boardid 값은???" + boardid);
			cnt = commentDB.getCommentCount(boardid);
			System.out.println(cnt + "-----cnt count 수");
			model.addAttribute("cnt", cnt);

			// 댓글 list
			comment = commentDB.getComments(boardid);
			System.out.println("comment:" + comment);
			model.addAttribute("commentList", comment);

		//}

		return "main";
	}

	@RequestMapping("main/{code}")
	public String main(Model model, @PathVariable("code") String code, Model m) throws Throwable {
		List<SidoDTO> sidoList = areaDB.sidoList();
		model.addAttribute("sido", sidoList);

		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		model.addAttribute("main", MainList);
		if (code.length() == 5) {
			List<SigunguDTO> sigunguList = areaDB.sigungu(code);
			model.addAttribute("sigungu", sigunguList);
		}
		if (code.length() == 10) {
			List<DongDTO> dongList = areaDB.dong(code);
			model.addAttribute("dong", dongList);
		}

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