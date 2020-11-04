package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import board.BoardDTO;
import mybatis.AbstractMybatis;

@Service
public class BoardDAO extends AbstractMybatis {
	String namespace = "Board";

	Map<String, Object> map = new HashMap<String, Object>();

	// 동마다 게시물 수 count
	public int getBoardCount(String dong_code) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			map.put("dong_code", dong_code);
			System.out.println(map);
			return sqlSession.selectOne(namespace + ".getBoardCount", map);
		} finally {
			sqlSession.close();
		}
	}

	public List<BoardDTO> getArticles(String dong_code) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		map.clear();
		map.put("dong_code", dong_code);
		try {
			return sqlSession.selectList(namespace + ".getArticles", map);
		} finally {
			sqlSession.close();
		}
	}

	public int insertArticle(BoardDTO article) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			map.clear();
			int number = sqlSession.selectOne(namespace + ".insertArticle_new");
			if (number != 0)
				number = number + 1;
			else
				number = 1;
			String statement = namespace + ".insertArticle";
			
			map.put("userid", article.getUserid());
			map.put("boardid", number);
			map.put("dong_code", article.getDong_code());
			map.put("writer", article.getWriter());
			map.put("regDate", article.getRegDate());
			map.put("content", article.getContent());
			map.put("filename", article.getFilename());

			System.out.println(map);

			article.setBoardid(number);

			System.out.println("article은??" + article);

			return sqlSession.insert(statement, article);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}

	}

	public int deleteArticle(String userid, String dong_code, String boardid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		map.clear();
		int x = -1;
		try {
			if (userid != null) {
				map.put("userid", userid);
				map.put("dong_code", dong_code);
				map.put("boardid", boardid);
				x = sqlSession.delete(namespace + ".delete", map);
			}
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return x;
	}

}
