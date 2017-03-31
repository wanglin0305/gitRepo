package recommand.service;

import recommand.domain.UserInfoBean;
import result.domain.Result;
import user.domain.User;

public interface RecommendService {

	boolean validateUserInfo(User user);

	Result caculateResult(User user);

	User updateUserInfo(UserInfoBean userInfoBean, User user);

}
