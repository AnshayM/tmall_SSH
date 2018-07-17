package pers.anshay.tmall.action;

import org.apache.struts2.convention.annotation.Action;

import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain 用户管理
 */
public class UserAction extends Action4Result {
	// 分页显示所有用户列表
	@Action("admin_user_list")
	public String list() {
		if (page == null) {
			page = new Page();
		}
		int total = userService.total();
		page.setTotal(total);
		users = userService.listByPage(page);
		return "listUser";
	}
}
