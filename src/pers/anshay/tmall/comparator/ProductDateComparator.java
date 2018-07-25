package pers.anshay.tmall.comparator;

import java.util.Comparator;

import pers.anshay.tmall.pojo.Product;

/**
 * @author Anshay
 * @date 2018年7月25日
 * @explain 新品比较器，把创建日期玩的放前面
 */
public class ProductDateComparator implements Comparator<Product> {

	@Override
	public int compare(Product p1, Product p2) {
		return p1.getCreateDate().compareTo(p2.getCreateDate());
	}

}
