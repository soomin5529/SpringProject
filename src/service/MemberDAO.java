package service;

import java.util.Date;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

@Service
public class MemberDAO extends AbstractMybatis {
	String namespace = "Member";
	HashMap<String, Object> map = new HashMap<String, Object>();

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

}
/*
 * public boolean updateMember(MemberDTO bean) { Connection conn = null;
 * PreparedStatement pstmt = null; boolean flag = false;
 * 
 * try { conn = DriverManager.getConnection(JDBC_URL, USER, PASS); String
 * strQuery =
 * "update Member set pwd=?, name=? , gender=? , birthday=?, email=? , zipcode=? , address=? , hobby=? , job=? where id=?"
 * ; pstmt = conn.prepareStatement(strQuery);
 * 
 * pstmt.setString(1, bean.getPwd()); pstmt.setString(2, bean.getName());
 * pstmt.setString(3, bean.getGender()); pstmt.setString(4, bean.getBirthday());
 * pstmt.setString(5, bean.getEmail()); pstmt.setString(6, bean.getZipcode());
 * pstmt.setString(7, bean.getAddress());
 * 
 * String hobby[] = bean.getHobby();
 * 
 * char hb[] = {'0','0','0','0','0'}; String lists[] =
 * {"�뜝�룞�삕�뜝�떢�냲�삕","�뜝�룞�삕�뜝�룞�삕","�뜝�룞�삕�뜝�룞�삕", "�뜝�룞�삕�솕","�뜝�룜�룞"};
 * //list[] �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �솗�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 1�뜝�룞�삕
 * �뜝�룞�삕�뜝�룞�삕
 * 
 * for(int i=0;i<hobby.length;i++) { System.out.println(hobby[i]); for (int j =
 * 0; j < lists.length; j++) { if(hobby[i].equals(lists[j])) hb[j] = '1'; } }
 * 
 * pstmt.setString(8, bean.getHob()); pstmt.setString(9, bean.getJob());
 * pstmt.setString(10, bean.getId()); if (pstmt.executeUpdate() == 1) flag =
 * true;
 * 
 * } catch (Exception e) { e.printStackTrace(); } finally { Util.close(conn,
 * pstmt); } return flag; }
 * 
 * public MemberDTO getMember(String id) { Connection conn = null;
 * PreparedStatement pstmt = null; ResultSet rs = null; String sql = null;
 * MemberDTO bean = null; try { conn = DriverManager.getConnection(JDBC_URL,
 * USER, PASS); sql = "select*from tblMember where id=?"; pstmt =
 * conn.prepareStatement(sql); pstmt.setString(1, id); rs =
 * pstmt.executeQuery(); if (rs.next()) { bean = new MemberDTO();
 * bean.setId(rs.getString("id")); bean.setPwd(rs.getString("pwd"));
 * bean.setName(rs.getString("name")); bean.setGender(rs.getString("gender"));
 * bean.setBirthday(rs.getString("birthday"));
 * bean.setEmail(rs.getString("email"));
 * bean.setZipcode(rs.getString("zipcode"));
 * bean.setAddress(rs.getString("address")); bean.setHob(rs.getString("hobby"));
 * bean.setJob(rs.getString("job")); } } catch (Exception ex) {
 * System.out.println("Exception" + ex); } finally { if (rs != null) ; try {
 * rs.close(); } catch (Exception e) { e.printStackTrace(); } finally {
 * Util.close(conn, pstmt); } } return bean; }
 * 
 * public List<MemberDTO> getMemberList() { Connection conn = null;
 * PreparedStatement pstmt = null; ResultSet rs = null; String sql = null;
 * List<MemberDTO> li = new ArrayList(); try { conn =
 * DriverManager.getConnection(JDBC_URL, USER, PASS); sql =
 * "select*from tblMember"; pstmt = conn.prepareStatement(sql);
 * 
 * rs = pstmt.executeQuery(); while (rs.next()) { MemberDTO bean = new
 * MemberDTO(); bean.setId(rs.getString(1)); bean.setName(rs.getString(3));
 * bean.setGender(rs.getString(4)); bean.setBirthday(rs.getString(5));
 * bean.setEmail(rs.getString(6)); bean.setZipcode(rs.getString(7));
 * bean.setAddress(rs.getString(8)); bean.setHob(rs.getString(9));
 * bean.setJob(rs.getString(10));
 * 
 * li.add(bean); }
 * 
 * } catch (Exception ex) { System.out.println("Exception" + ex); } finally { if
 * (rs != null) ; try { rs.close(); } catch (Exception e) { e.printStackTrace();
 * } finally { Util.close(conn, pstmt, rs); } } return li; }
 * 
 * public boolean deleteMember(String id, String pwd) { boolean result = false;
 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
 * String sql = null; String dbpwd = "";
 * 
 * try { conn = DriverManager.getConnection(JDBC_URL, USER, PASS); sql =
 * "select pwd from tblMember where id=? "; pstmt = conn.prepareStatement(sql);
 * pstmt.setString(1, id); rs = pstmt.executeQuery(); if (rs.next()) { dbpwd =
 * rs.getString("pwd"); if (dbpwd.equals(pwd)) { String sql1 =
 * "delete from tblMember where id=?"; pstmt = conn.prepareStatement(sql1);
 * pstmt.setString(1, id); pstmt.executeUpdate(); result = true; } } } catch
 * (Exception e) { e.printStackTrace(); } finally { Util.close(conn, pstmt, rs);
 * } return result; }
 */

//}
