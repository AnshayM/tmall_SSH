/**
 * 
 */
package pers.anshay.tmall.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;

import pers.anshay.tmall.service.OrderService;
import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @createDate 2018年7月18日
 * @explain 订单的控制器
 */
public class OrderAction extends Action4Result {

	/**
	 * 列表显示订单
	 */
	@Action("admin_order_list")
	public String list() {
		if (page == null) {
			page = new Page();
		}
		int total = orderService.total();
		page.setTotal(total);
		orders = orderService.listByPage(page);
		orderItemService.fill(orders);

		return "listOrder";
	}

	/**
	 * 投递订单，更改订单状态为待确认
	 */
	@Action("admin_order_delivery")
	public String delivery() {
		t2p(order);
		order.setDeliveryDate(new Date());
		order.setStatus(OrderService.waitConfirm);
		orderService.update(order);
		return "listOrderPage";
	}
}
