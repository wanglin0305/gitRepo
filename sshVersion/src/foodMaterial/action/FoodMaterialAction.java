package foodMaterial.action;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Servlet;
import javax.servlet.jsp.PageContext;

import org.apache.catalina.connector.Request;
import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.opensymphony.xwork2.ActionSupport;

import foodMaterial.domain.FMPageBean;
import foodMaterial.domain.FoodMaterial;
import foodMaterial.domain.FoodMaterialCategory;
import foodMaterial.service.FoodMaterialService;

public class FoodMaterialAction extends ActionSupport{
	
	private FoodMaterialService fms;
	public void setFms(FoodMaterialService fms) {
		this.fms = fms;
	}

//	չʾһ����������
	public String listCategory(){
		List<FoodMaterialCategory> list = fms.listCategory();
		ServletActionContext.getRequest().setAttribute("fmclist", list);
		return "listCategory";
		
	}
//	չʾĳһ�����������ж�����������
	public String listSecondCategory(){
		String categoryId = ServletActionContext.getRequest().getParameter("id");
		List<FoodMaterialCategory> list = fms.listSecondCategory(categoryId);
		ServletActionContext.getRequest().setAttribute("secondFmcList", list);
		return "listSecondCategory";
	}
//	չʾĳ��������������ʳ������
	public String listAll(){
		String categoryId = ServletActionContext.getRequest().getParameter("id");

		List<FoodMaterial> list = fms.listAll(categoryId);
		ServletActionContext.getRequest().setAttribute("fmlist", list);
		return "listAll";
	}
//	��ҳ��ʾ����������ʳ������
	public String listPage(){
		Integer currentPage = 1;
				try {
					currentPage = Integer.parseInt(ServletActionContext.getRequest().getParameter("currentpage"));
				} catch (Exception e) {
					e.printStackTrace();
				};
		String categoryId = ServletActionContext.getRequest().getParameter("id");
		FMPageBean pageBean = fms.listPage(currentPage,categoryId);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		ServletActionContext.getRequest().setAttribute("id", categoryId);
		return "listPage";
	}
//	չʾͨ�����Ʋ��һ��߿�·�ﷶΧ���ҵ�ʳ��
	public String findFM(){
		String name = ServletActionContext.getRequest().getParameter("name");
		String min = ServletActionContext.getRequest().getParameter("min");
		String max = ServletActionContext.getRequest().getParameter("max");
		List<FoodMaterial> list = fms.findFm(name,min,max);
		ServletActionContext.getRequest().setAttribute("fmlist", list);
		return "findFM";
	}
//	չʾÿ��ʳ�ĵ���ϸ��Ϣ
	public String fMDetail(){
		String fmId = ServletActionContext.getRequest().getParameter("id");
		FoodMaterial fm = fms.getFmById(fmId);
		ServletActionContext.getRequest().setAttribute("fm", fm);
		
		return "fMDetail";
	}
}
