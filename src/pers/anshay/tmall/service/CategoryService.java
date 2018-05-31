package pers.anshay.tmall.service;

import java.util.List;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年5月29日
 * @explain 接口，用于提供业务方法list()，即查询所有的分类
 */
public interface CategoryService {
	/**
	 * 列表显示
	 */
	public List list();

	/**
	 * 获取分类总数
	 */
	public int total();

	/**
	 * 获取查询结果
	 */
	public List<Category> listByPage(Page page);

	/**
	 * 添加
	 */
	public void save(Category category);
}