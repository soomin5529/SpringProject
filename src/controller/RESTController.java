package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import area.DongDTO;

@RestController
public class RESTController {
	
	private ModelAndView mv;
	
	@RequestMapping("/view/main/dong/{dongCode}")
	@ResponseBody
	public ModelAndView openDashBoardOfDong(@PathVariable("dongCode") String dongCode) {
		mv = new ModelAndView();
		System.out.println(dongCode);
		mv.setViewName("/rest/dashBoard");
		mv.addObject("dong",dongCode);
		return mv;
	}

}
