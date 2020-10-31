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
	public List<IndustryCountDTO> getMainCategoryCount (Map<String, String> dongCode) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".getMainCategoryCount", dongCode);
		} finally {
			sqlSession.close();
		}
	}
	
	// 동코드별 중분류 업종수 top5 출력
	public List<IndustryCountDTO> getMiddleCategoryCount (Map<String, String> dongCode) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".getMiddleCategoryCount", dongCode);
		} finally {
			sqlSession.close();
		}
	}
		
	// 동코드별 주요시설 수 출력
	public List<IndustryCountDTO> getFacilityCount(Map<String, String> dongCode) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".getFacilityCount", dongCode);
		} finally {
			sqlSession.close();
		}
	}	
}
