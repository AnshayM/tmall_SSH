package pers.anshay.tmall.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain 用户类
 */

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private String password;
	private String name;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getAnonymousName() {
		if (null == name) {
			return null;
		} else if (name.length() <= 1) {
			return "*";
		} else if (name.length() == 2) {
			return name.substring(0, 1) + "*";
		}
		char[] cs = name.toCharArray();
		for (int i = 1; i < cs.length - 1; i++) {
			cs[i] = '*';
		}
		//讲字符数组转化为String类型数据
		return new String(cs);
	}
}