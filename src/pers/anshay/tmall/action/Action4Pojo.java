package pers.anshay.tmall.action;

import java.util.List;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.pojo.ProductImage;
import pers.anshay.tmall.pojo.Property;
import pers.anshay.tmall.pojo.PropertyValue;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain 对象和集合
 */
public class Action4Pojo extends Action4Pagination {
	protected Category category;
	protected Property property;
	protected Product product;
	protected ProductImage productImage;
	protected PropertyValue propertyValue;

	protected List<Category> categorys;
	protected List<Property> propertys;
	protected List<Product> products;
	protected List<ProductImage> productSingleImages;
	protected List<ProductImage> productDetailImages;
	protected List<PropertyValue> propertyValues;

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
	 * @param propertyValues the propertyValues to set
	 */
	public void setPropertyValues(List<PropertyValue> propertyValues) {
		this.propertyValues = propertyValues;
	}

}
