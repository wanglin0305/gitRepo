package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import user.domain.User;

/**
 * Servlet Filter implementation class RecommandFilter
 */

public class LoginFilter implements Filter {

	/**
	 * @
	 *����û��Ƿ��¼��δ��¼ת����msgҳ�棬���Զ���ת����¼ҳ��
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		User user = (User) hRequest.getSession().getAttribute("user");
		if(user == null){
			hRequest.setAttribute("code", "error");
			hRequest.setAttribute("msg", "����û�е�¼�����ܷ��ʱ���Դ��3�����ת����½ҳ�棡");
			hResponse.setHeader("refresh","3;url=/sshVersion/jsp/user/login.jsp");
			hRequest.getRequestDispatcher("/msg.jsp").forward(hRequest, hResponse);
		}else{
		chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
