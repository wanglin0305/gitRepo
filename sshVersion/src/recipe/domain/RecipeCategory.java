package recipe.domain;

import java.util.Set;

public class RecipeCategory {
	private String rcId;
	private String rcname;
	private RecipeCategory parentCategory;
	private Set<Recipe> rSet;
	
	
	
	public RecipeCategory() {
	}
	public RecipeCategory(String rcname) {
		this.rcname = rcname;
	}
	public String getRcId() {
		return rcId;
	}
	public void setRcId(String rcId) {
		this.rcId = rcId;
	}
	public String getRcname() {
		return rcname;
	}
	public void setRcname(String rcname) {
		this.rcname = rcname;
	}
	public RecipeCategory getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(RecipeCategory parentCategory) {
		this.parentCategory = parentCategory;
	}
	public Set<Recipe> getrSet() {
		return rSet;
	}
	public void setrSet(Set<Recipe> rSet) {
		this.rSet = rSet;
	}
	
}
