package test;

import java.util.List;

import org.junit.Test;
import org.springframework.orm.hibernate5.HibernateTemplate;

import recipe.dao.RecipeDao;
import recipe.dao.impl.RecipeDaoImpl;
import recipe.domain.RecipeCategory;

public class TestRecipeDao {
	
	private RecipeDao recipedao = new RecipeDaoImpl();
	
	@Test
	public void testListCategory(){
		List<RecipeCategory> list = recipedao.listcategory();
		System.out.println(list);
	}
	
}
