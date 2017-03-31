package user.domain;


import java.util.HashSet;
import java.util.Set;
import recommand.domain.Recommend;
import result.domain.Result;

//�û�
public class User {
	//�û����������ݿ��е�ΨһId
	private int userId;
	//�û���
	private String userName;
	//�û�����
	private String password;
	
	private String email;
	//�û�����
	private Integer age;
	//�û�����
	private Float weight;
	//�û��Ա���int 1 2 ��ʾ��Ů
	private Integer sex;
	//�û��Ĺ������ʣ��ᡢ�С��������Ͷ������ȼ�����int 1 2 3��ʾ
	private Integer work;
	//���Ա�Ϊ�У���Ĭ�϶���false
	//�û��Ƿ���
	private Boolean pregnant;
	//�û��Ƿ��ڲ�����
	private Boolean lactation;
	
	//������û����һ�θ������ݺ�ÿ����������ĸ�Ӫ��Ԫ�ص���
	private Set<Result> resultSet = new HashSet<Result>();
	
	private Set<Recommend> recommends = new HashSet<Recommend>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getWork() {
		return work;
	}

	public void setWork(Integer work) {
		this.work = work;
	}

	public Boolean getPregnant() {
		return pregnant;
	}

	public void setPregnant(Boolean pregnant) {
		this.pregnant = pregnant;
	}

	public Boolean getLactation() {
		return lactation;
	}

	public void setLactation(Boolean lactation) {
		this.lactation = lactation;
	}

	public Set<Result> getResultSet() {
		return resultSet;
	}

	public void setResultSet(Set<Result> resultSet) {
		this.resultSet = resultSet;
	}

	public Set<Recommend> getRecommends() {
		return recommends;
	}

	public void setRecommends(Set<Recommend> recommends) {
		this.recommends = recommends;
	}
	
	

	
	
	
}
