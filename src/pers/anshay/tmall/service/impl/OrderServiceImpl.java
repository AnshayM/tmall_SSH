package pers.anshay.tmall.service.impl;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.anshay.tmall.pojo.Order;
import pers.anshay.tmall.pojo.OrderItem;
import pers.anshay.tmall.service.OrderItemService;
import pers.anshay.tmall.service.OrderService;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Autowired
	OrderItemService orderItemService;

	/**
	 * 生成订单
	 * 
	 * @return total,订单总价
	 */
	// 因为插入订单和修改订单要么都成功要么都失败，所以在方法前加利润事务注解
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
	public float createOrder(Order order, List<OrderItem> ois) {
		save(order);
		float total = 0;
		for (OrderItem oi : ois) {
			orderItemService.update(oi);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
		}
		return total;
	}

}
