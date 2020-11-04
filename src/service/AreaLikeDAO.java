package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import area.AreaLikeDTO;
import board.BoardLikeDTO;
import mybatis.AbstractMybatis;

@Service
public class AreaLikeDAO extends AbstractMybatis {
	String namespace = "AreaLike";

	Map<String, Object> map = new HashMap<String, Object>();

	public int insertAreaLike(String userid, String code) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = 0;
		try {
			AreaLikeDTO article = new AreaLikeDTO();
			String statement = namespace + ".insertAreaLike";

			article.setUserid(userid);
			article.setCode(code);

			System.out.println("insertAreaLike article??===" + article);

			result = sqlSession.insert(statement, article);
			sqlSession.commit();
			System.out.println("result" + result);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	public int deleteAreaLike(String userid, String code) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = 0;
		try {
			map.clear();
			String statement = namespace + ".deleteAreaLike";

			map.put("code", code);
			map.put("userid", userid);

			System.out.println("deleteAreaLike article??===" + map);
			result = sqlSession.delete(statement, map);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		return result;

	}

	public List<Object> selectAreaLike(String userid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<Object> areaLike = null;
		try {
			map.clear();
			String statement = namespace + ".selectAreaLike";

			map.put("userid", userid);

			System.out.println("selectAreaLike article??===" + map);
			areaLike = sqlSession.selectList(statement, map);

		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		System.out.println("areaLike---" + areaLike);
		return areaLike;

	}
	
	public List<String> getUserid(String code) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<String> areaLike = null;
		try {
			map.clear();
			String statement = namespace + ".getUserid";

			map.put("code", code);

			System.out.println("getUserid article??===" + map);
			areaLike = sqlSession.selectList(statement, map);

		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		System.out.println("areaLike-getuserId 값은--" + areaLike);
		return areaLike;

	}

}
