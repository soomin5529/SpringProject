package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import board.BoardLikeDTO;

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

			article.setBoardid(boardid);
			article.setUserid(userid);

			System.out.println("insertBoardLike article??===" + article);

			return sqlSession.insert(statement, article);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}

	}

}
