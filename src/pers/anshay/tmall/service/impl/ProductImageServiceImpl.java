package pers.anshay.tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.pojo.ProductImage;
import pers.anshay.tmall.service.ProductImageService;

/**
 * @author Anshay
 * @date 2018年7月10日
 * @explain
 */
@Service
public class ProductImageServiceImpl extends BaseServiceImpl implements ProductImageService {
	/* 获取第一张图片 */
	@Override
	public void setFirstProductImage(Product product) {
		if (null != product.getFirstProductImage()) {
			return;
		}
		List<ProductImage> pis = list("product", product, "type", ProductImageService.type_single);
		if (!pis.isEmpty()) {
			product.setFirstProductImage(pis.get(0));
		}
	}

}
