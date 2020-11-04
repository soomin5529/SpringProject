package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import area.AreaDTO;
import industry.IndustryDTO;
import industry.IndustryRankDTO;
import service.AreaDAO;
import service.AreaLikeDAO;
import service.DashboardDAO;
import service.IndustryDAO;
import service.StoreDAO;
import store.StoreDTO;

@Controller
@RequestMapping("/dashBoard/")
public class DashBoardController {
   ModelAndView mv;
   String userid = "";
   String name = "";

   @Autowired
   StoreDAO storeDB;
   @Autowired
   DashboardDAO dashboardDB;
   @Autowired
   AreaDAO areaDB;
   @Autowired
   IndustryDAO industryDB;
   @Autowired
   AreaLikeDAO arealikeDB;

   @ModelAttribute
   public void headProcess(HttpServletRequest request, HttpServletResponse res) {
      try {
         request.setCharacterEncoding("utf-8");
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      HttpSession session = request.getSession();
      if (request.getParameter("userid") != null) {
         session.setAttribute("userid", request.getParameter("userid"));
      }
      userid = (String) session.getAttribute("userid");
   }

   @RequestMapping(value = "/douhnutchart", method = RequestMethod.POST, produces = "application/json; charset=utf8")
   @ResponseBody
   public List<IndustryRankDTO> drawDouhnutChartOnDashBoard(@RequestBody Map<String, String> dongCode) {
      // 선택된 동의 maincategoryRank (산업-대분류 랭킹 TOP3.)
      List<IndustryRankDTO> maincategoryRank = industryDB.maincategoryRank(dongCode.get("code"));

      return maincategoryRank;
   }

   @RequestMapping(value = "/barchart", method = RequestMethod.POST, produces = "application/json; charset=utf8")
   @ResponseBody
   public List<IndustryRankDTO> drawBarChartOnDashBoard(@RequestBody Map<String, String> dongCode) {
      // 선택된 동의 middlecategoryRank (산업-중분류 랭킹 TOP3.)
      List<IndustryRankDTO> middlecategoryRank = industryDB.middlecategoryRank(dongCode.get("code"));

      return middlecategoryRank;
   }

   @RequestMapping(value = "/frenchise", method = RequestMethod.POST, produces = "application/json; charset=utf8")
   @ResponseBody
   public List<IndustryRankDTO> frenchiseRank(@RequestBody Map<String, String> dongCode) {
      List<IndustryRankDTO> frenchiseRank = industryDB.frenchiseRank(dongCode.get("code"));

      return frenchiseRank;
   }

   @RequestMapping(value = "/middlecategory", method = RequestMethod.POST, produces = "application/json; charset=utf8")
   @ResponseBody
   public List<IndustryDTO> middlecategory(@RequestBody Map<String, String> data) {
      List<IndustryDTO> middlecategory = industryDB.middlecategory(data);

      return middlecategory;
   }

   @RequestMapping(value = "/smallcategory", method = RequestMethod.POST, produces = "application/json; charset=utf8")
   @ResponseBody
   public List<IndustryDTO> smallcategory(@RequestBody Map<String, String> data) {
      List<IndustryDTO> smallcategory = industryDB.smallcategory(data);

      return smallcategory;
   }

   @RequestMapping(value = "/currentDongStore", method = RequestMethod.POST, produces = "application/json; charset=utf8")
   @ResponseBody
   public List<StoreDTO> currentDongStore(@RequestBody Map<String, String> data) {
      System.out.println(data);
      // data -> 위도경도 범위 & 동 코드 & 업종분류 및 코드
      List<StoreDTO> storeList = storeDB.storeListInDongBound(data);
      return storeList;
   }

   @RequestMapping(value = "dong/{dongCode}", method = RequestMethod.POST, produces = "application/text; charset=utf8")
   @ResponseBody
   public ModelAndView openDashBoard(@PathVariable("dongCode") String dongCode, HttpServletRequest request)
         throws Exception {
      System.out.println("***Open DashBoard!***");
      mv = new ModelAndView();

      // 동 코드로 시군구 코드를 만든다
      String sigunguCode = dongCode.substring(0, 5);
      // 행정구역(시군구, 읍면동) 이름
      AreaDTO sigungu = areaDB.sigungu(sigunguCode);
      AreaDTO dong = areaDB.dong(dongCode);
      // 선택된 동에 있는 maincategory (산업-대분류)
      List<IndustryDTO> maincategory = industryDB.maincategory(dongCode);
      // ModelAndView Setting
      mv.setViewName("jsp_nohead/dashBoard");
      mv.addObject("sigungu", sigungu);
      mv.addObject("dong", dong);
      mv.addObject("maincategory", maincategory);
      mv.addObject("userid", userid);

      // 로그인이 되어 있다면, 관심지역 설정을 받아온다(true, false)
      if (userid != null) {
         boolean result = arealikeDB.checkAreaLike(userid, dongCode);
         mv.addObject("checkAreaLike", result);
      }

      return mv;
   }

   @RequestMapping(value = "boardWriteForm/{dongCode}", produces = "application/text; charset=utf8")
   @ResponseBody
   public ModelAndView boardWriteForm(@PathVariable("dongCode") String dongCode, HttpServletRequest request)
         throws Throwable {
      mv = new ModelAndView();
      String dongName = "";
      String sigunguName = "";
      AreaDTO sigungu = null;
      AreaDTO dong = null;

      HttpSession session = request.getSession();
      String userid = (String) session.getAttribute("userid");
      String name = (String) session.getAttribute("name");

      String sigunguCode = dongCode.substring(0, 5);

      sigungu = areaDB.sigungu(sigunguCode);
      dong = areaDB.dong(dongCode);

      sigunguName = sigungu.getName();
      dongName = dong.getName();

      mv.addObject("dongName", dongName);
      mv.addObject("sigunguName", sigunguName);
      mv.addObject("dongCode", dongCode);
      mv.addObject("userid", userid);
      mv.addObject("name", name);
      mv.setViewName("jsp_nohead/boardWriteForm");
      return mv;

   }

  
}