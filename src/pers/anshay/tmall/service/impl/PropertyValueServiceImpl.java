package pers.anshay.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.pojo.Property;
import pers.anshay.tmall.pojo.PropertyValue;
import pers.anshay.tmall.service.PropertyService;
import pers.anshay.tmall.service.PropertyValueService;

/**
 * @author Anshay
 * @date 2018年7月13日
 * @explain 产品属性值管理实现类
 */
@Service
public class PropertyValueServiceImpl extends BaseServiceImpl implements PropertyValueService {
	@Autowired
	PropertyService propertyService;

	/*
	 * PropertyValueServiceImpl继承了BaseServiceImpl 并实现了PropertyValueService接口
	 * ，所以提供了public void init(Product product) 方法的实现： 1. 这个方法的作用是初始化PropertyValue。
	 * 为什么要初始化呢？ 因为对于PropertyValue的管理，没有增加，只有修改。 所以需要通过初始化来进行自动地增加，以便于后面的修改。 2.
	 * 首先根据产品获取分类，然后获取这个分类下的所有属性集合 3. 然后用属性和产品去查询，看看这个属性和这个产品，是否已经存在属性值了。 4.
	 * 如果不存在，那么就创建一个属性值，并设置其属性和产品，接着插入到数据库中。 这样就完成了属性值的初始化。
	 */
	@Override
	public void init(Product product) {
		List<Property> propertys = propertyService.listByParent(product.getCategory());
		for (Property property : propertys) {
			PropertyValue propertyValue = get(property, product);
			if (null == propertyValue) {
				propertyValue = new PropertyValue();
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				save(propertyValue);
			}
		}
	}

	private PropertyValue get(Property property, Product product) {
		List<PropertyValue> result = this.list("property", property, "product", product);
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
}
