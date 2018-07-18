package pers.anshay.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.anshay.tmall.pojo.Order;
import pers.anshay.tmall.pojo.OrderItem;
import pers.anshay.tmall.service.OrderItemService;
import pers.anshay.tmall.service.ProductImageService;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain 订单项管理
 */

@Service
public class OrderItemServiceImpl extends BaseServiceImpl implements OrderItemService {
	@Autowired
	ProductImageService productImageService;

	@Override
	public void fill(List<Order> orders) {
		for (Order order : orders) {
			fill(order);
		}
	}

	@Override
	public void fill(Order order) {
		List<OrderItem> orderItems = this.listByParent(order);
		order.setOrderItems(orderItems);

		float total = 0;
		int totalNumber = 0;
		for (OrderItem oi : orderItems) {
			total += oi.getNumber() * oi.getProduct().getPromotePrice();
			totalNumber += oi.getNumber();

			productImageService.setFirstProductImage(oi.getProduct());
		}
		order.setTotal(total);
		order.setOrderItems(orderItems);
		order.setTotalNumber(totalNumber);
	}

}
