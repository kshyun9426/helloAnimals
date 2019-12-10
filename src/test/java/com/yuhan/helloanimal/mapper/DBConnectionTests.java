package com.yuhan.helloanimal.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yuhan.helloanimal.config.RootConfig;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class DBConnectionTests {
	
	@Autowired
	private DataSource dataSource;
	
	//테스트 마무리
//	@Test
//	public void testConnection() {
//		try(Connection con = dataSource.getConnection()){
//			assertNotNull(con);
//			log.info(con);
//		}catch(SQLException ex) {
//			fail(ex.getMessage());
//		}
//	}
	
}

























