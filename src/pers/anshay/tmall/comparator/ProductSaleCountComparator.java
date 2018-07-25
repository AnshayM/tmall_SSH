package pers.anshay.tmall.comparator;

import java.util.Comparator;

import pers.anshay.tmall.pojo.Product;

/**
 * @author Anshay
 * @date 2018年7月25日
 * @explain 销量比较器，把销售量高的放在前面
 */
public class ProductSaleCountComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		return p2.getSaleCount() - p1.getSaleCount();
	}

}
