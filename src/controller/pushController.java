package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import area.AreaDTO;
import area.AreaNoticeDTO;
import service.AreaDAO;
import service.AreaNoticeDAO;

@Controller
@RequestMapping("/push/")
public class pushController {

	@Autowired
	AreaNoticeDAO areanoticeDB;
	@Autowired
	AreaDAO areaDB;

	// 날짜 변환 메소드
	public String regDate(String regdate) throws ParseException {

		String DateDays = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(calendar.getTimeInMillis());
		String todayDate = sdf.format(date);
		long todayTimestamp = sdf.parse(todayDate).getTime();
		Date date2 = new Date(todayTimestamp);
		// 오늘 날짜
		String todayDate2 = sdf.format(date2);
		// 등록된 날짜 타임스탬프
		long nextdayTimestamp = sdf.parse(regdate).getTime();
		// 일수 차 (타임스탬프 기준)
		long difference = (todayTimestamp - nextdayTimestamp);
		// 일수 차 ( 날짜 기준)
		long days = difference / (24 * 60 * 60 * 1000);
		System.out.println(days);
		if ((days / 30) == 1) {
			DateDays = "한달 전";
		} else if ((days / 7) == 1) {
			DateDays = "일주일 전";
		} else if ((days / 60) == 1) {
			DateDays = "두달 전";
		} else if (days == 0) {
			DateDays = "방금 전";
		} else {
			DateDays = days + "일 전";
		}

		return DateDays;
	}

	@RequestMapping(value = "/noticeForm", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public ModelAndView noticeForm(HttpServletRequest request) throws Throwable {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("userid");
		List<AreaNoticeDTO> noticeList = areanoticeDB.selectNotice(userid);
		String sigunguCode = null;
		String dongCode = null;
		AreaDTO sigungu = null;
		AreaDTO dong = null;
		String sigunguName = null;
		String dongName = null;
		String name = null;
		int boardid = 0;
		Map<Integer, String> AreaNamemap = new HashMap<Integer, String>();
		Map<Integer, String> regDatemap = new HashMap<Integer, String>();
		for (AreaNoticeDTO code : noticeList) {
			dongCode = code.getDongCode();
			boardid = code.getBoardid();
			sigunguCode = dongCode.substring(0, 5);

			sigungu = areaDB.sigungu(sigunguCode);
			dong = areaDB.dong(dongCode);

			sigunguName = sigungu.getName();
			dongName = dong.getName();
			name = sigunguName + "    " + dongName;

			AreaNamemap.put(boardid, name);
			regDatemap.put(boardid, regDate(code.getBoard_regdate()));
		}

		System.out.println(noticeList + "---------->노티스 리스트");
		mav.addObject("regDatemap", regDatemap);
		mav.addObject("AreaNamemap", AreaNamemap);
		mav.addObject("noticeList", noticeList);
		mav.setViewName("jsp_nohead/popPush");

		return mav;
	}

	@RequestMapping(value = "updateReaded", produces = "application/text; charset=utf8")
	@ResponseBody
	public int updateReaded(@RequestParam("boardid") int boardid) throws Throwable {
		System.out.println(boardid+"---------------->");
		int result = 0;
		result = areanoticeDB.updateReaded(boardid);

		return result;
	}
}
