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

	// board 글 등록 시 boardid, regdate insert 및 update
	public int insertNotice(String userid, String dongcode, int boardid, String regdate) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = 0;
		try {
			AreaNoticeDTO article = new AreaNoticeDTO();
			String statement = namespace + ".insertNotice";

			article.setUserid(userid);
			article.setDongCode(dongcode);
			article.setBoardid(boardid);
			article.setBoard_regdate(regdate);

			System.out.println("insertNotice DB?===" + article);

			result = sqlSession.insert(statement, article);
			sqlSession.commit();
			System.out.println("게시물 등록시 boardid,regdate, dongcode insert  result" + result);
		} finally {
			sqlSession.close();
		}
		return result;
	}

	// area_notice form
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

	public int updateReaded(int boardid) throws Exception {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = 0;
		try {
			AreaNoticeDTO article = new AreaNoticeDTO();
			System.out.println(boardid);
			String statement = namespace + ".updateReaded";
			article.setBoardid(boardid);
			System.out.println("updateReaded DB?===" + article);

			result = sqlSession.update(statement, article);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		return result;
	}

}
