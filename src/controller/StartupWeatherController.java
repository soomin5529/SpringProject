package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import area.AreaDTO;
import area.SidoDTO;
import industry.IndustryDTO;
import industry.MainCategoryDTO;
import service.AreaDAO;
import service.IndustryDAO;
import service.StartupWeatherDAO;
import startupWeather.IndustryCountDTO;
import startupWeather.NewAndFailDTO;
import startupWeather.PopulationDTO;
import startupWeather.SalesAvgDTO;

@Controller
@RequestMapping("/view/")
public class StartupWeatherController {
	@Autowired
	AreaDAO areaDB;
	@Autowired
	IndustryDAO industryDB;
	@Autowired
	StartupWeatherDAO startupWeatherDB;

	public ModelAndView mv = new ModelAndView();

	// main화면 실행 시 시도 selectBox에 값 생성
	@RequestMapping("startupWeather")
	public String startupWeather(Model model) throws Exception { // 처음 시도 목록을 받아 지역 시도선택에 뿌려준다.
		List<SidoDTO> sidoList = areaDB.sidoList();
		model.addAttribute("sido", sidoList);
		System.out.println(sidoList);

		List<MainCategoryDTO> MainList = industryDB.category_mainList();
		model.addAttribute("main", MainList);

		return "view/startupWeather";
	}

	// 창업기상도 검색 결과 출력
	@RequestMapping(value = "/startupWeatherSearch", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public ModelAndView startupWeatherSearch(@RequestParam("dongCode") String dongCode,
			@RequestParam("smallCode") String smallCode, @RequestParam("funds") int funds) throws Exception {
		ModelAndView mv = new ModelAndView();
		AreaDTO dong = areaDB.dong(dongCode);
		String guCode = (dong.getCode()).substring(0, 5);
		IndustryDTO industry = startupWeatherDB.smallcategorySearch(smallCode);
		String small = industry.getCode();
		String mainCode = (industry.getCode()).substring(0, 1);

		List<IndustryCountDTO> IndustryCount = startupWeatherDB.getIndustryCount(guCode, small);
		SalesAvgDTO salesAvg = startupWeatherDB.getSalesAvg(guCode, mainCode);
		NewAndFailDTO newAndFailRate = startupWeatherDB.getNewAndFail(guCode);
		PopulationDTO Population = startupWeatherDB.getPopulation(guCode);

		mv.addObject("dong", dong);
		mv.addObject("industry", industry);
		mv.addObject("IndustryCount", IndustryCount);
		mv.addObject("salesAvg", salesAvg);
		mv.addObject("newAndFailRate", newAndFailRate);
		mv.addObject("Population", Population);
		mv.setViewName("jsp_nohead/startupWeatherSearch");
		return mv;
	}

	// 레이더차트 출력
	@RequestMapping(value = "/sendRadarChart", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String,Integer> sendRadarChart(@RequestBody Map<String, String> data) throws Exception {
		HashMap<String,Integer> pointList = new HashMap<String,Integer>();
		AreaDTO dong = areaDB.dong(data.get("dongCode"));
		String guCode = (dong.getCode()).substring(0, 5);
		IndustryDTO industry = startupWeatherDB.smallcategorySearch(data.get("smallCode"));
		String small = industry.getCode();
		String mainCode = (industry.getCode()).substring(0, 1);
		List<IndustryCountDTO> IndustryCount = startupWeatherDB.getIndustryCount(guCode, small);
		SalesAvgDTO salesAvg = startupWeatherDB.getSalesAvg(guCode, mainCode);
		NewAndFailDTO newAndFailRate = startupWeatherDB.getNewAndFail(guCode);
		PopulationDTO Population = startupWeatherDB.getPopulation(guCode);
		int rare; 									// 업소수(희소성)
		long sales = salesAvg.getAvgSum(); 			// 매출평균(영업력)
		long safety = newAndFailRate.getFailRate(); // 개폐업률(안정성)
		int persistence; 							// 평균영업연수(지속성)
		long population = Population.getDensity(); // 인구밀도(집객력)
		
		//업소수 0인 경우 index 안나오는 경우 해결
		if(IndustryCount.size() == 2) {
			rare = IndustryCount.get(1).getCount();
		}else{
			rare = 0;
		}
		
		// 업소수(희소성)
		if (rare <= 5) {
			pointList.put("rare", 20);
		} else if (rare <= 10) {
			pointList.put("rare", 15);
		} else if (rare <= 50) {
			pointList.put("rare", 10);
		} else if (rare < 100) {
			pointList.put("rare", 5);
		} else {
			pointList.put("rare", 0);
		}
		
		// 매출평균(영업력)
		if (sales >= 3000) {
			pointList.put("sales", 20);
		} else if (sales >= 2000) {
			pointList.put("sales", 15);
		} else if (sales >= 1000) {
			pointList.put("sales", 10);
		} else if (sales >= 500) {
			pointList.put("sales", 5);
		} else {
			pointList.put("sales", 0);
		}
		
		// 개폐업률(안정성)
		if (safety <= 10) {
			pointList.put("safety", 20);
		} else if (safety <= 20) {
			pointList.put("safety", 15);
		} else if (safety <= 30) {
			pointList.put("safety", 10);
		} else if (safety < 40) {
			pointList.put("safety", 5);
		} else {
			pointList.put("safety", 0);
		}
		
		//평균영업연수(지속성)
		pointList.put("persistence", 10);
		
		//인구밀도(집객력)
		if (population >= 15000) {
			pointList.put("population", 20);
		} else if (population >= 11000) {
			pointList.put("population", 15);
		} else if (population >= 7000) {
			pointList.put("population", 10);
		} else if (population >= 3000) {
			pointList.put("population", 5);
		} else {
			pointList.put("population", 0);
		}
		 
		int total = pointList.get("rare") + pointList.get("sales") + pointList.get("safety") + pointList.get("persistence") + pointList.get("population");
		pointList.put("total", total); // 합계
		
		System.out.println("pointList" + pointList);
		return pointList;
	}
}