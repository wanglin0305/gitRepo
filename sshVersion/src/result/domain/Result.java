package result.domain;

import java.util.Date;

import user.domain.User;

public class Result {
	//������������ݿ��е�Id
	private int resultId;
	//���û�һ�����������
	private float calorie;
	//���û�һ�������̼ˮ���������
	private float carbohydrate;
	//���û�һ������ĵ����ʵ���
	private float protein;
	//���û�һ�������֬������
	private float fat;
	//���ɱ������������
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
