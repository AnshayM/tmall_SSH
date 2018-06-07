package pers.anshay.tmall.action;

import java.lang.reflect.Method;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import pers.anshay.tmall.service.CategoryService;
import pers.anshay.tmall.service.ProductService;
import pers.anshay.tmall.service.PropertyService;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain 提供服务的注入
 */
public class Action4Service extends Action4Pojo {
	@Autowired
	CategoryService categoryService;
	@Autowired
	PropertyService propertyService;
	@Autowired
	ProductService productService;

	/**
	 * transient to persistent 瞬时对象转换为持久对象
	 */
	public void t2p(Object o) {
		try {
			Class clazz = o.getClass();
			int id = (Integer) clazz.getMethod("getId").invoke(o);
			Object persistentBean = categoryService.get(clazz, id);

			String beanName = clazz.getSimpleName();
			Method setMethod = getClass().getMethod("set" + WordUtils.capitalize(beanName), clazz);
			setMethod.invoke(this, persistentBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
