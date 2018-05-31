package pers.anshay.tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.anshay.tmall.dao.impl.DAOImpl;
import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.service.CategoryService;
import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年5月29日
 * @explain 实现了CategoryService 接口，提供list()方法的具体实现。 同时自动装配(注入) 了DAOImpl的实例dao。
 *          在list()方法中，通过dao获取所有的分类对象。
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	DAOImpl dao;

	@Override
	public List list() {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc);
	}

	/* 查询数据库获取分类总数 */
	@Override
	public int total() {
		String hql = "select count(*) from Category";
		List<Long> l = dao.find(hql);
		if (l.isEmpty()) {
			return 0;
		}
		Long result = l.get(0);
		return result.intValue();
	}

	/* 根据页数获取Category的查询结果集 */
	@Override
	public List<Category> listByPage(Page page) {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc, page.getStart(), page.getCount());
	}

	/* 添加新分类 */
	@Override
	public void save(Category category) {
		dao.save(category);
	}

	/* 删除分类 */
	@Override
	public void delete(Category category) {
		dao.delete(category);
	}

	/* 根据id获取指定的分类实体，用于编辑修改 */
	@Override
	public Category get(Class clazz, int id) {
		return (Category) dao.get(clazz, id);
	}

}
