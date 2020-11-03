package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import area.AreaDTO;
import area.DongDTO;
import area.SigunguDTO;
import dashboard.IndustryCountDTO;
import industry.MainCategoryDTO;
import service.AreaDAO;
import service.BoardLikeDAO;
import service.DashboardDAO;
import service.IndustryDAO;
import service.MemberDAO;
import service.StoreDAO;

@Controller
@RequestMapping("/dashboard/")
public class DashboardController {
	ModelAndView mv;
	String userid = "";
	String name = "";
	
	@Autowired
	DashboardDAO dashboardDB;
	
	@Autowired
	AreaDAO areaDB;
	
	@Autowired
	IndustryDAO industryDB;
	
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


	/* 대분류 업종 top3, 중분류 업종 top5, 주요시설 수 */
	@RequestMapping(value = "chart", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public List<IndustryCountDTO> chart(@RequestBody Map<String, String> dong_code) throws Throwable {
		List<IndustryCountDTO> result = dashboardDB.getMainCategoryCount(dong_code);
		result.addAll(dashboardDB.getMiddleCategoryCount(dong_code));
		result.addAll(dashboardDB.getFacilityCount(dong_code));
		return result;
	}
	
	@RequestMapping(value = "{dongCode}" , produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView openDashBoardOfDong(@PathVariable("dongCode") String dongCode) throws Throwable {
		mv = new ModelAndView();
		String dongName = "";
		String sigunguName = "";
		AreaDTO sigungu = null;
		AreaDTO dong=null;

		String sigunguCode = dongCode.substring(0, 5);
		System.out.println(sigunguCode);
		
		sigungu = areaDB.sigungu(sigunguCode);
		dong = areaDB.dong(dongCode);
		
		sigunguName = sigungu.getName();
		dongName = dong.getName();
		
		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		mv.addObject("main", MainList);
		mv.addObject("dongName", dongName);
		mv.addObject("sigunguName", sigunguName);
		mv.addObject("dongCode", dongCode);
		mv.addObject("name" , name);
		mv.addObject("userid", userid);
		
		mv.setViewName("jsp_nohead/dashBoard");
		return mv;

			
		

	}
	
	@RequestMapping(value = "boardWriteForm/{dongCode}" , produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView boardWriteForm(@PathVariable("dongCode") String dongCode) throws Throwable {
		mv = new ModelAndView();
		String dongName = "";
		String sigunguName = "";
		AreaDTO sigungu = null;
		AreaDTO dong=null;

		String sigunguCode = dongCode.substring(0, 5);
		
		sigungu = areaDB.sigungu(sigunguCode);
		dong = areaDB.dong(dongCode);
		
		sigunguName = sigungu.getName();
		dongName = dong.getName();
		
		mv.addObject("dongName", dongName);
		mv.addObject("sigunguName", sigunguName);
		mv.addObject("dongCode", dongCode);
		mv.addObject("userid", userid);
		mv.addObject("name", name);
		mv.setViewName("jsp_nohead/boardWriteForm");
		return mv;

			
		

	}
}
