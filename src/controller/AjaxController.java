package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import area.AreaDTO;
import area.DongDTO;
import area.SigunguDTO;
import industry.IndustryDTO;
import service.AreaDAO;
import service.BoardLikeDAO;
import service.IndustryDAO;
import service.MemberDAO;
import service.StoreDAO;
import store.StoreDTO;

@Controller
@RequestMapping("/request/")
public class AjaxController {

	@Autowired
	AreaDAO areaDB;
	@Autowired
	IndustryDAO industryDB;
	@Autowired
	MemberDAO memberDB;
	@Autowired
	StoreDAO storeDB;
	@Autowired
	BoardLikeDAO boardlikeDB;

	// produces -> encoding문제 해결/안해주면 한글깨짐
	@RequestMapping(value = "/areaOption", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	// main화면에서 지역 선택 시 다음 옵션 동적으로 받아오기.
	public List<AreaDTO> mainOption(@RequestBody Map<String, String> areaArray)
			throws Throwable {
		List<AreaDTO> areaList = null;
		System.out.println(areaArray+"----------->");
		if (areaArray.get("type").contains("sido")) {
			areaList = areaDB.sigunguList(areaArray.get("code"));
		}
		if (areaArray.get("type").contains("sigungu")) {
			areaList = areaDB.dongList(areaArray.get("code"));
		}
		System.out.println("지역리스트------>" + areaList);
		return areaList;
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

	@RequestMapping(value = "/selectCode", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	// 카테고리 selectBox에서 옵션을 받기위한 bean
	public String selectCode(@RequestParam("area") String requestArea, @RequestParam("code") String requestCode)
			throws Throwable {
		List<SigunguDTO> areaList = null;
		List<DongDTO> areaList2 = null;
		String resultOption = "";

		if (requestArea.contains("sigungu")) {
			areaList = areaDB.sigungu(requestCode);
			System.out.println("구코드 이름------>" + areaList);

			for (SigunguDTO area : areaList) {
				resultOption += area.getName();
				System.out.println("resultOption구---" + resultOption);

			}
		}

		if (requestArea.contains("dong")) {
			areaList2 = areaDB.dong(requestCode);
			System.out.println("동코드 이름------>" + areaList2);

			for (DongDTO area : areaList2) {
				resultOption += area.getName();
				System.out.println("resultOption동---" + resultOption);
			}
		}

		return resultOption;
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String loginCheck(@RequestParam("userid") String requestId, @RequestParam("pwd") String requestPwd)
			throws Throwable {
		boolean loginCheck = false;
		String result = null;
		loginCheck = memberDB.loginMember(requestId, requestPwd);
		if (loginCheck == true) {
			result = "ok";
		} else {
			result = "아이디나 비밀번호가 올바르지 않습니다.";
		}
		return result;
	}

	@RequestMapping(value = "/checkId", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String checkId(@RequestParam("userid") String requestId) throws Throwable {
		boolean checkId = memberDB.checkId(requestId);
		String result = null;

		if (checkId) {
			result = "이미 존재하는 아이디입니다.";
		} else {
			result = "사용가능합니다.";
		}
		return result;
	}

	@RequestMapping(value = "/findAreaToJson", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String findAreaToJson(@RequestParam("code") String areaCode, @RequestParam("dong") String dongName)
			throws Throwable {
		// 서울시 읍면동 json 읽어서 좌표찾기
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("json/seoul_emd.json").getFile());
		FileReader fr = new FileReader(file);
		Object seoulEMDObj = new JSONParser().parse(fr);
		JSONObject seoulEMDjsonObj = (JSONObject) seoulEMDObj;

		// geoJson의 동코드와 우리 DB의 코드가 차이가 있어 보정을 해준다.
		Object key = (Integer.parseInt(areaCode) / 100) + "";
		String tmpCoordinates = seoulEMDjsonObj.get(key).toString().substring(2,
				seoulEMDjsonObj.get(key).toString().length() - 2);
		String[] coordinates = tmpCoordinates.replaceAll("\\[", "").replaceAll("\\],", "/").replaceAll("\\]", "")
				.split("/");
		String path = "";
		for (int i = 0; i < coordinates.length; i++) {
			if (i == coordinates.length - 1) {
				path += coordinates[i].split(",")[1] + "," + coordinates[i].split(",")[0];
			} else {
				path += coordinates[i].split(",")[1] + "," + coordinates[i].split(",")[0] + "/";
			}
		}
		return path;
	}

	@RequestMapping(value = "/extractStoreFromDong", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String extractStoreFromDong(@RequestParam("code") String requestDongCode,
			@RequestParam("dong") String requestDongName, Model model) {
		List<StoreDTO> stores = storeDB.storeList(requestDongCode);
		return "";
	}

	@RequestMapping(value = "/currentPageStore", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public List<StoreDTO> currentPageStore(@RequestBody Map<String, Object> params, HttpServletResponse response)
			throws IOException {
		List<StoreDTO> stores = storeDB.allStoreList(params);
		System.out.println(stores.size() + "--------> 검색된 상점수");
		return stores;
	}
}
