package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import area.AreaDTO;
import area.AreaInMapBoundDTO;
import industry.IndustryDTO;
import service.AreaDAO;
import service.AreaLikeDAO;
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
	@Autowired
	AreaLikeDAO arealikeDB;

	// produces -> encoding문제 해결/안해주면 한글깨짐
	@RequestMapping(value = "/areaOption", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	// main화면에서 지역 선택 시 다음 옵션 동적으로 받아오기.
	public List<AreaDTO> mainOption(@RequestBody Map<String, String> areaArray) throws Throwable {
		List<AreaDTO> areaList = null;
		System.out.println(areaArray + "----------->");
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

	@RequestMapping(value = "/findDongToJson", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String findDongToJson(@RequestParam("code") String areaCode) throws Throwable {
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
		AreaDTO dong = (AreaDTO) areaDB.dong(areaCode);
		String path = dong.getLatitude() + "," + dong.getLongitude() + ":";
		for (int i = 0; i < coordinates.length; i++) {
			if (i == coordinates.length - 1) {
				path += coordinates[i].split(",")[1] + "," + coordinates[i].split(",")[0];
			} else {
				path += coordinates[i].split(",")[1] + "," + coordinates[i].split(",")[0] + "/";
			}
		}
		return path;
	}

	@RequestMapping(value = "/findSigunguToJson", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String findSigunguToJson(@RequestParam("code") String areaCode) throws Throwable {
		// 시군구 json 읽어서 좌표찾기
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("json/seoul_sgg.geojson").getFile());
		FileReader fr = new FileReader(file);
		Object sggObj = new JSONParser().parse(fr);
		JSONObject sggJsonObj = (JSONObject) sggObj;
		JSONArray arr = (JSONArray) sggJsonObj.get("features");
		String[] coordinates = null;
		for (int i = 0; i < arr.size(); i++) {
			JSONObject tmpObj = (JSONObject) arr.get(i);
			JSONObject properties = (JSONObject) tmpObj.get("properties");
			if (properties.get("ADM_SECT_C").toString().equals(areaCode)) {
				JSONObject geometry = (JSONObject) tmpObj.get("geometry");
				JSONArray coordinatesJson = (JSONArray) geometry.get("coordinates");
				coordinates = coordinatesJson.toString().substring(3, coordinatesJson.toString().length() - 3)
						.replaceAll("\\[", "").replaceAll("\\],", "/").replaceAll("\\]", "").split("/");
			}
		}
		AreaDTO sigungu = (AreaDTO) areaDB.sigungu(areaCode);
		String path = sigungu.getLatitude() + "," + sigungu.getLongitude() + ":";
		for (int i = 0; i < coordinates.length; i++) {
			if (i == coordinates.length - 1) {
				path += coordinates[i].split(",")[1] + "," + coordinates[i].split(",")[0];
			} else {
				path += coordinates[i].split(",")[1] + "," + coordinates[i].split(",")[0] + "/";
			}
		}
		
		return path;
	}

	// 화면에 보여지는 지도의 상점들 출력
	@RequestMapping(value = "/currentPageStore", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public List<StoreDTO> currentPageStore(@RequestBody Map<String, Object> params, HttpServletResponse response)
			throws IOException {
		List<StoreDTO> stores = storeDB.allStoreList(params);
		System.out.println(stores.size() + "--------> 검색된 상점 수");
		return stores;
	}

	// 화면에 보여지는 지도의 행정구역(시군구, 읍면동) 출력
	@RequestMapping(value = "/currentPageDistrict/{type}", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public List<AreaInMapBoundDTO> currentPageDistrict(@RequestBody Map<String, Object> params, @PathVariable("type") String type,
			HttpServletResponse response) throws IOException {
		System.out.println("행정구역은?------>" + type);
		List<AreaInMapBoundDTO> districts = null;
		if (type.contains("sigungu")) {
			districts = areaDB.sigunguListInMapBound(params);
			System.out.println(districts.size() + "--------> 검색된 시군구 수");
		} else {
			districts = areaDB.dongListInMapBound(params);
			System.out.println(districts.size() + "--------> 검색된 읍면동 수");
		}
		return districts;
	}
	

	@RequestMapping(value = "/insertLike", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String selectCode(@RequestParam("boardid") int boardid,
			@RequestParam("status") String status, HttpServletRequest request) throws Throwable {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		String resultOption = "";
		int num = 10;
		if (status.equals("insert")) {
			num = boardlikeDB.insertBoardLike(boardid, userid);
			resultOption = "들어감";
			System.out.println(resultOption);
		} else if (status.equals("delete")) {
			num = boardlikeDB.deleteBoardLike(boardid, userid);
			resultOption = "빼기 성공";
			System.out.println(resultOption);
		}
		return resultOption;
	}
	
	@RequestMapping(value = "/insertLikeArea", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public int insertLikeArea(@RequestParam("dongcode") String code,
			@RequestParam("status") String status, HttpServletRequest request) throws Throwable {
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		int num = 10;
		if (status.equals("insert")) {
			num = arealikeDB.insertAreaLike( userid, code);
			System.out.println("들어가무 " + num);
			
		} else if (status.equals("delete")) {
			num = arealikeDB.deleteAreaLike( userid, code);
			System.out.println("삭제대무 "+num);
		}
		return num;
	}
}