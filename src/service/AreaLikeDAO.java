package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import area.AreaLikeDTO;
import mybatis.AbstractMybatis;

@Service
public class AreaLikeDAO extends AbstractMybatis {
	String namespace = "AreaLike";

	Map<String, Object> map = new HashMap<String, Object>();

	public int insertAreaLike(String userid, String code) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = 0;
		try {
			// AreaLikeDTO article = new AreaLikeDTO();
			String statement = namespace + ".insertAreaLike";
			map.clear();
			map.put("userid", userid);
			map.put("code", code);

			System.out.println("insertAreaLike article??===" + map);

			result = sqlSession.insert(statement, map);
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
			String statement = namespace + ".deleteAreaLike";
			map.clear();
			map.put("userid", userid);
			map.put("code", code);

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
			sqlSession.close();
		}
		System.out.println("areaLike---" + areaLike);
		return areaLike;
	}

	public boolean checkAreaLike(String userid, String code) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		AreaLikeDTO areaLike = null;
		boolean result = false;
		try {
			map.clear();
			map.put("userid", userid);
			map.put("code", code);
			String statement = namespace + ".checkAreaLike";
			areaLike = sqlSession.selectOne(statement, map);
			System.out.println("areaLike---" + areaLike);
			if (areaLike != null) {
				result = true;
			}
		} finally {
			sqlSession.close();
		}
		return result;
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
