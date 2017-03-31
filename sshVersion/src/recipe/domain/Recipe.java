package recipe.domain;

import java.util.Map;
import java.util.Set;
//ʳ�׶���
public class Recipe {
	
	private String rId;
	private String rName;
//	private Image rImage;
	private String rSteps;

	// ��recipe�����Ǹ������recipecategory�Ƕ�Զ�Ĺ�ϵ
	private Set<RecipeCategory> categorieSet;

	// ��recipe������Щʵ����ϼ��京���Ƕ���
	private Map<String, String> fmCountMap;

	private float calorie;
	private float fat;
	private float protein;
	private float carbohydrate;

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

//	public Image getrImage() {
//		return rImage;
//	}
//
//	public void setrImage(Image rImage) {
//		this.rImage = rImage;
//	}



	public Set<RecipeCategory> getCategorieSet() {
		return categorieSet;
	}


	public String getrSteps() {
		return rSteps;
	}

	public void setrSteps(String rSteps) {
		this.rSteps = rSteps;
	}

	public void setCategorieSet(Set<RecipeCategory> categorieSet) {
		this.categorieSet = categorieSet;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}



	public Map<String, String> getFmCountMap() {
		return fmCountMap;
	}

	public void setFmCountMap(Map<String, String> fmCountMap1) {
		this.fmCountMap = fmCountMap1;
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

	public void setFat(float fat) {
		this.fat = fat;
	}

	public float getProtein() {
		return protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	public float getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(float carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	
	
	
}
