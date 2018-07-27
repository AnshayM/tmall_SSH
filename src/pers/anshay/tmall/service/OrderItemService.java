package pers.anshay.tmall.service;

import java.util.List;

import pers.anshay.tmall.pojo.Order;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain
 */
public interface OrderItemService extends BaseService {
	/**
	 * 递归调用为为多个订单计算
	 */
	public void fill(List<Order> orders);

	/**
	 * 为订单对象填充其orderItems字段，并且计算出订单总金额，订单总购买数量
	 */
	public void fill(Order order);

}
