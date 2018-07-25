package pers.anshay.tmall.comparator;

import java.util.Comparator;

import pers.anshay.tmall.pojo.Product;

/**
 * @author Anshay
 * @date 2018年7月25日
 * @explain 人气比较器，把评论数量多的放在前面
 */
public class ProductReviewComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getReviewCount() - p1.getReviewCount();
	}

}
