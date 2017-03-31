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
			ServletActionContext.getRequest().setAttribute("msg", "����û�е�¼�����ܷ��ʱ���Դ��3�����ת����½ҳ�棡");
			ServletActionContext.getResponse().setHeader("refresh","3;url=/sshVersion/jsp/user/login.jsp");
			return "msg";
		}else{
			return invocation.invoke();
		}
	}

}
