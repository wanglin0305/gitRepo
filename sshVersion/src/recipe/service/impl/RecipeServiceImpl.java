package recipe.service.impl;

import java.util.List;

import recipe.dao.RecipeDao;
import recipe.domain.Recipe;
import recipe.domain.RecipeCategory;
import recipe.service.RecipeService;

public class RecipeServiceImpl implements RecipeService{
	
	private RecipeDao recipeDao;
	public void setRecipeDao(RecipeDao recipeDao) {
		this.recipeDao = recipeDao;
	}

	@Override
	public List<RecipeCategory> listCategory() {
		return recipeDao.listcategory();
	}

	@Override
	public List<Recipe> listall(String categoryId) {
		return recipeDao.listall(categoryId);
	}
	
}
