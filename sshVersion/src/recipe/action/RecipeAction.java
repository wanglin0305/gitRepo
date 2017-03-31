package recipe.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import recipe.domain.Recipe;
import recipe.domain.RecipeCategory;
import recipe.service.RecipeService;

public class RecipeAction extends ActionSupport {
	
	private RecipeService recipeService;	
	public void setRecipeService(RecipeService recipeService) {
		this.recipeService = recipeService;
	}


	public String listCategory(){
		
		List<RecipeCategory> list = recipeService.listCategory();
		ServletActionContext.getRequest().getSession().setAttribute("rclist", list);
		System.out.println(list);
		return "listCategory";
}
	public String listAll(){
		String categoryId = ServletActionContext.getRequest().getParameter("id");
		
		List<Recipe> list = recipeService.listall(categoryId);
		
		ServletActionContext.getRequest().setAttribute("rlist",list);
		
		return "listAll";
	}

}
