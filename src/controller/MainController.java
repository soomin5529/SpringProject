package controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import service.BoardLikeDAO;
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
	@Autowired
	BoardLikeDAO boardlikeDB;

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
	@SuppressWarnings("finally")
	@RequestMapping("main")
	public String main(Model model) throws Exception {
		// 처음 시도 목록을 받아 지역 시도선택에 뿌려준다.
		List<SidoDTO> sidoList = areaDB.sidoList();
		model.addAttribute("sido", sidoList);
		// 
		List<AreaDTO> sigunguList = areaDB.sigunguList("11");
		model.addAttribute("sigunguList", sigunguList);
		System.out.println(sigunguList + "------------> 시군구 리스트");

		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		model.addAttribute("main", MainList);

		List<SigunguDTO> sigungu = areaDB.sigungu("11680");
		model.addAttribute("sigungu", sigungu);
		List<DongDTO> dongList = areaDB.dong("1168010100");
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
			// board list 뿌려주기
			articles = boardDB.getArticles(dong_code);
			System.out.println("article" + articles);
			model.addAttribute("articleList", articles);
		}
		// board 게시물 수
		model.addAttribute("count", count);
		// =================comment list========================================
		int boardid = 0;
		int cnt = 0;
		int boardLikecnt = 0;
		int commentLikecnt = 0;
		String regdate = null;
		List<CommentDTO> comment = null;
		// key 값: Boardid , value 값 : boardid 에 달린 댓글 list
		Map<Integer, List<CommentDTO>> map = new HashMap<Integer, List<CommentDTO>>();
		Map<Integer, Integer> boardLike = new HashMap<Integer, Integer>();
		Map<Integer, String> regDatemap = new HashMap<Integer, String>();
		try {
			// 댓글 list
			for (BoardDTO b : articles) {

				boardid = b.getBoardid();
				// 날짜 계산 --------------
				regdate = b.getRegDate();
				regDatemap.put(boardid, regDate(regdate));
				// 날짜 계산 --------------
				System.out.println("boardid 값은?=====" + boardid);
				cnt = commentDB.getCommentCount(boardid);
				boardLikecnt = boardlikeDB.getBoardLikeCount(boardid);
				// 댓글 개수
				model.addAttribute("cnt", cnt);

				// 댓글 list
				comment = commentDB.getComments(boardid);

				map.put(boardid, comment);
				boardLike.put(boardid, boardLikecnt);
				System.out.println("boardLike=====" + boardLike);
			}
			System.out.println("map:" + map);
			// 댓글 리스트
			model.addAttribute("map", map);
			// 좋아요 수
			model.addAttribute("boardLike", boardLike);

			model.addAttribute("regDate", regDatemap);
		} catch (NullPointerException e) {
			// TODO: handle exception
		}finally {
			return "main";
		}
	}

	// 날짜 변환 메소드
	public String regDate(String regdate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		Date today = new Date();
		Date startDate = null;

		startDate = sdf.parse(regdate);

		sdf.format(today);

		String DateDays = null;

		long calDate = today.getTime() - startDate.getTime();
		long calDateDays = calDate / (24 * 60 * 60 * 1000);
		calDateDays = Math.abs(calDateDays);
		if ((calDateDays / 30) == 1) {
			DateDays = "한달 전";
		} else if ((calDateDays / 7) == 1) {
			DateDays = "일주일 전";
		} else if ((calDateDays / 60) == 1) {
			DateDays = "두달 전";
		} else if (calDateDays == 0) {
			DateDays = "방금 전";
		} else {
			DateDays = String.valueOf(calDateDays) + "일 전";
		}
		System.out.println("date=====" + startDate);
		System.out.println("today=====" + today);
		System.out.println("두 날짜의 날짜 차이: " + DateDays);

		return DateDays;
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

	@RequestMapping("intro")
	public String intro() throws Throwable {
		return "/jsp_nohead/intro.jsp";
	}

}