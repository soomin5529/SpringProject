package controller;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import area.DongDTO;
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
@RequestMapping("/board/")
public class BoardController {
	public String boardid = "";
	public String userid = "";
	public String dong_code = "";
	// boardid, userid, dong_code 는 view 에서 값 받아야함
	public String remoteId = "";
	public ModelAndView mv = new ModelAndView();

	@Autowired
	MemberDAO memberDB;

	@Autowired
	BoardDAO boardDB;

	@Autowired
	AreaDAO areaDB;

	@Autowired
	IndustryDAO industryDB;
	
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
		remoteId = request.getRemoteAddr();

		/*
		 * if (request.getParameter("dong_code") != null) {
		 * session.setAttribute("dong_code", request.getParameter("dong_code")); }
		 */
		if (request.getParameter("boardid") != null) {
			session.setAttribute("boardid", request.getParameter("boardid"));
		}
		if (request.getParameter("userid") != null) {
			session.setAttribute("userid", request.getParameter("userid"));
		}

		
		userid = (String) session.getAttribute("userid");

	}

	@RequestMapping("boardwriteForm")
	public String writeUploadForm(BoardDTO article, Model m) throws Exception {
		m.addAttribute("userid", userid);
	//	m.addAttribute("dong_code", dong_code);
		

		return "board/writeUploadForm";
	}

	@RequestMapping("writeUploadPro")
	public String writeUploadPro(MultipartHttpServletRequest multipart, BoardDTO article) throws Exception {
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	    String regDate = sdf.format(today);
	    
		MultipartFile multi = multipart.getFile("uploadfile");
		String filename = multi.getOriginalFilename();
		dong_code = multipart.getParameter("dongCode");
		System.out.println("동코드가 나오니???---------------" + dong_code);

		if (filename != null && !filename.equals("")) {
			String uploadpath = multipart.getRealPath("/") + "/uploadFile";
			FileCopyUtils.copy(multi.getInputStream(),
					new FileOutputStream(uploadpath + "/" + multi.getOriginalFilename()));
			article.setFilename(filename);
			// article.setFilesize((int) multi.getSize());
		} else {
			article.setFilename("");
			// article.setFilesize(0);
		}
		// article.setIp(remoteId);
		// article.setBoardid(boardid);
		article.setDong_code(dong_code);
		article.setRegDate(regDate);

		System.out.println("전" + article);
	     boardDB.insertArticle(article);
		return "redirect:/view/main";
		// jsp로 보내지 않고 바로 view 로
	}
	
	@RequestMapping("commentUploadPro")
	public String commentUploadPro(CommentDTO article, HttpServletRequest request) throws Exception {
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
	    String regDate = sdf.format(today);
		
        String userid= request.getParameter("userid");
        String name = request.getParameter("name");
        int boardid = Integer.parseInt(request.getParameter("boardid"));
        String content = request.getParameter("content");
        article.setBoardid(boardid);
        article.setContent(content);
        article.setName(name);
        article.setUserid(userid);
        article.setRegDate(regDate);
		System.out.println("comment article------" + article);
	    commentDB.insertComment(article);
		return "redirect:/view/main";
		// jsp로 보내지 않고 바로 view 로
	}

	@RequestMapping("boardList")
	public ModelAndView list() throws Exception {
		int count = 0;
		List<BoardDTO> article = null;

		count = boardDB.getBoardCount(dong_code);
		if (count > 0) {
			article = boardDB.getArticles(dong_code);
		}
		mv.clear();
		mv.addObject("count", count);
		mv.addObject("articleList", article);
		mv.setViewName("board/boardList");

		return mv;
	}

	@RequestMapping("dashBoard/{dong_code}")
	public String dashBoard(@PathVariable("dong_code") String dong_code, Model m) throws Exception {
		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		m.addAttribute("main", MainList);
		System.out.println("메인리스트 나와요" + MainList);

		List<SigunguDTO> sigunguList = areaDB.sigungu(dong_code);
		// List<DongDTO> dongList = areaDB.dong(dong_code);
		m.addAttribute("sigungu", sigunguList);
		System.out.println("시군구 나와요" + sigunguList);

		return "board/dashBoard";
	}

	@RequestMapping("deletePro")
	public String deletePro(String userid, String dong_code, String boardid, Model m) throws Exception {
		int delete_ok = boardDB.deleteArticle(userid, dong_code, boardid);
		m.addAttribute("delete_ok", delete_ok);
		return "board/deletePro";
	}

	/*
	 * @RequestMapping("content/{num}") public String content(@PathVariable("num")
	 * int num, Model m) throws Exception { BoardDTO article =
	 * boardDB.getArticle(num, boardid, true);
	 * 
	 * m.addAttribute("article", article);
	 * 
	 * return "board/content"; }
	 */

}
