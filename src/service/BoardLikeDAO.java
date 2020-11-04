package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import board.BoardLikeDTO;
import mybatis.AbstractMybatis;

@Service
public class BoardLikeDAO extends AbstractMybatis {
	String namespace = "BoardLike";

	Map<String, Object> map = new HashMap<String, Object>();

	public int getBoardLikeCount(int boardid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			map.put("boardid", boardid);
			return sqlSession.selectOne(namespace + ".getBoardLikeCount", map);
		} finally {
			sqlSession.close();
		}
	}

	public int insertBoardLike(int boardid, String userid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".insertBoardLike";
			map.clear();
			map.put("userid", userid);
			map.put("boardid", boardid);
			return sqlSession.insert(statement, map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}

	public int deleteBoardLike(int boardid, String userid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".deleteBoardLike";
			map.clear();
			map.put("boardid", boardid);
			map.put("userid", userid);
			return sqlSession.delete(statement, map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
	}

	public List<BoardLikeDTO> checkBoardLike(String userid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			String statement = namespace + ".checkBoardLike";
			map.clear();
			map.put("userid", userid);
			return sqlSession.selectList(statement, map);
		} finally {
			sqlSession.close();
		}
	}

}
