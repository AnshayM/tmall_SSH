package pers.anshay.tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import pers.anshay.tmall.dao.impl.DAOImpl;
import pers.anshay.tmall.service.BaseService;
import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain
 */
public class BaseServiceImpl implements BaseService {
	@Autowired
	DAOImpl dao;
	protected Class clazz;

	public static void main(String[] args) {
		new CategoryServiceImpl().showClass();
	}

	public void showClass() {
		System.out.println(clazz);
	}

	/**
	 * 为了获取指定的class
	 */
	public BaseServiceImpl() {
		try {
			throw new Exception();
		} catch (Exception e) {
			StackTraceElement stes[] = e.getStackTrace();
			String serviceImpleClassName = stes[1].getClassName();
			try {
				Class serviceImplClazz = Class.forName(serviceImpleClassName);
				String serviceImpleClassSimpleName = serviceImplClazz.getSimpleName();
				String pojoSimpleName = serviceImpleClassSimpleName.replaceAll("ServiceImpl", "");
				String pojoPackageName = serviceImplClazz.getPackage().getName().replaceAll(".service.impl", ".pojo");
				String pojoFullName = pojoPackageName + "." + pojoSimpleName;
				clazz = Class.forName(pojoFullName);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public List list() {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc);
	}

	@Override
	public int total() {
		String hql = "select count(*) from " + clazz.getName();
		List<Long> l = dao.find(hql);
		if (l.isEmpty()) {
			return 0;
		}
		Long result = l.get(0);
		return result.intValue();
	}

	@Override
	public List<Object> listByPage(Page page) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc, page.getStart(), page.getCount());
	}

	@Override
	public Integer save(Object object) {
		return (Integer) dao.save(object);
	}

	@Override
	public void delete(Object object) {
		dao.delete(object);
	}

	@Override
	public Object get(Class clazz, int id) {
		return dao.get(clazz, id);
	}

	@Override
	public void update(Object object) {
		dao.update(object);
	}

	@Override
	public Object get(int id) {
		return dao.get(clazz, id);
	}

}
