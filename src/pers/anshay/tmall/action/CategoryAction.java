package pers.anshay.tmall.action;

import java.util.List;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.service.CategoryService;
import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年5月29日
 * @explain CategoryAction 类作为MVC设计模式中的控制层起作用。 1.
 *          使用basicstruts，与接下来的配置文件struts.xml中定义的basicstruts保持一致 2.
 *          在Result注解中，定义了返回的页面为/admin/listCategory.jsp 3.
 *          自动装配（注入）categoryService对象，用于从数据库获取所有分类对象的集合。 4.
 *          把对admin_category_list路径的访问映射到list方法上 5.
 *          list()方法通过categoryService获取到所有的分类对象，放在categorys属性中。 6. 同时提供了
 *          getCategorys() 方法，用于向listCategory.jsp页面传递数据
 */

@Namespace("/")
@ParentPackage("basicstruts")
@Results({
		/* 分类管理 */
		@Result(name = "listCategory", location = "/admin/listCategory.jsp"), })
public class CategoryAction {

	@Autowired
	CategoryService categoryService;

	List<Category> categorys;

	Page page;

	@Action("admin_category_list")
	public String list() {
		if (page == null) {
			page = new Page();
		}
		int total = categoryService.total();
		page.setTotal(total);
		// categorys = categoryService.list();
		categorys = categoryService.listByPage(page);
		System.out.println(categorys);
		return "listCategory";
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
