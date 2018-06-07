package pers.anshay.tmall.service.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import pers.anshay.tmall.service.BaseService;
import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain 基础接口实现类，具体业务实现类只需要继承这个接口就可以。因为使用了委派模式，即可隐藏dao的存在
 */
@Service
public class BaseServiceImpl extends ServiceDelegateDAO implements BaseService {

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
			// stes[0]是自己，setes[1]是这一条继承线上第一个继承这个类的子类
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
		return (Integer) super.save(object);
	}

	@Override
	public Object get(Class clazz, int id) {
		return super.get(clazz, id);
	}

	@Override
	public Object get(int id) {
		return get(clazz, id);
	}

	@Override
	public List listByParent(Object parent) {
		// 借助反射获取父类的类型名称
		String parentName = parent.getClass().getSimpleName();
		// 把首字母变小写
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc);
	}

	// 为什么需要首字母小写
	@Override
	public List list(Page page, Object parent) {
		String parentName = parent.getClass().getSimpleName();
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));
		dc.addOrder(Order.desc("id"));
		// 基本与方法listByParent一致，只在返回结果上有不同
		return findByCriteria(dc, page.getStart(), page.getCount());
	}

	@Override
	public int total(Object parentObject) {
		String parementName = parentObject.getClass().getSimpleName();
		String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parementName);

		String sqlFormat = "select count(*) from %s bean where bean.%s = ?";
		String hql = String.format(sqlFormat, clazz.getName(), parentNameWithFirstLetterLower);

		List<Long> l = this.find(hql, parentObject);
		if (l.isEmpty()) {
			return 0;
		}
		Long result = l.get(0);
		return result.intValue();
	}

}
