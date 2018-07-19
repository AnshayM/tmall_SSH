package pers.anshay.tmall.action;

import org.apache.struts2.convention.annotation.Action;

import pers.anshay.tmall.service.CategoryService;

/**
 * @author Anshay
 * @date 2018年7月19日
 * @explain 前台访问控制器
 */
public class ForeAction extends Action4Result {

	@Action("forehome")
	public String home() {
		categorys = categoryService.list();
		productService.fill(categorys);
		productService.fillByRow(categorys);
		return "home.jsp";

	}

}
