package pers.anshay.tmall.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

import pers.anshay.tmall.service.CategoryService;

/**
 * @author Anshay
 * @date 2018年7月19日
 * @explain 前台访问控制器
 */
public class ForeAction extends Action4Result {
	// 返回前台的信息
	String msg;

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

}
