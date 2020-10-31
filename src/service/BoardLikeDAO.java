package service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import board.BoardLikeDTO;
import mybatis.AbstractMybatis;

@Service
public class BoardLikeDAO extends AbstractMybatis {
	String namespace = "Like";

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
			map.clear();
			String statement = namespace + ".insertBoardLike";
			BoardLikeDTO article = new BoardLikeDTO();

			map.put("userid", userid);
			map.put("boardid", boardid);
			System.out.println("insertBoardLike article??===" + map);

			return sqlSession.insert(statement, map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}

	}
	
	public int deleteBoardLike(int boardid, String userid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			map.clear();
			String statement = namespace + ".deleteBoardLike";

			map.put("boardid", boardid);
			map.put("userid", userid);

			System.out.println("deleteBoardLike article??===" + map);
			return sqlSession.delete(statement, map);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}

	}

}
