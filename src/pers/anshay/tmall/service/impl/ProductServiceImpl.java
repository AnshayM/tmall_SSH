package pers.anshay.tmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.service.OrderItemService;
import pers.anshay.tmall.service.ProductImageService;
import pers.anshay.tmall.service.ProductService;
import pers.anshay.tmall.service.ReviewService;

/**
 * @author Anshay
 * @date 2018年6月7日
 * @explain
 */

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Autowired
	ProductImageService productImageService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	ReviewService reviewService;

	@Override
	public List<Product> search(String keyword, int start, int count) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.add(Restrictions.like("name", "%" + keyword + "%"));
		return findByCriteria(dc, start, count);
	}

	@Override
	public void setSaleAndReviewNumber(Product product) {
		int saleCount = orderItemService.total(product);
		product.setSaleCount(saleCount);
		int reviewCount = reviewService.total(product);
		product.setReviewCount(reviewCount);
	}

	@Override
	public void setSaleAndReviewNumber(List<Product> products) {
		for (Product product : products) {
			setSaleAndReviewNumber(product);
		}
	}

	@Override
	public void fill(List<Category> categorys) {
		for (Category category : categorys) {
			fill(category);
		}
	}


	@Override
	public void fillByRow(List<Category> categorys) {
		// 一个分类对应多行产品，一行产品对应多个产品记录
		// 设置推荐数量为8个
		int productNumberEachRow = 8;
		for (Category category : categorys) {
			List<Product> products = category.getProducts();
			List<List<Product>> productsByRow = new ArrayList<>();
			for (int i = 0; i < products.size(); i += productNumberEachRow) {
				int size = i + productNumberEachRow;
				size = size > products.size() ? products.size() : size;
				List<Product> productsOfEachRow = products.subList(i, size);
				productsByRow.add(productsOfEachRow);
			}
			category.setProductsByRow(productsByRow);
		}
	}

	@Override
	public void fill(Category category) {
		// 获取该分类下的产品集合
		List<Product> products = listByParent(category);
		// 为每个产品配置缩略图
		for (Product product : products) {
			productImageService.setFirstProductImage(product);
		}
		// 将配置了缩略图的产品集合更新到分类下
		category.setProducts(products);
	}
}
