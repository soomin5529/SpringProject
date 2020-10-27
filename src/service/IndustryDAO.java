package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import industry.IndustryDTO;
import industry.MainCategoryDTO;
import industry.MiddleCategoryDTO;
import industry.SmallCategoryDTO;

@Service
public class IndustryDAO extends AbstractMybatis {
	String namespace = "Industry";

	public List<MainCategoryDTO> category_mainList() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".category_mainList");
		} finally {
			sqlSession.close();
		}
	}

	public List<IndustryDTO> category_middleList(String mainCode) {
		Map<String, String> middle = new HashMap<String, String>();
		middle.put("maincategory_code", mainCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".category_middleList", middle);
		} finally {
			sqlSession.close();
		}
	}

	public List<IndustryDTO> category_smallList(String middleCode) {
		Map<String, Object> small = new HashMap<String, Object>();
		small.put("middlecategory_code", middleCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".category_smallList", small);
		} finally {
			sqlSession.close();
		}
	}

}