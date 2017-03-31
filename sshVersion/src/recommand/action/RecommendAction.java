package recommand.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.swing.internal.plaf.metal.resources.metal_es;

import recommand.domain.UserInfoBean;
import recommand.service.RecommendService;
import result.domain.Result;
import user.domain.User;

public class RecommendAction extends ActionSupport implements ModelDriven<UserInfoBean>{

	private UserInfoBean userInfoBean = new UserInfoBean();
	public UserInfoBean getModel() {
		return userInfoBean;
	}
	private RecommendService recommendService;
	public void setRecommendService(RecommendService recommendService) {
		this.recommendService = recommendService;
	}
	
//	验证用户信息是否全面
	public String validateInfo(){
//		1、调用service方法，判断用户信息是否填全
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		boolean b = recommendService.validateUserInfo(user);
//		2、若填全，调用service的计算result方法，并将结果存到request中，进入result页面
		if(b){
			Result result = recommendService.caculateResult(user);
			ServletActionContext.getRequest().setAttribute("result", result);
			return "result";
		}else {
//		3、若未填全，保存错误信息，进入用户信息填写页面
			ServletActionContext.getRequest().setAttribute("msg", "您的信息填写不全，请先填写详细信息！");
			return "userInfo";
		}
		}
	public String userInfo(){
//		1、验证表单数据，将错误信息放在map里面
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		Map<String, String > errorMap = validateUserInfoBean(userInfoBean,session);
//		2、若表单验证不通过，将错误信息存到request域中，转发到信息填写页面
		if(errorMap.size()>0){
			ServletActionContext.getRequest().setAttribute("errors", errorMap);
			return "userInfo";
		}
//		3、表单验证通过，调用service方法完成用户信息更新，并将新的用户对象存入session中
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		User newUser = recommendService.updateUserInfo(userInfoBean,user);
		ServletActionContext.getRequest().getSession().setAttribute("user", newUser);

//		4、调用sevice的result方法，计算result结果，将result对象存入request域中，转发到result页面
		Result result = recommendService.caculateResult(newUser);
		ServletActionContext.getRequest().setAttribute("result", result);
		return "result";
}

	private Map<String, String> validateUserInfoBean(UserInfoBean userInfoBean, HttpSession session) {
		
		Map<String, String> errors = new HashMap<>();
		
		String age= userInfoBean.getAge();
		if(age ==null || age.trim().isEmpty()){
			errors.put("age", "年龄不能为空！");
		}else{
			int ageNum = Integer.parseInt(age);
			if(ageNum >110 || ageNum < 0){
				errors.put("age"," 年龄必须在0~110之间！");
			}
		}
		String weight = userInfoBean.getWeight();
		if(weight ==null || weight.trim().isEmpty()){
			errors.put("weight", "体重不能为空！");
		}else{
			int weightNum = Integer.parseInt(weight);
			if(weightNum < 0){
				errors.put("weight"," 体重不能为负数！");
			}
		}
		
		String sex = userInfoBean.getSex();
		if(sex ==null || sex.trim().isEmpty()){
			errors.put("sex", "请选择性别！");
		}
		
		String work = userInfoBean.getWork();
		if(work ==null || work.trim().isEmpty()){
			errors.put("work", "请选择工作性质！");
		}
		
		String pregnant = userInfoBean.getPregnant();
		if(pregnant ==null || pregnant.trim().isEmpty()){
			errors.put("pregnant", "请选择是否怀孕！");
		}else if (sex.equals("male") && pregnant.equals("1")) {
			errors.put("pregnant", "请重新选择性别或者是否怀孕！");
		}
		
		String lactation = userInfoBean.getLactation();
		if(lactation ==null || lactation.trim().isEmpty()){
			errors.put("lactation", "请选择是否处于哺乳期！");
		}else if (sex.equals("male")&& lactation.equals("1")) {
			errors.put("lactation", "请重新选择性别或是否处于哺乳期！");
		}

		String verifyCode = userInfoBean.getVerifycode();
		String rverifyCode = (String) session.getAttribute("verifyCode");
		if(verifyCode ==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空！");
		}else if(!verifyCode.equalsIgnoreCase(rverifyCode)){
			errors.put("verifyCode", "验证码不正确！");
		}
		
		return errors;
	}
	


	

}
