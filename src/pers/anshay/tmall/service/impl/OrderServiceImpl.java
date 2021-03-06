package pers.anshay.tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.anshay.tmall.pojo.Order;
import pers.anshay.tmall.pojo.OrderItem;
import pers.anshay.tmall.pojo.User;
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
	//事务管理会导致找不到transactionManager这个bean报错，目前不知道怎么解决。
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
	public float createOrder(Order order, List<OrderItem> ois) {
		save(order);
		float total = 0;
		for (OrderItem oi : ois) {
			oi.setOrder(order);
			orderItemService.update(oi);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
		}
		return total;
	}

	@Override
	public List<Order> listByUserWithoutDelete(User user) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.add(Restrictions.eq("user", user));
		// 不等于OrderService.delete
		dc.add(Restrictions.ne("status", OrderService.delete));
		return findByCriteria(dc);
	}

}
