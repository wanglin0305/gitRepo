package test;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import user.dao.UserDAO;
import user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:bean1.xml")
public class TestUserDAO extends AbstractJUnit4SpringContextTests{
	
	@Resource
	UserDAO userDao;
	
	@Test
	public void testAdd(){
		User user = new User();
		user.setUserName("abbb");
		System.out.println(userDao.add(user));
	}
	
	@Test
	public void testAjaxValidateLoginName(){
		System.out.println(userDao.ajaxValidateLoginName("ÕÅÈý·á"));
	}
	
	@Test
	public void testAjaxValidateEmail(){
		System.out.println(userDao.ajaxValidateEmail("111@123.com"));
	}
}
