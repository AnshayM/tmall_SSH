package pers.anshay.tmall.action;

import java.util.List;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.pojo.Order;
import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.pojo.ProductImage;
import pers.anshay.tmall.pojo.Property;
import pers.anshay.tmall.pojo.PropertyValue;
import pers.anshay.tmall.pojo.User;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain 对象和集合，继承分页管理类
 */
public class Action4Pojo extends Action4Pagination {
	protected Category category;
	protected Property property;
	protected Product product;
	protected ProductImage productImage;
	protected PropertyValue propertyValue;
	protected User user;
	protected Order order;

	protected List<Category> categorys;
	protected List<Property> propertys;
	protected List<Product> products;
	protected List<ProductImage> productSingleImages;
	protected List<ProductImage> productDetailImages;
	protected List<PropertyValue> propertyValues;
	protected List<User> users;
	protected List<Order> orders;

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductImage getProductImage() {
		return productImage;
	}

	public void setProductImage(ProductImage productImage) {
		this.productImage = productImage;
	}

	public List<ProductImage> getProductSingleImages() {
		return productSingleImages;
	}

	public void setProductSingleImages(List<ProductImage> productSingleImages) {
		this.productSingleImages = productSingleImages;
	}

	public List<ProductImage> getProductDetailImages() {
		return productDetailImages;
	}

	public void setProductDetailImages(List<ProductImage> productDetailImages) {
		this.productDetailImages = productDetailImages;
	}

	/**
	 * @return the propertyValues
	 */
	public List<PropertyValue> getPropertyValues() {
		return propertyValues;
	}

	/**
	 * @param propertyValues
	 *            the propertyValues to set
	 */
	public void setPropertyValues(List<PropertyValue> propertyValues) {
		this.propertyValues = propertyValues;
	}

	/**
	 * @return the propertyValue
	 */
	public PropertyValue getPropertyValue() {
		return propertyValue;
	}

	/**
	 * @param propertyValue
	 *            the propertyValue to set
	 */
	public void setPropertyValue(PropertyValue propertyValue) {
		this.propertyValue = propertyValue;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * @param orders
	 *            the orders to set
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
