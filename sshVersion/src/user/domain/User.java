package user.domain;


import java.util.HashSet;
import java.util.Set;
import recommand.domain.Recommend;
import result.domain.Result;

//用户
public class User {
	//用户对象在数据库中的唯一Id
	private int userId;
	//用户名
	private String userName;
	//用户密码
	private String password;
	
	private String email;
	//用户年龄
	private Integer age;
	//用户体重
	private Float weight;
	//用户性别，用int 1 2 表示男女
	private Integer sex;
	//用户的工作性质，轻、中、重体力劳动三个等级，用int 1 2 3表示
	private Integer work;
	//若性别为男，则默认都是false
	//用户是否怀孕
	private Boolean pregnant;
	//用户是否处于哺乳期
	private Boolean lactation;
	
	//计算出用户最后一次更新数据后每天所需摄入的各营养元素的量
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
