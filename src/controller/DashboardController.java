package controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dashboard.IndustryCountDTO;
import service.DashboardDAO;

@Controller
@RequestMapping("/dashboard/")
public class DashboardController {
	String dong_code;

	@Autowired
	DashboardDAO dashboardDB;

	/* 대분류 업종 top3, 중분류 업종 top5, 주요시설 수 */
	@RequestMapping(value = "chart", method = RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public List<IndustryCountDTO> chart(@RequestBody Map<String, String> dong_code) throws Throwable {
		List<IndustryCountDTO> result = dashboardDB.getMainCategoryCount(dong_code);
		result.addAll(dashboardDB.getMiddleCategoryCount(dong_code));
		result.addAll(dashboardDB.getFacilityCount(dong_code));
		return result;
	}
}
