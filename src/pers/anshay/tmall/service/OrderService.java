package pers.anshay.tmall.service;

import java.util.List;

import pers.anshay.tmall.pojo.Order;
import pers.anshay.tmall.pojo.OrderItem;
import pers.anshay.tmall.pojo.User;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain 订单状态常量，管理工作放在OrderItemService中
 */
public interface OrderService extends BaseService {
	public static final String waitPay = "waitPay";
	public static final String waitDelivery = "waitDelivery";
	public static final String waitConfirm = "waitConfirm";
	public static final String waitReview = "waitReview";
	public static final String finish = "finish";
	public static final String delete = "delete";

	/**
	 * 生成订单,结算总价
	 */
	public float createOrder(Order order, List<OrderItem> ois);

	/**
	 * 查询当前用户的订单列表(即未删除的订单)
	 */
	public List<Order> listByUserWithoutDelete(User user);
}
