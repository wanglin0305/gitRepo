package recipe.dao;

import java.util.List;

import recipe.domain.Recipe;
import recipe.domain.RecipeCategory;

public interface RecipeDao {
	
	List<RecipeCategory> listcategory();
	
	List<Recipe> listall(String categoryId);
}
