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
	
//	user模块的表单数据封装bean
	private UserFormBean userBean = new UserFormBean();
	public UserFormBean getModel() {
		return userBean;
	}

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 校验注册名是否已经注册过
	 * 
	 * @return 不转发也不重定向，只是向response中写入一个布尔值。用于jquery的ajax过程检验数据。
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
	 * 校验email是否已经注册过
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
	 * 校验验证码是否正确
	 * 产生验证码图片的时候，将正确的验证码放到session中
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

	// 用户注册提交表单后的处理方法
	public String regist() {
//		1、校验表单数据，如果失败，保存错误信息，返回到regist.jsp页面
		HttpSession session = ServletActionContext.getRequest().getSession();
		Map<String, String > errorMap = validateRegist(userBean,session);
		if(errorMap.size()>0){
			ServletActionContext.getRequest().setAttribute("errors", errorMap);
			return "registFail";
		}
		
		
//		2、调用service的regist方法完成注册
		int id = userService.regist(userBean);

//		3、保存成功信息，转发到某个显示页面
		ServletActionContext.getRequest().setAttribute("code", "success");
		ServletActionContext.getRequest().setAttribute("msg", "注册成功！3秒后跳转到登录页面！");
		ServletActionContext.getResponse().setHeader("refresh","3;url=/sshVersion/jsp/user/login.jsp");
		return "registSuccess";
	}
	
	public String login(){
//		1、封装表单数据，自动封装到userBean	
//		2、校验表单数据
		HttpSession session = ServletActionContext.getRequest().getSession();
		Map<String, String> errorMap = validateLogin(userBean, session);
		if(errorMap.size()>0){
			ServletActionContext.getRequest().setAttribute("errors", errorMap);
			return "loginFail";
		}
//		3、调用service的方法，查找user对象
		User user = userService.login(userBean);
//		4、若user存在，
//		1）、将当前用户保存到session中
//		2）、将用户名保存到cookie中，下次登陆时显示用户名
//		3）、重定向到index.jsp
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
//		5、若user不存在，登录失败
//		1）、保存错误信息
//		2）、保存表单信息
//		3）、返回login.jsp
		ServletActionContext.getRequest().setAttribute("msg", "用户名或密码不正确！");
		ServletActionContext.getRequest().setAttribute("userBean", userBean);
		return "loginFail";
	}
	
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "logout";
	}
	
	public String updatePassword(){
		
//		1、封装数据userBean,loginpass是老密码，newpass是新密码，reloginpass是新的确认密码
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			ServletActionContext.getRequest().setAttribute("msg", "您还没有登录！");
			return "loginFail";
		}
		int id = user.getUserId();
		System.out.println(id);
		System.out.println(userBean.getLoginpass());
//		2、校验表单数据
		HttpSession session = ServletActionContext.getRequest().getSession();
		Map<String, String> errorMap = validateUpdatePassword(userBean, session);
		if(errorMap.size()>0){
			ServletActionContext.getRequest().setAttribute("errors", errorMap);
			ServletActionContext.getRequest().setAttribute("userBean", userBean);
			return "updatePasswordFail";
		}
//		3、调用service方法，验证数据
		boolean b = userService.validatePassword(userBean,id);
//		4、验证通过，则调用service方法，修改密码，转发到msg页面
		if(b){
			User user2  = userService.updatePassword(userBean,id);
			ServletActionContext.getRequest().getSession().setAttribute("user", user2);
			ServletActionContext.getRequest().setAttribute("code", "success");
			ServletActionContext.getRequest().setAttribute("msg", "修改成功，3秒后跳转到首页！");
			ServletActionContext.getResponse().setHeader("refresh","3;url=/sshVersion/index.jsp");
			return "updatePasswordSuccess";
		}
//		5、验证不通过，保存错误信息，返回修改密码页面
		ServletActionContext.getRequest().setAttribute("msg", "密码输入不正确！");
		return "updatePasswordFail";
	}
	
	//验证注册页面表单数据
	private Map<String, String> validateRegist(UserFormBean userBean,  HttpSession session) {
		Map<String, String> errors = new HashMap<>();
		
		String loginname = userBean.getLoginname();
		if(loginname ==null || loginname.trim().isEmpty()){
			errors.put("loginname", "用户名不能为空！");
		}else if(loginname.length() > 20 || loginname.length() < 2){
			errors.put("loginname", "用户名长度必须在3~20之间！");
		}else if(!userService.ajaxValidateLoginName(loginname)){
			errors.put("loginname", "用户名已被注册！");
		}
		
		String loginpass = userBean.getLoginpass();
		if(loginpass ==null || loginpass.trim().isEmpty()){
			errors.put("loginpass", "密码不能为空！");
		}else if(loginpass.length() > 20 || loginpass.length() < 6){
			errors.put("loginpass", "密码长度必须在6~20之间！");
		}
		
		String reloginpass = userBean.getReloginpass();
		if(reloginpass ==null || reloginpass.trim().isEmpty()){
			errors.put("reloginpass", "确认密码不能为空！");
		}else if(!reloginpass.equals(loginpass)){
			errors.put("reloginpass", "确认密码必须和登录密码一致！");
		}
		
		String email = userBean.getEmail();
		if(email ==null || email.trim().isEmpty()){
			errors.put("email", "邮箱不能为空！");
		}else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")){
			errors.put("email", "您输入的邮箱格式不正确！");
		}else if(!userService.ajaxValidateEmail(email)){
			errors.put("email", "邮箱已被注册！");
		}
		
		String verifyCode = userBean.getVerifycode();
		String rverifyCode = (String) session.getAttribute("verifyCode");
		if(verifyCode ==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空！");
		}else if(!verifyCode.equalsIgnoreCase(rverifyCode)){
			errors.put("verifyCode", "验证码不正确！");
		}
		
		return errors;
	}
	//验证登录页面表单数据
	private Map<String, String> validateLogin(UserFormBean userBean,  HttpSession session) {
		Map<String, String> errors = new HashMap<>();
		
		String loginname = userBean.getLoginname();
		if(loginname ==null || loginname.trim().isEmpty()){
			errors.put("loginname", "用户名不能为空！");
		}else if(loginname.length() > 20 || loginname.length() < 2){
			errors.put("loginname", "用户名长度必须在3~20之间！");
		}
		
		String loginpass = userBean.getLoginpass();
		if(loginpass ==null || loginpass.trim().isEmpty()){
			errors.put("loginpass", "密码不能为空！");
		}else if(loginpass.length() > 20 || loginpass.length() < 6){
			errors.put("loginpass", "密码长度必须在6~20之间！");
		}	
		String verifyCode = userBean.getVerifycode();
		String rverifyCode = (String) session.getAttribute("verifyCode");
		if(verifyCode ==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空！");
		}else if(!verifyCode.equalsIgnoreCase(rverifyCode)){
			errors.put("verifyCode", "验证码不正确！");
		}
		
		return errors;
	}
//	验证修改密码页面表单数据
	private Map<String, String> validateUpdatePassword(UserFormBean userBean,  HttpSession session) {
		Map<String, String> errors = new HashMap<>();
			
		String loginpass = userBean.getLoginpass();
		if(loginpass ==null || loginpass.trim().isEmpty()){
			errors.put("loginpass", "密码不能为空！");
		}else if(loginpass.length() > 20 || loginpass.length() < 6){
			errors.put("loginpass", "密码长度必须在6~20之间！");
		}	
		
		String newpass = userBean.getNewpass();
		if(newpass ==null || newpass.trim().isEmpty()){
			errors.put("newpass", "新密码不能为空！");
		}else if(loginpass.length() > 20 || loginpass.length() < 6){
			errors.put("newpass", "新密码长度必须在6~20之间！");
		}
		
		String reloginpass = userBean.getReloginpass();
		if(reloginpass ==null || reloginpass.trim().isEmpty()){
			errors.put("reloginpass", "确认密码不能为空！");
		}else if(!reloginpass.equals(newpass)){
			errors.put("reloginpass", "确认密码必须和新密码一致！");
		}
		
		String verifyCode = userBean.getVerifycode();
		String rverifyCode = (String) session.getAttribute("verifyCode");
		System.out.println(verifyCode);
		System.out.println(rverifyCode);
		if(verifyCode ==null || verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空！");
		}else if(!verifyCode.equalsIgnoreCase(rverifyCode)){
			errors.put("verifyCode", "验证码不正确！");
		}
		
		return errors;
	}
}
