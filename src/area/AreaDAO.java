package area;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import area.Sido;
import area.Sigungu;
import mybatis.AbstractMybatis;

// 매핑된 xml의 sql을 불러서 쓴다. namespace.id 식으로 쓴다.
public class AreaDAO extends AbstractMybatis {
	String namespace = "Area";
	
	// 시도 출력-시도가 18개로 되어있으므로, List로 출력한다.
	public List<Sido> sidoList() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".sidoList");
		} finally {
			sqlSession.close();
		}
	}

	public List<Sigungu> sigunguList(String sidoCode) {
		Map<String, Object> sigungu = new HashMap<String, Object>();
		sigungu.put("sido_code", sidoCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".sigunguList", sigungu);
		} finally {
			sqlSession.close();
		}
	}

	public List<Dong> dongList(String sigunguCode) {
		Map<String, Object> dong = new HashMap<String, Object>();
		dong.put("sigungu_code", sigunguCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".dongList", dong);
		} finally {
			sqlSession.close();
		}
	}

}