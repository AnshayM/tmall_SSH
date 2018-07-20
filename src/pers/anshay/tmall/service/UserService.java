package pers.anshay.tmall.service;

import pers.anshay.tmall.pojo.User;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain 用户管理
 */
public interface UserService extends BaseService {
	/**
	 * 用户是否存在
	 */
	boolean isExist(String name);

	/**
	 * 根据用户名和密码向数据库查询记录
	 * 
	 * @return user/null
	 */
	User get(String name, String password);
}
