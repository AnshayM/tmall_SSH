package pers.anshay.tmall.comparator;

import java.util.Comparator;

import pers.anshay.tmall.pojo.Product;

/**
 * @author Anshay
 * @date 2018年7月25日
 * @explain 综合比较器，把销量*评价高的放在前面
 */
public class ProductAllComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getReviewCount() * p2.getSaleCount() - p1.getReviewCount() * p1.getSaleCount();
	}

}
