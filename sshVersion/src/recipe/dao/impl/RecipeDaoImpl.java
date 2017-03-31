package recipe.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import recipe.dao.RecipeDao;
import recipe.domain.Recipe;
import recipe.domain.RecipeCategory;

public class RecipeDaoImpl implements RecipeDao {
	
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<RecipeCategory> listcategory() {
		String sql = "from RecipeCategory";
		List<RecipeCategory> list = (List<RecipeCategory>) hibernateTemplate.find(sql);
		return list;
	}

	@Override
	public List<Recipe> listall(String categoryId) {
		RecipeCategory recipeCategory = 
				hibernateTemplate.get(RecipeCategory.class, categoryId);
		
		List<Recipe> list = new ArrayList<Recipe>(recipeCategory.getrSet());
		return list;
	}

}
