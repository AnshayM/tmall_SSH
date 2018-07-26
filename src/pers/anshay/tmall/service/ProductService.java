package pers.anshay.tmall.service;

import java.util.List;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.pojo.Product;

/**
 * @author Anshay
 * @date 2018年6月7日
 * @explain 产品接口
 */
public interface ProductService extends BaseService {

	/**
	 * 
	 * 为分类填充产品集合
	 * 
	 * @param category
	 */
	public void fill(Category category);

	/**
	 * 为多个分类填充产品集合
	 * 
	 * @param categorys
	 */
	public void fill(List<Category> categorys);

	/**
	 * 为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示
	 * 
	 * @param categorys
	 */
	public void fillByRow(List<Category> categorys);

	/**
	 * 设置销售量
	 */
	public void setSaleAndReviewNumber(List<Product> products);

	/**
	 * 设置销售量
	 */
	public void setSaleAndReviewNumber(Product product);

	/**
	 * 根据关键字模糊查询
	 */
	public List<Product> search(String keyword, int start, int count);

}
