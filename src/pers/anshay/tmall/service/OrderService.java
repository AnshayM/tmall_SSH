package pers.anshay.tmall.service;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain 订单状态常量
 */
public interface OrderService extends BaseService {
	public static final String waitPay = "waitPay";
	public static final String waitDelivery = "waitDelivery";
	public static final String waitConfirm = "waitConfirm";
	public static final String waitReview = "waitReview";
	public static final String finish = "finish";
	public static final String delete = "delete";
}
