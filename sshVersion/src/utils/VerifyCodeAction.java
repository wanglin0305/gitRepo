package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("serial")
public class VerifyCodeAction  extends ActionSupport{
	
	public String execute() throws IOException{
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();//获取一次性验证码图片
		
		// 该方法必须在getImage()方法之后来调用，写入到response中
		VerifyCode.output(image, ServletActionContext.getResponse().getOutputStream());
	
		// 把文本保存到session中，为LoginServlet验证做准备
		//		(vc.getText())//获取图片上的文本
		ServletActionContext.getRequest().getSession().setAttribute("verifyCode", vc.getText());
		return null;
	}
	
	}

