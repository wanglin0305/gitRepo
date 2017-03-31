package filter;

import org.apache.struts2.ServletActionContext;
import org.hibernate.procedure.internal.Util.ResultClassesResolutionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import user.domain.User;

class LoginInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		if(user == null){
			ServletActionContext.getRequest().setAttribute("code", "error");
			ServletActionContext.getRequest().setAttribute("msg", "您还没有登录，不能访问本资源，3秒后跳转到登陆页面！");
			ServletActionContext.getResponse().setHeader("refresh","3;url=/sshVersion/jsp/user/login.jsp");
			return "msg";
		}else{
			return invocation.invoke();
		}
	}

}
