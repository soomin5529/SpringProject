package controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import board.BoardDTO;
import dashboard.IndustryCountDTO;
import service.DashboardDAO;

@Controller
@RequestMapping("/dashboard/")
public class DashboardController {
	String dong_code;
	
	@Autowired
	DashboardDAO dashboardDB;
	
	/*대분류 업종 top3*/
	@RequestMapping("mainCategoryTop3")
	@ResponseBody
	public List<IndustryCountDTO> mainCategoryTop3(MultipartHttpServletRequest multipart) throws Throwable {
		dong_code = multipart.getParameter("dongCode");
		List<IndustryCountDTO> MainCount = dashboardDB.getMainCategoryCount(dong_code);
		
		return MainCount;
	}
}
