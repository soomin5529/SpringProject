package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import area.AreaDTO;
import industry.IndustryDTO;
import service.AreaDAO;
import service.IndustryDAO;

@Controller
@RequestMapping("/request/")
public class AjaxController {

	@Autowired
	AreaDAO areaDB;
	@Autowired
	IndustryDAO industryDB;

	// produces -> encoding문제 해결/안해주면 한글깨짐
	@RequestMapping(value = "/areaOption", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	// main화면에서 지역 선택 시 다음 옵션 동적으로 받아오기.
	public String mainOption(@RequestParam("area") String requestArea, @RequestParam("code") String requestCode)
			throws Throwable {

		String resultOption = "<option value=\"no\" disabled selected>선택</option>";
		List<AreaDTO> areaList = null;

		if (requestArea.contains("sido")) {
			areaList = areaDB.sigunguList(requestCode);
		}
		if (requestArea.contains("sigungu")) {
			areaList = areaDB.dongList(requestCode);
		}

		System.out.println("지역리스트------>" + areaList);
		for (AreaDTO area : areaList) {
			resultOption += "<option value=\"" + area.getCode() + "\">" + area.getName() + "</option>\n";
		}

		return resultOption;
	}

	@RequestMapping(value = "/categoryOption", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	// 카테고리 selectBox에서 옵션을 받기위한 bean
	public String categoryOption(@RequestParam("category") String requestCategory,
			@RequestParam("code") String requestCode) throws Throwable {
		List<IndustryDTO> industryList = null;
		String resultOption = "<option value=\"no\" disabled selected>선택</option>";

		if (requestCategory.contains("main")) {
			industryList = industryDB.category_middleList(requestCode);
		}
		if (requestCategory.contains("middle")) {
			industryList = industryDB.category_smallList(requestCode);
		}

		System.out.println("상업리스트------>" + industryList);
		for (IndustryDTO industry : industryList) {
			resultOption += "<option value=\"" + industry.getCode() + "\">" + industry.getName() + "</option>\n";
		}

		return resultOption;
	}
}
