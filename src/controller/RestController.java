package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import area.DongDTO;

@Controller
public class RestController {

	@RequestMapping("/dash")
	public @ResponseBody DongDTO dash() {
		DongDTO dong = new DongDTO();
		dong.setCode("ddd");
		dong.setName("역삼동");

		return dong;

	}

}
