package com.keduit.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)//JUnit4클래스를 통해서 test를 할거야
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")//환경을 갖고 있음
@Log4j//log를 통해 출력해서 볼 수 있다

public class DataSourceTests {
	
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	@Setter(onMethod_ = @Autowired)
	private SqlSessionFactory sqlSessionFactory;
	
	
	@Test
	public void testConnection() {
		try(Connection con = dataSource.getConnection()) {
			log.info(con);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testMyBatis() {
		try(SqlSession session = sqlSessionFactory.openSession();
				Connection con = session.getConnection()){
			log.info(session);
			log.info(con);
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
}
