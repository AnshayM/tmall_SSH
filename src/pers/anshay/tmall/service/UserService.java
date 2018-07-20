package pers.anshay.tmall.service;

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
}
