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
	 * 为什么需要返回一个Integer而不是void
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
