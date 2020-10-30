package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import area.AreaDTO;
import area.SelectedAreaDTO;
import industry.MainCategoryDTO;
import service.AreaDAO;
import service.BoardDAO;
import service.IndustryDAO;
import service.MemberDAO;
import service.StoreDAO;
import store.StoreDTO;

@RestController
public class RESTController {

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
	StoreDAO storeDB;

	@RequestMapping("main/sanggwon/{code}")
	public String main(Model model, @PathVariable("code") String code) throws Throwable {

		// List<SidoDTO> sidoList = areaDB.sidoList();

		// 선택된 지역의 시도, 시군구, 동
		List<SelectedAreaDTO> selectedArea = areaDB.selectedArea(code);
		// 메인 카테고리 -> 선택된 지역의 메인으로 할까?
		List<MainCategoryDTO> mainList = industryDB.category_mainList();
		// 선택된 지역의 중심 좌표
		List<AreaDTO> coordinate = areaDB.areaCoordinate(code);
		// 선택된 지역의 상점들
		List<StoreDTO> stores = storeDB.storeList(code);

		// model.addAttribute("sido", sidoList);

		model.addAttribute("selectedArea", selectedArea);
		model.addAttribute("mainList", mainList);
		model.addAttribute("coordinate", coordinate);
		model.addAttribute("stores", stores);

//		
//		if (code.length() == 5) {
//			List<SigunguDTO> sigunguList = areaDB.sigungu(code);
//			model.addAttribute("sigungu", sigunguList);
//		}
//		if (code.length() == 10) {
//			List<DongDTO> dongList = areaDB.dong(code);
//			model.addAttribute("dong", dongList);
//		}
		return "board/dash";
	}
}
