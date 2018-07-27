package pers.anshay.tmall.service;

import java.util.List;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.pojo.Order;
import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.pojo.Review;

/**
 * @author Anshay
 * @date 2018年7月25日
 * @explain 导航下面的产品列表
 */
public interface ReviewService extends BaseService {

	public void saveReviewAndUpdateOrderStatus(Review review, Order order);

}
