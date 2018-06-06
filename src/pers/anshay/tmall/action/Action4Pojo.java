package pers.anshay.tmall.action;

import java.util.List;

import pers.anshay.tmall.pojo.Category;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain 对象和集合
 */
public class Action4Pojo extends Action4Pagination{
	protected Category category;
	protected List<Category> categorys;

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

}
