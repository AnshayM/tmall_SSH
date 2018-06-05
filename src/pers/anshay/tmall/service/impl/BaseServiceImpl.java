package pers.anshay.tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import pers.anshay.tmall.service.BaseService;
import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain 基础接口实现类，具体业务实现类只需要继承这个接口就可以。因为使用了微拍模式，即可去掉dao的存在
 */
public class BaseServiceImpl extends ServiceDelegateDAO implements BaseService {
	/*
	 * @Autowired DAOImpl dao;
	 */
	protected Class clazz;

	public static void main(String[] args) {
		new CategoryServiceImpl().showClass();
	}

	public void showClass() {
		System.out.println(clazz);
	}

	/**
	 * 在构造方法中，借助异常处理和反射得到Category.class或者Product.class。
	 * 即要做到哪个类继承了BaseServiceImpl，clazz 就对应哪个类对象。 比如是
	 * CategoryServiceImpl继承了BaseServiceImpl,那么这个clazz的值就是Category.class 比如是
	 * ProductServiceImpl继承了BaseServiceImpl,那么这个clazz的值就是Product.class
	 * 
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
		return findByCriteria(dc);
	}

	@Override
	public int total() {
		String hql = "select count(*) from " + clazz.getName();
		List<Long> l = find(hql);
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
		return findByCriteria(dc, page.getStart(), page.getCount());
	}

	@Override
	public Integer save(Object object) {
		return (Integer) save(object);
	}

	@Override
	public Object get(Class clazz, int id) {
		return get(clazz, id);
	}

	@Override
	public Object get(int id) {
		return get(clazz, id);
	}

	// 因为继承了ServiceDelegateDAO,所以就继承了update和delete方法
	/*
	 * @Override public void update(Object object) { dao.update(object); }
	 * 
	 * @Override public void delete(Object object) { dao.delete(object); }
	 */

}
