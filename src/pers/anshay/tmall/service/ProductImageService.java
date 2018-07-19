package pers.anshay.tmall.service;

import java.util.List;

import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.pojo.ProductImage;

/**
 * @author Anshay
 * @date 2018年7月10日
 * @explain 产品图片管理
 */
public interface ProductImageService extends BaseService {

	public static final String type_single = "type_single";
	public static final String type_detail = "type_detail";

	public void setFirstProductImage(Product product);
}
