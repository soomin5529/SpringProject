package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import area.SidoDTO;
import industry.MainCategoryDTO;
import service.AreaDAO;
import service.IndustryDAO;

@Controller
@RequestMapping("/view/")
public class MainController {

	public ModelAndView mv = new ModelAndView();

	@Autowired
	AreaDAO areaDB;

	@Autowired
	IndustryDAO industryDB;

	// main화면 실행 시 시도 selectBox에 값 생성
	@RequestMapping("main")
	public String main(Model model) throws Throwable {
		List<SidoDTO> sidoList = areaDB.sidoList();
		model.addAttribute("sido", sidoList);
		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		model.addAttribute("main", MainList);
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