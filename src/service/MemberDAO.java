package service;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import member.MemberDTO;


@Service
public class MemberDAO extends AbstractMybatis {
	String namespace = "Member";
	HashMap<String, Object> map = new HashMap<String, Object>();
//회원가입
	public int insertmember(String userid, String pwd, String name, String email, String birthdate, String gender) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = 0;
		try {
			String statement = namespace + ".insertmember";
			map.put("userid", userid);
			map.put("pwd", pwd);
			map.put("name", name);
			map.put("email", email);
			map.put("birthdate", birthdate.replace("-", ""));
			map.put("gender", gender);
			map.put("regdate", new Date());
			map.put("author", 1);
			System.out.println(map);
			result = sqlSession.insert(statement, map);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
		return result;
	}
//로그인
	public boolean loginMember(String userid, String pwd) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String x = "";
		boolean result = false;
		try {
			map.clear();
			map.put("userid", userid);
			map.put("pwd", pwd);
			String statement = namespace + ".loginMember";
			x = sqlSession.selectOne(statement, map);
			if (x != null) {
				result = true;
			}
		} finally {
			sqlSession.close();
		}
		return result;
	}

	public boolean checkId(String userid) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		boolean result = false;
		String checkId = "";
		try {
			String statement = namespace + ".checkId";
			checkId = sqlSession.selectOne(statement, userid);
			if (checkId != null) {
				result = true;
			}
		} finally {
			sqlSession.close();
		}
		return result;
	}
//로그아웃
	public boolean logout(HttpSession session) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		boolean result = false;
		session.invalidate();
		return result;
	}
	


}