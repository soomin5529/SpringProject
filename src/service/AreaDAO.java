package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import area.AreaDTO;
import area.DongDTO;
import area.SidoDTO;
import area.SigunguDTO;
import mybatis.AbstractMybatis;

@Service
public class AreaDAO extends AbstractMybatis {
	String namespace = "Area";

	// 시도 출력-시도가 18개로 되어있으므로, List로 출력한다.
	public List<SidoDTO> sidoList() {
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

	public List<SigunguDTO> sigungu(String sigunguCode) {
		Map<String, Object> sigungu_code = new HashMap<String, Object>();
		sigungu_code.put("sigungu_code", sigunguCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".sigungu", sigungu_code);
		} finally {
			sqlSession.close();
		}

	}

	public List<DongDTO> dong(String dongCode) {
		Map<String, Object> dong_code = new HashMap<String, Object>();
		dong_code.put("dong_code", dongCode);
		System.out.println("동코드가 머냐" + dongCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".dong", dong_code);
		} finally {
			sqlSession.close();
		}

	}

}