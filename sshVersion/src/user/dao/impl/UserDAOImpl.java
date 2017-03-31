package user.dao.impl;

import java.util.List;


import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import user.dao.UserDAO;
import user.domain.User;
import user.domain.UserFormBean;

@Transactional
public class UserDAOImpl implements UserDAO {
	
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public int add(User user) {	
		int id = (int) hibernateTemplate.save(user);
		return id;
	}

	@Override
	public void update(User user) {
		
		

	}
	@Override
	public boolean findById(String password, int id) {
		User user = hibernateTemplate.get(User.class, id);
		return (user.getPassword().equals(password));
	}

	@Override
	public User findByName(String name) {
		return null;
	}

	@Override
	public User findByName(String name, String password) {
		String sql = "from User where userName = ? and password = ?";
		List<User> list = (List<User>) hibernateTemplate.find(sql, name,password);
		if(!list.isEmpty()){
			User user = list.get(0);
			return user;
		}
		return null;
	}
	
	//验证用户名是否注册过，返回值为True表示没有注册过
	@Override
	public boolean ajaxValidateLoginName(String name) {
		String sql = "select count(*) from User where userName = ?";
		long num = (long) hibernateTemplate.find(sql, name).get(0);
	
		return (num==0);
	}
	
	@Override
	public boolean ajaxValidateEmail(String email) {
		String sql = "from User where email = ?";
		List<User> list = (List<User>) hibernateTemplate.find(sql, email);
	
		return list.isEmpty();
	}

	@Override
	public User updatePassword(UserFormBean userBean, int id) {
		User user = hibernateTemplate.get(User.class, id);
		user.setPassword(userBean.getNewpass());
		hibernateTemplate.update(user);
		return user;
	}



}
