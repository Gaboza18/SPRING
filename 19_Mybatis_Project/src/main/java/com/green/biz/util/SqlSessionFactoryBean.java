package com.green.biz.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {
	
	private static SqlSessionFactory sessionFactory = null;

	static {
		if (sessionFactory == null) {
			try {
				Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
				// sql-map-config.xml을 읽어와 데이터베이스를 연결한 세션 객체를 생성
				sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 위에서 생성한 데이터베이스 연결세션을 다른 메소드에 제공
	 */
	public static SqlSession getSqlSessionInstance() {

		return sessionFactory.openSession();

	}

}
