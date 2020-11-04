package service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import industry.IndustryDTO;
import industry.IndustryRankDTO;
import mybatis.AbstractMybatis;
import startupWeather.IndustryCountDTO;
import startupWeather.NewAndFailDTO;
import startupWeather.OperateAvgDTO;
import startupWeather.PopulationDTO;
import startupWeather.SalesAvgDTO;

@Service
public class StartupWeatherDAO extends AbstractMybatis {
	String namespace = "StartupWeather";
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	/* 업종이름 출력 */
	public IndustryDTO smallcategorySearch(String smallCode){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectOne(namespace + ".smallcategorySearch", smallCode);
		} finally {
			sqlSession.close();
		}
	}
	
	/* 시군구별 업종소분류별 업소수 */
	public List<IndustryCountDTO> getIndustryCount(String guCode, String smallCode) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<IndustryCountDTO> result;
		try {
			String statement = namespace + ".getIndustryCount";
			map.put("guCode", guCode);
			map.put("smallCode", smallCode);
			result = sqlSession.selectList(statement, map);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	/* 시군구별 업종대분류별 매출평균 */
	public SalesAvgDTO getSalesAvg(String guCode, String mainCode) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		SalesAvgDTO result;
		try {
			String statement = namespace + ".getSalesAvg";
			map.put("guCode", guCode);
			map.put("mainCode", mainCode);
			result = sqlSession.selectOne(statement, map);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	/* 시군구별 개폐업률 */
	public NewAndFailDTO getNewAndFail(String guCode) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectOne(namespace + ".getNewAndFail", guCode);
		} finally {
			sqlSession.close();
		}
	}
	
	
	/* 시군구별 평균 운영연수 */
	/*
	public List<OperateAvgDTO> getOperateAvg(String guCode) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<OperateAvgDTO> result;
		try {
			String statement = namespace + ".getOperateAvg";
			map.put("guCode", guCode);
			result = sqlSession.selectList(statement, map);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		return result;
	}
	*/
	
	/* 시군구별 인구밀도 */
	public PopulationDTO getPopulation(String guCode) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectOne(namespace + ".getPopulation", guCode);
		} finally {
			sqlSession.close();
		}
	}
}
