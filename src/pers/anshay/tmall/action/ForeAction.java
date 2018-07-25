package pers.anshay.tmall.action;

import java.util.Collections;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

import com.opensymphony.xwork2.ActionContext;

import pers.anshay.tmall.comparator.ProductAllComparator;
import pers.anshay.tmall.comparator.ProductDateComparator;
import pers.anshay.tmall.comparator.ProductPriceComparator;
import pers.anshay.tmall.comparator.ProductReviewComparator;
import pers.anshay.tmall.comparator.ProductSaleCountComparator;
import pers.anshay.tmall.pojo.User;
import pers.anshay.tmall.service.ProductImageService;

/**
 * @author Anshay
 * @date 2018年7月19日
 * @explain 前台访问控制器
 */
public class ForeAction extends Action4Result {

	/* bug：msg无法传递到前台 */
	// 返回前台的信息
	String msg;

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
			case "salcount":
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

	@Action("foreloginAjax")
	public String loginAjax() {
		user.setName(HtmlUtils.htmlEscape(user.getName()));
		User user_session = userService.get(user.getName(), user.getPassword());

		if (null == user_session) {
			return "fail.jsp";
		} else {
			return "success.jsp";
		}
	}

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

	@Action("forehome")
	public String home() {
		categorys = categoryService.list();
		productService.fill(categorys);
		productService.fillByRow(categorys);
		return "home.jsp";

	}

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

	/* 这个user怎么来的：：： */
	@Action("forelogin")
	public String login() {
		user.getName();
		// 获取前台传过来的用户名（这一步有什么作用？？）
		user.setName(HtmlUtils.htmlEscape(user.getName()));
		// 根据前台传过来的用户名和密码去查询用户，返回结果赋给user_session
		User user_session = userService.get(user.getName(), user.getPassword());
		if (null == user_session) {

			msg = "账号密码错误";
			return "login.jsp";
		}
		ActionContext.getContext().getSession().put("user", user_session);
		return "homePage";
	}

	@Action("forelogout")
	public String logout() {
		// 在session中把当前用户信息去掉即完成登出功能
		ActionContext.getContext().getSession().remove("user");
		return "homePage";
	}
}
