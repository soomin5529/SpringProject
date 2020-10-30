package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import area.SidoDTO;
import area.SigunguDTO;
import mybatis.AbstractMybatis;
import board.BoardDTO;
import comment.CommentDTO;

@Service
public class CommentDAO extends AbstractMybatis {
	String namespace = "Comment";

	Map<String, Object> map = new HashMap<String, Object>();
    //댓글 개수 ${cnt} count
	
	public int getCommentCount(int boardid) throws Exception {
       
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			map.put("boardid", boardid);
			return sqlSession.selectOne(namespace + ".getCommentCount", map);
		} finally {
			sqlSession.close();
		}
	}
	
	// 댓글 list 받기
	public List<CommentDTO> getComments(int boardid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		map.clear();
		map.put("boardid", boardid);
		try {
			return sqlSession.selectList(namespace + ".getComments", map);
		} finally {
			sqlSession.close();
		}
	}
	// insert
	public int insertComment(CommentDTO article) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		    String regDate = sdf.format(today);
			map.clear();
			int number = sqlSession.selectOne(namespace + ".insertComment_new");
			if (number != 0)
				number = number + 1;
			else
				number = 1;
			String statement = namespace + ".insertComment";
			/*
			 * map.put("commentid", number); map.put("userid", article.getUserid());
			 * map.put("boardid", article.getBoardid()); map.put("content",
			 * article.getContent());
			 */
			
			article.setCommentid(number);
			article.setRegDate(regDate);
			
			//article.setName(name);

			System.out.println("article은??" + article);

			return sqlSession.insert(statement, article);
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}

	}
	//삭제
	public int deleteComment(String userid, String commentid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		map.clear();
		int x = -1;
		try {
			if (userid != null) {
				map.put("userid", userid);
				map.put("commentid", commentid);
				x = sqlSession.delete(namespace + ".deleteComment", map);
			}
		} finally {
			sqlSession.commit();
			sqlSession.close();
		}
		return x;
	}
	
	
	
	
}