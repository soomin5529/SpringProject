package member;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public abstract class AbstractMybatis {
	private static SqlSessionFactory sqlSessionFactory; 

	static {
		setsqlSessionFactory();
	}
	
	private static void setsqlSessionFactory() { //getConnection 역할
		String resource = "mybatis/mybatis-config.xml"; //xml을 불러온다
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	protected SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
