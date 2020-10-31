package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import mybatis.AbstractMybatis;
import store.StoreDTO;

@Service
public class StoreDAO extends AbstractMybatis {
	String namespace = "Store";
	
	public List<StoreDTO> allStoreList(Map<String, Object> lanlng) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".allStoreList", lanlng);
		} finally {
			sqlSession.close();
		}
	}
	
	public List<StoreDTO> storeList(String dongCode) {
		Map<String, Object> dong = new HashMap<String, Object>();
		dong.put("dongCode", dongCode);
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			return sqlSession.selectList(namespace + ".storeList", dong);
		} finally {
			sqlSession.close();
		}
	}
}