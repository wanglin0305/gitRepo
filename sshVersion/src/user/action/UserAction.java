package user.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.Else;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.org.apache.bcel.internal.generic.NEW;

import user.domain.User;
import user.domain.UserFormBean;
import user.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<UserFormBean> {
	
//	userģ��ı����ݷ�װbean
	private UserFormBean userBean = new UserFormBean();
	public UserFormBean getModel() {
		return userBean;
	}

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * У��ע�����Ƿ��Ѿ�ע���
	 * 
	 * @return ��ת��Ҳ���ض���ֻ����response��д��һ������ֵ������jquery��ajax���̼������ݡ�
	 */
	public String ajaxValidateLoginName() {
		String name = ServletActionContext.getRequest().getParameter("loginname");
		boolean b = userService.ajaxValidateLoginName(name);
		try {
			ServletActionContext.getResponse().getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * У��email�Ƿ��Ѿ�ע���
	 * 
	 * @return
	 */
	public String ajaxValidateEmail() {
		String email = ServletActionContext.getRequest().getParameter("email");
		boolean b = userService.ajaxValidateEmail(email);
		try {
			ServletActionContext.getResponse().getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * У����֤���Ƿ���ȷ
	 * ������֤��ͼƬ��ʱ�򣬽���ȷ����֤��ŵ�session��
	 * @return
	 */
	public String ajaxValidateVerifyCode() {
		String verifyCode = (String) ServletActionContext.getRequest().getSession().getAttribute("verifyCode");

		String inputCode = ServletActionContext.getRequest().getParameter("verifyCode");

		boolean b = verifyCode.equalsIgnoreCase(inputCode);
		try {
			ServletActionContext.getResponse().getWriter().print(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// �û�ע���ύ����Ĵ�����
	public String regist() {
//		1��У������ݣ����ʧ�ܣ����������Ϣ�����ص�regist.jspҳ��
		HttpSession session = ServletActionContext.getRequest().getSession();
		Map<String, String > errorMap = validateRegist(userBean,session);
		if(errorMap.size()>0){
			ServletActionContext.getRequest().setAttribute("errors", errorMap);
			return "registFail";
		}
		
		
//		2������service��regist�������ע��
		int id = userService.regist(userBean);

//		3������ɹ���Ϣ��ת����ĳ����ʾҳ��
		ServletActionContext.getRequest().setAttribute("code", "success");
		ServletActionContext.getRequest().setAttribute("msg", "ע��ɹ���3�����ת����¼ҳ�棡");
		ServletActionContext.getResponse().setHeader("refresh","3;url=/sshVersion/jsp/user/login.jsp");
		return "registSuccess";
	}
	
	public String login(){
//		1����װ�����ݣ��Զ���װ��userBean	
//		2��У�������
		HttpSession session = ServletActionContext.getRequest().getSession();
		Map<String, String> errorMap = validateLogin(userBean, session);
		if(errorMap.size()>0){
			ServletActionContext.getRequest().setAttribute("errors", errorMap);
			return "loginFail";
		}
//		3������service�ķ���������user����
		User user = userService.login(userBean);
//		4����user���ڣ�
//		1��������ǰ�û����浽session��
//		2�������û������浽cookie�У��´ε�½ʱ��ʾ�û���
//		3�����ض���index.jsp
		if(user != null){
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			String username= user.getUserName();
			try {
				username =URLEncoder.encode(username, "utf-8");
			} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
			}
			Cookie cookie = new Cookie("loginname", username);
			cookie.setMaxAge(60*60*24*7);
			ServletActionContext.getResponse().addCookie(cookie);
			return "loginSuccess";
		}
//		5����user�����ڣ���¼ʧ��
//		1�������������Ϣ
//		2�����������Ϣ
//		3��������login.jsp
		ServletActionContext.getRequest().setAttribute("msg", "�û��������벻��ȷ��");
		ServletActionContext.getRequest().setAttribute("userBean", userBean);
		return "loginFail";
	}
	
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
	
	public String updatePassword(){
		
//		1����װ����userBean,loginpass�������룬newpass�������룬reloginpass���µ�ȷ������
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			ServletActionContext.getRequest().setAttribute("msg", "����û�е�¼��");
			return "loginFail";
		}
		int id = user.getUserId();
		System.out.println(id);
		System.out.println(userBean.getLoginpass());
//		2��У�������
		HttpSession session = ServletActionContext.getRequest().getSession();
		Map<String, String> errorMap = validateUpdatePassword(userBean, session);
		if(errorMap.size()>0){
			ServletActionContext.getRequest().setAttribute("errors", errorMap);
			ServletActionContext.getRequest().setAttribute("userBean", userBean);
			return "updatePasswordFail";
		}
//		3������service��������֤����
		boolean b = userService.validatePassword(userBean,id);
//		4����֤ͨ���������service�������޸����룬ת����msgҳ��
		if(b){
			User user2  = userService.updatePassword(userBean,id);
			ServletActionContext.getRequest().getSession().setAttribute("user", user2);
			ServletActionContext.getRequest().setAttribute("code", "success");
			ServletActionContext.getRequest().setAttribute("msg", "�޸ĳɹ���3�����ת����ҳ��");
			ServletActionContext.getResponse().setHeader("refresh","3;url=/sshVersion/index.jsp");
			return "updatePasswordSuccess";
		}
//		5����֤��ͨ�������������Ϣ�������޸�����ҳ��
		ServletActionContext.getRequest().setAttribute("msg", "�������벻��ȷ��");
		return "updatePasswordFail";
	}
	
	//��֤ע��ҳ�������
	private Map<String, String> validateRegist(UserFormBean userBean,  HttpSession session) {
		Map<String, String> errors = new HashMap<>();
		
		String loginname = userBean.getLoginname();
		if(loginname ==null || loginname.trim().isEmpty()){
			errors.put("loginname", "�û�������Ϊ�գ�");
		}else if(loginname.length() > 20 || loginname.length() < 2){
			errors.put("loginname", "�û������ȱ�����3~20֮�䣡");
		}else if(!userService.ajaxValidateLoginName(loginname)){
			errors.put("loginname", "�û����ѱ�ע�ᣡ");
		}
		
		String loginpass = userBean.getLoginpass();
		if(loginpass ==null || loginpass.trim().isEmpty()){
			errors.put("loginpass", "���벻��Ϊ�գ�");
		}else if(loginpass.length() > 20 || loginpass.length() < 6){
			errors.put("loginpass", "���볤�ȱ�����6~20֮�䣡");
		}
		
		String reloginpass = userBean.getReloginpass();
		if(reloginpass ==null || reloginpass.trim().isEmpty()){
			errors.put("reloginpass", "ȷ�����벻��Ϊ�գ�");
		}else if(!reloginpass.equals(loginpass)){
			errors.put("reloginpass", "ȷ���������͵�¼����һ�£�");
		}
		
		String email = userBean.getEmail();
		if(email ==null || email.trim().isEmpty()){
			errors.put("email", "���䲻��Ϊ�գ�");
		}else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			errors.put("email", "������������ʽ����ȷ��");
		}else if(!userService.ajaxValidateEmail(email)){
			errors.put("email", "�����ѱ�ע�ᣡ");
		}
		
		String verifyCode = userBean.getVerifycode();
		String rverifyCode = (String) session.getAttribute("verifyCode");
		if(verifyCode ==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "��֤�벻��Ϊ�գ�");
		}else if(!verifyCode.equalsIgnoreCase(rverifyCode)){
			errors.put("verifyCode", "��֤�벻��ȷ��");
		}
		
		return errors;
	}
	//��֤��¼ҳ�������
	private Map<String, String> validateLogin(UserFormBean userBean,  HttpSession session) {
		Map<String, String> errors = new HashMap<>();
		
		String loginname = userBean.getLoginname();
		if(loginname ==null || loginname.trim().isEmpty()){
			errors.put("loginname", "�û�������Ϊ�գ�");
		}else if(loginname.length() > 20 || loginname.length() < 2){
			errors.put("loginname", "�û������ȱ�����3~20֮�䣡");
		}
		
		String loginpass = userBean.getLoginpass();
		if(loginpass ==null || loginpass.trim().isEmpty()){
			errors.put("loginpass", "���벻��Ϊ�գ�");
		}else if(loginpass.length() > 20 || loginpass.length() < 6){
			errors.put("loginpass", "���볤�ȱ�����6~20֮�䣡");
		}	
		String verifyCode = userBean.getVerifycode();
		String rverifyCode = (String) session.getAttribute("verifyCode");
		if(verifyCode ==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "��֤�벻��Ϊ�գ�");
		}else if(!verifyCode.equalsIgnoreCase(rverifyCode)){
			errors.put("verifyCode", "��֤�벻��ȷ��");
		}
		
		return errors;
	}
//	��֤�޸�����ҳ�������
	private Map<String, String> validateUpdatePassword(UserFormBean userBean,  HttpSession session) {
		Map<String, String> errors = new HashMap<>();
			
		String loginpass = userBean.getLoginpass();
		if(loginpass ==null || loginpass.trim().isEmpty()){
			errors.put("loginpass", "���벻��Ϊ�գ�");
		}else if(loginpass.length() > 20 || loginpass.length() < 6){
			errors.put("loginpass", "���볤�ȱ�����6~20֮�䣡");
		}	
		
		String newpass = userBean.getNewpass();
		if(newpass ==null || newpass.trim().isEmpty()){
			errors.put("newpass", "�����벻��Ϊ�գ�");
		}else if(loginpass.length() > 20 || loginpass.length() < 6){
			errors.put("newpass", "�����볤�ȱ�����6~20֮�䣡");
		}
		
		String reloginpass = userBean.getReloginpass();
		if(reloginpass ==null || reloginpass.trim().isEmpty()){
			errors.put("reloginpass", "ȷ�����벻��Ϊ�գ�");
		}else if(!reloginpass.equals(newpass)){
			errors.put("reloginpass", "ȷ����������������һ�£�");
		}
		
		String verifyCode = userBean.getVerifycode();
		String rverifyCode = (String) session.getAttribute("verifyCode");
		System.out.println(verifyCode);
		System.out.println(rverifyCode);
		if(verifyCode ==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "��֤�벻��Ϊ�գ�");
		}else if(!verifyCode.equalsIgnoreCase(rverifyCode)){
			errors.put("verifyCode", "��֤�벻��ȷ��");
		}
		
		return errors;
	}
}
