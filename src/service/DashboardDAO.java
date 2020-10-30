package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import dashboard.IndustryCountDTO;

@Service
public class DashboardDAO extends AbstractMybatis{
	String namespace = "Dashboard";
	
	// 동코드별 대분류 업종수 top3 출력
	public List<IndustryCountDTO> getMainCategoryCount (String dongCode) {
		Map<String, Object> dong_code = new HashMap<String, Object>();
		dong_code.put("dong_code", dongCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".getMainCategoryCount", dong_code);
		} finally {
			sqlSession.close();
		}
	}
	
	// 동코드별 중분류 업종수 top5 출력
	public List<IndustryCountDTO> getMiddleCategoryCount (String dongCode) {
		Map<String, Object> dong_code = new HashMap<String, Object>();
		dong_code.put("dong_code", dongCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".getMiddleCategoryCount", dong_code);
		} finally {
			sqlSession.close();
		}
	}
		
	// 동코드별 주요시설 수 출력
	public List<IndustryCountDTO> getFacilityCount (String dongCode) {
		Map<String, Object> dong_code = new HashMap<String, Object>();
		dong_code.put("dong_code", dongCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".getFacilityCount", dong_code);
		} finally {
			sqlSession.close();
		}
	}	
}
