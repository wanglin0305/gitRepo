package user.service.impl;

import org.springframework.transaction.annotation.Transactional;

import user.dao.UserDAO;
import user.domain.User;
import user.domain.UserFormBean;
import user.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {
	private UserDAO userDao;

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean ajaxValidateLoginName(String name) {
		
		return userDao.ajaxValidateLoginName(name);
	}

	@Override
	public boolean ajaxValidateEmail(String email) {
		
		return userDao.ajaxValidateEmail(email);
	}

	@Override
	public int regist(UserFormBean userBean) {
		User user = new User();
		user.setUserName(userBean.getLoginname());
		user.setPassword(userBean.getLoginpass());
		user.setEmail(userBean.getEmail());
		return userDao.add(user);
	}


	@Override
	public User login(UserFormBean userBean) {
		
		return userDao.findByName(userBean.getLoginname(),userBean.getLoginpass());
	}

	@Override
	public boolean validatePassword(UserFormBean userBean, int id) {
	
		return userDao.findById(userBean.getLoginpass(),id);
	}

	@Override
	public User updatePassword(UserFormBean userBean, int id) {
		return userDao.updatePassword(userBean,id);
	}

	

	
}
