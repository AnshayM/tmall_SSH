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
	/**
	 * 新增
	 */
	public Integer save(Object object);

	/**
	 * 更新
	 */
	public void update(Object object);

	/**
	 * 删除
	 */
	public void delete(Object object);

	/**
	 * 根据类和id获取entity
	 */
	public Object get(Class clazz, int id);

	/**
	 * 根据id获取entity
	 */
	public Object get(int id);

	/**
	 * 查询集合
	 */
	public List list();

	/**
	 * 分页查询
	 */
	public List listByPage(Page page);

	/**
	 * 获取总条数
	 */
	public int total();

	/**
	 * 根据父类查询所有子类对象
	 */
	public List listByParent(Object parent);

	/**
	 * 以分页的方式根据父类查询子类对象集合
	 */
	public List list(Page page, Object parent);

	/**
	 * 根据父类查询子类对象数量
	 */
	public int total(Object parentObject);

	/**
	 * 多参数查询
	 */
	public List list(Object... pairParms);
}
