package community;

import java.util.Date;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import member.AbstractMybatis;

public class CommunityDAO extends AbstractMybatis{
	String namespace="Community";
	HashMap<String, Object> map = new HashMap<String, Object>();

	public int insertCommunity(String userid, String pwd, String name, String email, String birthdate, String gender) {
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


}
