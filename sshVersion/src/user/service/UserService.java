package user.service;

import user.domain.User;
import user.domain.UserFormBean;

public interface UserService {
	
	boolean ajaxValidateLoginName(String name);
	
	boolean ajaxValidateEmail(String email);

	int regist(UserFormBean userBean);
	
	User login(UserFormBean userBean);

	boolean validatePassword(UserFormBean userBean,int id);

	User updatePassword(UserFormBean userBean, int id);
}
