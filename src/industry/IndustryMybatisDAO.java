package industry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import mybatis.AbstractMybatis;

// 매핑된 xml의 sql을 불러서 쓴다. namespace.id 식으로 쓴다.
public class IndustryMybatisDAO extends AbstractMybatis {
	String namespace = "Industry";

	public List<MainCategory> category_mainList() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".category_mainList");
		} finally {
			sqlSession.close();
		}
	}

	public List<MiddleCategory> category_middleList(String mainCode) {
		Map<String, String> middle = new HashMap<String, String>();
		middle.put("maincategory_code", mainCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".category_middleList", middle);
		} finally {
			sqlSession.close();
		}
	}

	public List<SmallCategory> category_smallList(String middleCode) {
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