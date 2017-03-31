package foodMaterial.domain;

import java.util.Set;

public class FoodMaterialCategory {
	
	private String fmCategoryId;
	private String fmCategoryName;
	
	private FoodMaterialCategory fMaterialCategory;

	
//	��foodMaterial�Ƕ�Զ�Ĺ�ϵ
	private Set<FoodMaterial> fMaterialset;
	
	public FoodMaterialCategory() {
}

	public FoodMaterialCategory(String name) {
	this.fmCategoryName = name;
}

	public String getFmCategoryId() {
		return fmCategoryId;
	}

	public void setFmCategoryId(String fmCategoryId) {
		this.fmCategoryId = fmCategoryId;
	}

	public String getFmCategoryName() {
		return fmCategoryName;
	}

	public void setFmCategoryName(String fmCategoryName) {
		this.fmCategoryName = fmCategoryName;
	}

	public FoodMaterialCategory getfMaterialCategory() {
		return fMaterialCategory;
	}

	public void setfMaterialCategory(FoodMaterialCategory fMaterialCategory) {
		this.fMaterialCategory = fMaterialCategory;
	}

	public Set<FoodMaterial> getfMaterialset() {
		return fMaterialset;
	}

	public void setfMaterialset(Set<FoodMaterial> fMaterialset) {
		this.fMaterialset = fMaterialset;
	}

	
}
