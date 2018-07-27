package pers.anshay.tmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.pojo.Order;
import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.pojo.Review;
import pers.anshay.tmall.service.OrderItemService;
import pers.anshay.tmall.service.OrderService;
import pers.anshay.tmall.service.ProductImageService;
import pers.anshay.tmall.service.ReviewService;

/**
 * @author Anshay
 * @date 2018年7月25日
 * @explain
 */
@Service
public class ReviewServiceImpl extends BaseServiceImpl implements ReviewService {
	@Autowired
	OrderService orderService;

	/* 为方法增加事务 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
	public void saveReviewAndUpdateOrderStatus(Review review, Order order) {
		orderService.update(order);
		save(review);

	}
}
