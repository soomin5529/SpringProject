package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import area.AreaDTO;
import area.DongDTO;
import area.SidoDTO;
import area.SigunguDTO;
import industry.MainCategoryDTO;
import service.AreaDAO;
import service.IndustryDAO;

@Controller
@RequestMapping("/view/")
public class StartupWeatherController {
	@Autowired
	AreaDAO areaDB;
	@Autowired
	IndustryDAO industryDB;
	
	public ModelAndView mv = new ModelAndView();
	
	// main화면 실행 시 시도 selectBox에 값 생성
	@RequestMapping("startupWeather")
	public String startupWeather(Model model) throws Exception {
		// 처음 시도 목록을 받아 지역 시도선택에 뿌려준다.
		List<SidoDTO> sidoList = areaDB.sidoList();
		model.addAttribute("sido", sidoList);
		System.out.println(sidoList);
		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		model.addAttribute("main", MainList);
		
		return "startupWeather";
	}
	
	/*
	 * @RequestMapping("startupWeather/search") public String
	 * startupWeatherSearch(HttpServletRequest request, Model model) throws
	 * Exception {
	 * 
	 * 
	 * String dongCode = request.getParameter("dong"); String smallCode =
	 * request.getParameter("small_category");
	 * 
	 * model.addAttribute("dongCode", dongCode); model.addAttribute("smallCode",
	 * smallCode);
	 * 
	 * return "startupWeather"; }
	 */
	
}
