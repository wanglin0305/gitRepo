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
	
//	��֤�û���Ϣ�Ƿ�ȫ��
	public String validateInfo(){
//		1������service�������ж��û���Ϣ�Ƿ���ȫ
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		boolean b = recommendService.validateUserInfo(user);
//		2������ȫ������service�ļ���result��������������浽request�У�����resultҳ��
		if(b){
			Result result = recommendService.caculateResult(user);
			ServletActionContext.getRequest().setAttribute("result", result);
			return "result";
		}else {
//		3����δ��ȫ�����������Ϣ�������û���Ϣ��дҳ��
			ServletActionContext.getRequest().setAttribute("msg", "������Ϣ��д��ȫ��������д��ϸ��Ϣ��");
			return "userInfo";
		}
		}
	public String userInfo(){
//		1����֤�����ݣ���������Ϣ����map����
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		Map<String, String > errorMap = validateUserInfoBean(userInfoBean,session);
//		2��������֤��ͨ������������Ϣ�浽request���У�ת������Ϣ��дҳ��
		if(errorMap.size()>0){
			ServletActionContext.getRequest().setAttribute("errors", errorMap);
			return "userInfo";
		}
//		3������֤ͨ��������service��������û���Ϣ���£������µ��û��������session��
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		User newUser = recommendService.updateUserInfo(userInfoBean,user);
		ServletActionContext.getRequest().getSession().setAttribute("user", newUser);

//		4������sevice��result����������result�������result�������request���У�ת����resultҳ��
		Result result = recommendService.caculateResult(newUser);
		ServletActionContext.getRequest().setAttribute("result", result);
		return "result";
}

	private Map<String, String> validateUserInfoBean(UserInfoBean userInfoBean, HttpSession session) {
		
		Map<String, String> errors = new HashMap<>();
		
		String age= userInfoBean.getAge();
		if(age ==null || age.trim().isEmpty()){
			errors.put("age", "���䲻��Ϊ�գ�");
		}else{
			int ageNum = Integer.parseInt(age);
			if(ageNum >110 || ageNum < 0){
				errors.put("age"," ���������0~110֮�䣡");
			}
		}
		String weight = userInfoBean.getWeight();
		if(weight ==null || weight.trim().isEmpty()){
			errors.put("weight", "���ز���Ϊ�գ�");
		}else{
			int weightNum = Integer.parseInt(weight);
			if(weightNum < 0){
				errors.put("weight"," ���ز���Ϊ������");
			}
		}
		
		String sex = userInfoBean.getSex();
		if(sex ==null || sex.trim().isEmpty()){
			errors.put("sex", "��ѡ���Ա�");
		}
		
		String work = userInfoBean.getWork();
		if(work ==null || work.trim().isEmpty()){
			errors.put("work", "��ѡ�������ʣ�");
		}
		
		String pregnant = userInfoBean.getPregnant();
		if(pregnant ==null || pregnant.trim().isEmpty()){
			errors.put("pregnant", "��ѡ���Ƿ��У�");
		}else if (sex.equals("male") && pregnant.equals("1")) {
			errors.put("pregnant", "������ѡ���Ա�����Ƿ��У�");
		}
		
		String lactation = userInfoBean.getLactation();
		if(lactation ==null || lactation.trim().isEmpty()){
			errors.put("lactation", "��ѡ���Ƿ��ڲ����ڣ�");
		}else if (sex.equals("male")&& lactation.equals("1")) {
			errors.put("lactation", "������ѡ���Ա���Ƿ��ڲ����ڣ�");
		}

		String verifyCode = userInfoBean.getVerifycode();
		String rverifyCode = (String) session.getAttribute("verifyCode");
		if(verifyCode ==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "��֤�벻��Ϊ�գ�");
		}else if(!verifyCode.equalsIgnoreCase(rverifyCode)){
			errors.put("verifyCode", "��֤�벻��ȷ��");
		}
		
		return errors;
	}
	


	

}
