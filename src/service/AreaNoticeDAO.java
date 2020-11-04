package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import area.AreaLikeDTO;
import area.AreaNoticeDTO;
import mybatis.AbstractMybatis;

@Service
public class AreaNoticeDAO extends AbstractMybatis {
	String namespace = "AreaNotice";

	@Autowired
	AreaLikeDAO arealikeDB;
	@Autowired
	BoardDAO boardDB;

	Map<String, Object> map = new HashMap<String, Object>();

	// arealikeDB 에 있는 user, code insert
	public int insertAreaLike(String userid, String dongcode) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = 0;
		try {
			AreaNoticeDTO article = new AreaNoticeDTO();
			String statement = namespace + ".insertAreaLike";

			article.setUserid(userid);
			article.setdongCode(dongcode);

			System.out.println("insertAreaLike DB?===" + article);

			result = sqlSession.insert(statement, article);
			sqlSession.commit();
			System.out.println("관심지역 유저, 동코드 result" + result);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	// board 글 등록 시 boardid, regdate insert 및 update
	public int insertBoard(String dongcode, int boardid, String regdate) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = 0;
		try {
			AreaNoticeDTO article = new AreaNoticeDTO();
			String statement = namespace + ".insertBoard";

			article.setdongCode(dongcode);
			article.setBoardid(boardid);
			article.setBoard_regdate(regdate);

			System.out.println("insertBoard DB?===" + article);

			result = sqlSession.update(statement, article);
			sqlSession.commit();
			System.out.println("게시물 등록시 boardid,regdate  result" + result);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	// area_notce form
	public List<AreaNoticeDTO> selectNotice(String userid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<AreaNoticeDTO> noticeList = null;
		try {
			map.clear();
			String statement = namespace + ".selectNotice";

			map.put("userid", userid);

			System.out.println("area_notice form DB?===" + map);

			noticeList = sqlSession.selectList(statement, map);
			sqlSession.commit();
			System.out.println("select * from area_notice ====" + noticeList);
		} finally {
			sqlSession.close();
		}
		return noticeList;
	}

}
