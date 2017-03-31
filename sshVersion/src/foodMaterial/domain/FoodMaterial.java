package foodMaterial.domain;

import java.util.Set;
//食物材料
public class FoodMaterial {
	private String fId;
	private String fName;
	private String otherName;
	
	private Set<String> otherNameSet;
	private float calorie;
	private float fat;
	private float protein;
	private float cabohydrate;
	
	private Set<FoodMaterialCategory> fMaterialCategorieSet;
	

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	
	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public float getCalorie() {
		return calorie;
	}

	public void setCalorie(float calorie) {
		this.calorie = calorie;
	}

	public float getFat() {
		return fat;
	}
	
	public Set<String> getOtherNameSet() {
		return otherNameSet;
	}

	public void setOtherNameSet(Set<String> otherNameSet) {
		this.otherNameSet = otherNameSet;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	public float getProtein() {
		return protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	public float getCabohydrate() {
		return cabohydrate;
	}

	public void setCabohydrate(float cabohydrate) {
		this.cabohydrate = cabohydrate;
	}

	public Set<FoodMaterialCategory> getfMaterialCategorieSet() {
		return fMaterialCategorieSet;
	}

	public void setfMaterialCategorieSet(Set<FoodMaterialCategory> fMaterialCategorieSet) {
		this.fMaterialCategorieSet = fMaterialCategorieSet;
	}

	@Override
	public String toString() {
		
		return this.getfName() + ":" + this.getOtherName() + ";\n" +
		"热量：" +this.getCalorie() + ";\n" + "碳水：" + this.getCabohydrate() + ";\n" +
				"蛋白质："  + this.getProtein() + ";\n" + "脂肪：" + this.getFat() + ";";
		}

}
