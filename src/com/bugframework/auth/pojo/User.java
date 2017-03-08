package com.bugframework.auth.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bugframework.common.pojo.IdEntity;
/**
 * 
 * <p>Title:用户信息表 </p>
 * <p>Description: 以后拓展用户信息都在这里添加或者修改</p>
 * @author 许增飞
 * @date 2016-10-10 下午11:03:34
 */
@Entity
@Table(name = "auth_user")
public class User extends IdEntity implements java.io.Serializable{
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 电子邮箱地址
	 */
	private String email;
	/**
	 * 性别标记：1-男 0-女
	 */
	private Integer sex;
	/**
	 * 长号
	 */
	private String phone1;
	/**
	 * 短号
	 */
	private String phone2;
	/**
	 * 家庭地址
	 */
	private String address1;
	/**
	 * 现住地址：一般填宿舍地址
	 */
	private String address2;
	/**
	 * 职位
	 */
	private String position;
	 

	public User() {

	}

	/**
	 * @param name
	 *            用户姓名
	 * @param email
	 *            电子邮箱地址
	 * @param sex
	 *            性别标记：1-男 0-女
	 * @param phone1
	 *            长号
	 * @param phone2
	 *            短号
	 * @param address1
	 *            家庭地址
	 * @param address2
	 *            现住地址：一般填宿舍地址
	 * @param position
	 *            职位
	 */
	public User(String name, String email, Integer sex, String phone1,
			String phone2, String address1, String address2, String position) {
		super();
		this.name = name;
		this.email = email;
		this.sex = sex;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.address1 = address1;
		this.address2 = address2;
		this.position = position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"name\":\"" + name + "\",\"email\":\"" + email
				+ "\",\"sex\":\"" + sex + "\",\"phone1\":\"" + phone1
				+ "\",\"phone2\":\"" + phone2 + "\",\"address1\":\"" + address1
				+ "\",\"address2\":\"" + address2 + "\",\"position\":\""
				+ position + "\"}";
	}

	/**
	 * 获取用户姓名
	 * 
	 * @return name 用户姓名
	 */
	@Column(name="name",length=50)
	public String getName() {
		return name;
	}

	/**
	 * 设置用户姓名
	 * 
	 * @param name
	 *            用户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取电子邮箱地址
	 * 
	 * @return email 电子邮箱地址
	 */
	@Column(name="email",length=50)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置电子邮箱地址
	 * 
	 * @param email
	 *            电子邮箱地址
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取性别标记：1-男0-女
	 * 
	 * @return sex 性别标记：1-男0-女
	 */
	@Column(name="sex")
	public Integer getSex() {
		return sex;
	}

	/**
	 * 设置性别标记：1-男0-女
	 * 
	 * @param sex
	 *            性别标记：1-男0-女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	/**
	 * 获取长号
	 * 
	 * @return phone1 长号
	 */
	@Column(name="phone1",length=20)
	public String getPhone1() {
		return phone1;
	}

	/**
	 * 设置长号
	 * 
	 * @param phone1
	 *            长号
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	/**
	 * 获取短号
	 * 
	 * @return phone2 短号
	 */
	@Column(name="phone2",length=10)
	public String getPhone2() {
		return phone2;
	}

	/**
	 * 设置短号
	 * 
	 * @param phone2
	 *            短号
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	/**
	 * 获取家庭地址
	 * 
	 * @return address1 家庭地址
	 */
	@Column(name="address1",length=100)
	public String getAddress1() {
		return address1;
	}

	/**
	 * 设置家庭地址
	 * 
	 * @param address1
	 *            家庭地址
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * 获取现住地址：一般填宿舍地址
	 * 
	 * @return address2 现住地址：一般填宿舍地址
	 */
	@Column(name="address2",length=50)
	public String getAddress2() {
		return address2;
	}

	/**
	 * 设置现住地址：一般填宿舍地址
	 * 
	 * @param address2
	 *            现住地址：一般填宿舍地址
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * 获取职位
	 * 
	 * @return position 职位
	 */
	@Column(name="position",length=50)
	public String getPosition() {
		return position;
	}

	/**
	 * 设置职位
	 * 
	 * @param position
	 *            职位
	 */
	public void setPosition(String position) {
		this.position = position;
	}
 
}
