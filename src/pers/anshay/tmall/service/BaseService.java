package pers.anshay.tmall.service;

import java.util.List;

import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年6月4日
 * @explain 由于可以预见的在后续做产品管理，用户管理，订单管理等等，会有很多近似的CURD的接口，那么我们就做一个BaseService，里面就提供这些CRUD和分页查询的方法
 */
public interface BaseService {
	/**
	 * 使用委派模式时，ServiceDelegateDAO提供的方法，其返回类型是Serializable(自增长id)
	 * ，所以按照方法重写原则，作为其子类BaseServiceImpl重写的save方法，其返回类型只能是Serializable 或者Serializable
	 * 的子类，所以这里选择的是Integer
	 */
	public Integer save(Object object);

	public void update(Object object);

	public void delete(Object object);

	public Object get(Class clazz, int id);

	public Object get(int id);

	public List list();

	public List listByPage(Page page);

	public int total();
}
