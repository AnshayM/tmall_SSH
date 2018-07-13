package pers.anshay.tmall.service;

import java.util.List;

import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.pojo.ProductImage;

/**
 * @author Anshay
 * @date 2018年7月10日
 * @explain
 */
public interface ProductImageService extends BaseService {

	public static final String type_single = "type_single";
	public static final String type_detail = "type_detail";

	// public List<ProductImage> list(String key_product, Product product, String
	// key_type, String type);
	public void setFirstProductImage(Product product);
}
