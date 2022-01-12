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
				// sql-map-config.xml�� �о�� �����ͺ��̽��� ������ ���� ��ü�� ����
				sessionFactory = new SqlSessionFactoryBuilder().build(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * ������ ������ �����ͺ��̽� ���Ἴ���� �ٸ� �޼ҵ忡 ����
	 */
	public static SqlSession getSqlSessionInstance() {

		return sessionFactory.openSession();

	}

}
