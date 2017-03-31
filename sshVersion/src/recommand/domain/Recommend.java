package recommand.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import user.domain.User;

public class Recommend {
	
	private int id;
	private User user;
	private Date date;
	
	private Set<RecommendedRecipe> recipes = new HashSet<RecommendedRecipe>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Set<RecommendedRecipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Set<RecommendedRecipe> recipes) {
		this.recipes = recipes;
	}
	
	

}
