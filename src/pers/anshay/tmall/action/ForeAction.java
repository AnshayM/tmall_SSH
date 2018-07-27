package pers.anshay.tmall.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.math.RandomUtils;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

import com.opensymphony.xwork2.ActionContext;

import pers.anshay.tmall.comparator.ProductAllComparator;
import pers.anshay.tmall.comparator.ProductDateComparator;
import pers.anshay.tmall.comparator.ProductPriceComparator;
import pers.anshay.tmall.comparator.ProductReviewComparator;
import pers.anshay.tmall.comparator.ProductSaleCountComparator;
import pers.anshay.tmall.pojo.OrderItem;
import pers.anshay.tmall.pojo.Product;
import pers.anshay.tmall.pojo.User;
import pers.anshay.tmall.service.OrderService;
import pers.anshay.tmall.service.ProductImageService;

/**
 * @author Anshay
 * @date 2018年7月19日
 * @explain 前台访问控制器
 */
public class ForeAction extends Action4Result {

	/**
	 * 删除订单
	 */
	@Action("foredeleteOrder")
	public String deleteOrder() {
		t2p(order);
		order.setStatus(orderService.delete);
		orderService.update(order);
		return "success.jsp";
	}

	/**
	 * 确认收货
	 */
	@Action("foreorderConfirmed")
	public String orderConfirmed() {
		t2p(order);
		order.setStatus(OrderService.waitReview);
		order.setConfirmDate(new Date());
		orderService.update(order);
		return "orderConfirmed.jsp";
	}

	/**
	 * 确认付款
	 */
	@Action("foreconfirmPay")
	public String confirmPay() {
		t2p(order);
		orderItemService.fill(order);
		return "confirmPay.jsp";
	}

	/**
	 * 查询我的订单
	 */
	@Action("foreboughat")
	public String bought() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		orders = orderService.listByUserWithoutDelete(user);
		orderItemService.fill(orders);
		return "bought.jsp";
	}

	/**
	 * 跳转已支付状态
	 */
	@Action("forepayed")
	public String payed() {
		t2p(order);
		order.setStatus(OrderService.waitDelivery);
		order.setPayDate(new Date());
		orderService.update(order);
		return "payed.jsp";
	}

	/**
	 * 支付
	 */
	@Action("forealipay")
	public String forelipay() {
		return "alipay.jsp";
	}

	/**
	 * 生成订单
	 */
	@Action("forecreateOrder")
	public String createOrder() {
		List<OrderItem> ois = (List<OrderItem>) ActionContext.getContext().getSession().get("orderItems");
		if (ois.isEmpty()) {
			return "login.jsp";
		}
		User user = (User) ActionContext.getContext().getSession().get("user");
		// 生成订单号的粗暴方法
		String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);

		order.setOrderCode(orderCode);
		order.setCreateDate(new Date());
		order.setUser(user);
		// 新建的订单状态设定为待支付
		order.setStatus(orderService.waitPay);

		total = orderService.createOrder(order, ois);

		return "success.jsp";
	}

	/**
	 * 删除
	 */
	@Action("foredeleteOrderItem")
	public String deleteOrderItem() {
		orderItemService.delete(orderItem);
		return "success.jsp";
	}

	/**
	 * 修改数量
	 */
	@Action("forechangeOrderItem")
	public String changeOrderItem() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		List<OrderItem> ois = orderItemService.list("user", user, "order", null);

		for (OrderItem oi : ois) {
			oi.setNumber(num);
			orderItemService.update(oi);
			break;
		}
		return "success.jsp";
	}

	/**
	 * 进入购物车
	 */
	@Action("forecart")
	public String cart() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		orderItems = orderItemService.list("user", user, "order", null);
		for (OrderItem orderItem : orderItems) {
			productImageService.setFirstProductImage(orderItem.getProduct());
		}
		return "cart.jsp";
	}

	/**
	 * 新增订单项OrderItem， 新增订单项要考虑两个情况
	 * 
	 * a.如果已经存在这个产品对应的OrderItem，并且还没有生成订单，即还在购物车中。 那么就应该在对应的OrderItem基础上，调整数量 a.1
	 * 基于用户对象user，查询没有生成订单的订单项集合 a.2 遍历这个集合 a.3 如果产品是一样的话，就进行数量追加 a.4 获取这个订单项的 id
	 * 
	 * b. 如果不存在对应的OrderItem,那么就新增一个订单项OrderItem b.1 生成新的订单项 b.2 设置数量，用户和产品 b.3
	 * 插入到数据库 b.4 获取这个订单项的 id
	 * 
	 */
	@Action("foreaddCart")
	public String addCart() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean found = false;
		List<OrderItem> ois = orderItemService.list("user", user, "order", null);
		// 在购物车里遍历所有产品，如果有匹配，增加相应的产品的数量
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId() == product.getId()) {
				oi.setNumber(oi.getNumber() + num);
				orderItemService.update(oi);
				found = true;
				break;
			}
		}
		if (!found) {
			OrderItem oi = new OrderItem();
			oi.setUser(user);
			oi.setNumber(num);
			oi.setProduct(product);
			orderItemService.save(oi);
		}

		return "success.jsp";
	}

	/**
	 * 购买
	 */
	@Action("forebuy")
	public String buy() {
		orderItems = new ArrayList<>();
		for (int oiid : oiids) {
			OrderItem oi = (OrderItem) orderItemService.get(oiid);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
			orderItems.add(oi);
			productImageService.setFirstProductImage(oi.getProduct());
		}
		ActionContext.getContext().getSession().put("orderItems", orderItems);
		return "buy.jsp";
	}

	/**
	 * 商品信息栏点击立即购买 首先从session中获取用户对象user
	 * 
	 * 接下来就是新增订单项OrderItem， 新增订单项要考虑两个情况
	 * 
	 * 果已经存在这个产品对应的OrderItem，并且还没有生成订单，即还在购物车中。 那么就应该在对应的OrderItem基础上，调整数量 a.1
	 * 基于用户对象user，查询没有生成订单的订单项集合 a.2 遍历这个集合 a.3 如果产品是一样的话，就进行数量追加 a.4 获取这个订单项的 id
	 * 
	 * 如果不存在对应的OrderItem,那么就新增一个订单项OrderItem b.1 生成新的订单项 b.2 设置数量，用户和产品 b.3 插入到数据库
	 * b.4 获取这个订单项的 id
	 * 
	 * 最后， 基于这个订单项id客户端跳转到结算页面的 对应的/forebu
	 */
	@Action("forebuyone")
	public String buyone() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		boolean found = false;
		List<OrderItem> ois = orderItemService.list("user", user, "order", null);
		for (OrderItem oi : ois) {
			if (oi.getProduct().getId() == product.getId()) {
				oi.setNumber(oi.getNumber() + num);
				orderItemService.update(oi);
				found = true;
				oiid = oi.getId();
				break;
			}
		}
		if (!found) {
			OrderItem oi = new OrderItem();
			oi.setUser(user);
			oi.setNumber(num);
			oi.setProduct(product);
			orderItemService.save(oi);
			oiid = oi.getId();
		}
		return "buyPage";
	}

	/**
	 * 查询
	 */
	@Action("foresearch")
	public String search() {
		products = productService.search(keyword, 0, 20);
		productService.setSaleAndReviewNumber(products);
		for (Product product : products) {
			productImageService.setFirstProductImage(product);
		}
		return "searchResult.jsp";
	}

	/**
	 * 点击顶部分类导航
	 */
	@Action("forecategory")
	public String category() {
		// 持久化对象的深沉意义，没有会怎样
		t2p(category);
		productService.fill(category);
		productService.setSaleAndReviewNumber(category.getProducts());

		if (null != sort) {
			switch (sort) {
			case "review":
				Collections.sort(category.getProducts(), new ProductReviewComparator());
				break;
			case "date":
				Collections.sort(category.getProducts(), new ProductDateComparator());
				break;
			case "saleCount":
				Collections.sort(category.getProducts(), new ProductSaleCountComparator());
				break;
			case "price":
				Collections.sort(category.getProducts(), new ProductPriceComparator());
				break;
			case "all":
				Collections.sort(category.getProducts(), new ProductAllComparator());
				break;
			}
		}
		return "category.jsp";
	}

	/**
	 * 在点击立即购买时弹出登录窗口（这个窗口和前面的登录窗口的区别）
	 */
	@Action("foreloginAjax")
	public String loginAjax() {
		user.setName(HtmlUtils.htmlEscape(user.getName()));
		User user_session = userService.get(user.getName(), user.getPassword());

		if (null == user_session) {
			return "fail.jsp";
		}
		ActionContext.getContext().getSession().put("user", user_session);
		return "success.jsp";

	}

	/**
	 * 检查当前登录状态（在哪里调用的）
	 */
	@Action("forecheckLogin")
	public String checkLogin() {
		User u = (User) ActionContext.getContext().getSession().get("user");
		if (null == u) {
			return "fail.jsp";
		} else {
			return "success.jsp";
		}
	}

	/**
	 * 1. 把product 指向持久化对象 2. 设置首张图片 3. 设置单个和详情图片集合 4. 获取本产品的属性值集合 5. 获取本产品的评价集合 6.
	 * 设置销售数量和评价数量 7. 服务端跳转到 product.jsp
	 */
	@Action("foreproduct")
	public String product() {
		t2p(product);

		productImageService.setFirstProductImage(product);
		productSingleImages = productImageService.list("product", product, "type", ProductImageService.type_single);
		productDetailImages = productImageService.list("product", product, "type", ProductImageService.type_detail);
		product.setProductSingleImages(productSingleImages);
		product.setProductDetailImages(productDetailImages);

		propertyValues = propertyValueService.listByParent(product);

		reviews = reviewService.listByParent(product);

		productService.setSaleAndReviewNumber(product);

		return "product.jsp";
	}

	/**
	 * 登出
	 */
	@Action("forelogout")
	public String logout() {
		// 在session中把当前用户信息去掉即完成登出功能
		ActionContext.getContext().getSession().remove("user");
		return "homePage";
	}

	/* 这个user怎么来的：：： */
	/**
	 * 登陆
	 */
	@Action("forelogin")
	public String login() {
		user.setName(HtmlUtils.htmlEscape(user.getName()));
		// 根据前台传过来的用户名和密码去查询用户，返回结果赋给user_session
		User user_session = userService.get(user.getName(), user.getPassword());
		if (null == user_session) {
			/* bug：msg无法传递到前台 */
			msg = "账号密码错误";
			return "login.jsp";
		}
		ActionContext.getContext().getSession().put("user", user_session);
		return "homePage";
	}

	/**
	 * 注册
	 */
	@Action("foreregister")
	public String register() {
		user.setName(HtmlUtils.htmlEscape(user.getName()));
		boolean exist = userService.isExist(user.getName());
		if (exist) {
			msg = "用户名已被使用，不能使用";
			return "register.jsp";
		}
		userService.save(user);
		return "registerSuccessPage";
	}

	/**
	 * 前台主页
	 */
	@Action("forehome")
	public String home() {
		categorys = categoryService.list();
		productService.fill(categorys);
		productService.fillByRow(categorys);
		return "home.jsp";

	}

}
