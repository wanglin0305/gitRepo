package result.domain;

import java.util.Date;

import user.domain.User;

public class Result {
	//结果对象在数据库中的Id
	private int resultId;
	//该用户一天所需的能量
	private float calorie;
	//该用户一天所需的碳水化合物的量
	private float carbohydrate;
	//该用户一天所需的蛋白质的量
	private float protein;
	//该用户一天所需的脂肪的量
	private float fat;
	//生成本条结果的日期
	private Date date;
	
	private User user;
	

	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}


	public float getCalorie() {
		return calorie;
	}
	
	public void setCalorie(float calorie) {
		this.calorie = calorie;
	}
	public float getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(float carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public float getProtein() {
		return protein;
	}
	public void setProtein(float protein) {
		this.protein = protein;
	}
	public float getFat() {
		return fat;
	}
	public void setFat(float fat) {
		this.fat = fat;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
