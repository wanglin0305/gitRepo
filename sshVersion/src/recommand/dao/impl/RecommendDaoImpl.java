package recommand.dao.impl;

import org.springframework.orm.hibernate5.HibernateTemplate;

import recommand.dao.RecommendDao;
import user.domain.User;

public class RecommendDaoImpl implements RecommendDao {
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public void updateUser(User user) {
		hibernateTemplate.saveOrUpdate(user);		
	}
	

}
