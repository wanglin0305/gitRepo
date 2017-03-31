package user.dao;

import user.domain.User;
import user.domain.UserFormBean;

public interface UserDAO {

	// 新建用户
	int add(User user);

	// 更新用户信息
	void update(User user);

	// 根据id查询用户,并验证密码正确与否
	boolean findById(String password, int id);

	User findByName(String name);

	// 根据用户名和密码查询用户
	User findByName(String name, String password);

	// 确认用户名是否已经存在
	boolean ajaxValidateLoginName(String name);

	// 确认邮箱是否已经存在
	boolean ajaxValidateEmail(String email);

	User updatePassword(UserFormBean userBean, int id);

}
