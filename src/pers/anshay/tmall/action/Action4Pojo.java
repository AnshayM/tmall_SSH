package pers.anshay.tmall.action;

import java.util.List;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.pojo.Property;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain 对象和集合
 */
public class Action4Pojo extends Action4Pagination {
	protected Category category;
	protected Property property;
	protected List<Category> categorys;
	protected List<Property> propertys;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public List<Property> getPropertys() {
		return propertys;
	}

	public void setPropertys(List<Property> propertys) {
		this.propertys = propertys;
	}

}
