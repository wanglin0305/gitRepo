package user.dao;

import user.domain.User;
import user.domain.UserFormBean;

public interface UserDAO {

	// �½��û�
	int add(User user);

	// �����û���Ϣ
	void update(User user);

	// ����id��ѯ�û�,����֤������ȷ���
	boolean findById(String password, int id);

	User findByName(String name);

	// �����û����������ѯ�û�
	User findByName(String name, String password);

	// ȷ���û����Ƿ��Ѿ�����
	boolean ajaxValidateLoginName(String name);

	// ȷ�������Ƿ��Ѿ�����
	boolean ajaxValidateEmail(String email);

	User updatePassword(UserFormBean userBean, int id);

}
