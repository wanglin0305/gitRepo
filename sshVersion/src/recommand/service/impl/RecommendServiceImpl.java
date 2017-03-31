package recommand.service.impl;

import org.springframework.transaction.annotation.Transactional;

import recommand.dao.RecommendDao;
import recommand.domain.UserInfoBean;
import recommand.service.RecommendService;
import result.domain.Result;
import sun.print.resources.serviceui;
import user.domain.User;
@Transactional
public class RecommendServiceImpl implements RecommendService {
	private RecommendDao recommendDao;

	public void setRecommendDao(RecommendDao recommendDao) {
		this.recommendDao = recommendDao;
	}

	@Override
	public boolean validateUserInfo(User user) {
		if(user.getAge()==null || user.getLactation() == null || user.getPregnant() == null || user.getSex() ==null
				|| user.getWeight() == null || user.getWork() == null){
			return false;
		}
		return true;
	}

//	根据某个公式，计算此人一天需要的卡路里，蛋白质、碳水化合物、脂肪等含量
	@Override
	public Result caculateResult(User user) {
		
		return null;
	}

	@Override
	public User updateUserInfo(UserInfoBean userInfoBean, User user) {
		user.setAge(Integer.parseInt(userInfoBean.getAge()));
		user.setWeight(Float.parseFloat(userInfoBean.getWeight()));
		if(userInfoBean.getSex().equals("male")){
			user.setSex(1);
		}else{
			user.setSex(0);
		}
		user.setWork(Integer.parseInt(userInfoBean.getWork()));
		user.setPregnant(Boolean.parseBoolean(userInfoBean.getPregnant()));
		user.setLactation(Boolean.parseBoolean(userInfoBean.getLactation()));
		recommendDao.updateUser(user);
		return user;
	}
	
}
