package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import area.AreaDTO;
import area.AreaInMapBoundDTO;
import area.SelectedAreaDTO;
import mybatis.AbstractMybatis;

@Service
public class AreaDAO extends AbstractMybatis {
	String namespace = "Area";

	// 시도 출력-시도가 18개로 되어있으므로, List로 출력한다.
	public List<AreaDTO> sidoList() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".sidoList");
		} finally {
			sqlSession.close();
		}
	}

	public List<AreaDTO> sigunguList(String sidoCode) {
		Map<String, Object> sigungu = new HashMap<String, Object>();
		sigungu.put("sido_code", sidoCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".sigunguList", sigungu);
		} finally {
			sqlSession.close();
		}
	}

	public List<AreaDTO> dongList(String sigunguCode) {
		Map<String, Object> dong = new HashMap<String, Object>();
		dong.put("sigungu_code", sigunguCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".dongList", dong);
		} finally {
			sqlSession.close();
		}
	}

   public AreaDTO sigungu(String sigunguCode) {
      Map<String, Object> sigungu_code = new HashMap<String, Object>();
      sigungu_code.put("sigungu_code", sigunguCode);
      SqlSession sqlSession = getSqlSessionFactory().openSession();
      try {
         return sqlSession.selectOne(namespace + ".sigungu", sigungu_code);
      } finally {
         sqlSession.close();
      }
   }
	

   public AreaDTO dong(String dongCode) {
      Map<String, Object> dong_code = new HashMap<String, Object>();
      dong_code.put("dong_code", dongCode);
      System.out.println("동코드가 머냐" + dongCode);
      SqlSession sqlSession = getSqlSessionFactory().openSession();
      try {
         return sqlSession.selectOne(namespace + ".dong", dong_code);
      } finally {
         sqlSession.close();
      }
   }

   public List<SelectedAreaDTO> selectedArea(String dongCode) {
      Map<String, Object> dong_code = new HashMap<String, Object>();
      dong_code.put("dong_code1", dongCode);
      dong_code.put("dong_code2", dongCode);
      SqlSession sqlSession = getSqlSessionFactory().openSession();
      try {
         return sqlSession.selectList(namespace + ".selectedArea", dong_code);
      } finally {
         sqlSession.close();
      }
   }

   public List<AreaDTO> areaCoordinate(String areaCode) {
      Map<String, Object> areaCodeMap = new HashMap<String, Object>();

      String areaTable = areaCode.length() == 2 ? "SIDO" : areaCode.length() == 5 ? "SIGUNGU" : "DONG";
      areaCodeMap.put("areaTable", areaTable);

      String areaCodeName = areaCode.length() == 2 ? "SIDO_CODE"
            : areaCode.length() == 5 ? "SIGUNGU_CODE" : "DONG_CODE";
      areaCodeMap.put("areaCode", areaCodeName);

      areaCodeMap.put("areaCode", areaCode);
      SqlSession sqlSession = getSqlSessionFactory().openSession();
      try {
         return sqlSession.selectList(namespace + ".areaCoordinate", areaCodeMap);
      } finally {
         sqlSession.close();
      }
   }
   
   public List<AreaInMapBoundDTO> sigunguListInMapBound(Map<String, Object> lanlng) {
      SqlSession sqlSession = getSqlSessionFactory().openSession();
      try {
         return sqlSession.selectList(namespace + ".sigunguListInMapBound", lanlng);
      } finally {
         sqlSession.close();
      }
   }
   public List<AreaInMapBoundDTO> dongListInMapBound(Map<String, Object> lanlng) {
      SqlSession sqlSession = getSqlSessionFactory().openSession();
      try {
         return sqlSession.selectList(namespace + ".dongListInMapBound", lanlng);
      } finally {
         sqlSession.close();
      }
   }
}