package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class VerifyCodeAction  extends ActionSupport{
	
	public String execute() throws IOException{
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();//��ȡһ������֤��ͼƬ
		
		// �÷���������getImage()����֮�������ã�д�뵽response��
		VerifyCode.output(image, ServletActionContext.getResponse().getOutputStream());
	
		// ���ı����浽session�У�ΪLoginServlet��֤��׼��
		//		(vc.getText())//��ȡͼƬ�ϵ��ı�
		ServletActionContext.getRequest().getSession().setAttribute("verifyCode", vc.getText());
		return null;
	}
	
	}

