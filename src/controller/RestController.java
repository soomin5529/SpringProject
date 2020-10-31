package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import area.DongDTO;
import area.SigunguDTO;
import service.AreaDAO;


@Controller
@RequestMapping("/rest/")
public class RestController {
	
	public ModelAndView mv = new ModelAndView();
	
	@Autowired
	AreaDAO areaDB;

	@RequestMapping(value = "/selectCode/{area}/{code}", produces = "application/text; charset=utf8")
	// 카테고리 selectBox에서 옵션을 받기위한 bean
	public String selectCode(@PathVariable("area") String requestArea, @PathVariable("code") String requestCode)
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
}
