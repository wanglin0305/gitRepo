package recipe.service;

import java.util.List;

import recipe.domain.Recipe;
import recipe.domain.RecipeCategory;

public interface RecipeService {
	List<RecipeCategory> listCategory();
	
	List<Recipe> listall(String categoryId);
}
