package pers.anshay.tmall.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import pers.anshay.tmall.pojo.Category;
import pers.anshay.tmall.service.CategoryService;
import pers.anshay.tmall.util.ImageUtil;
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
		@Result(name = "listCategory", location = "/admin/listCategory.jsp"),
		@Result(name = "listCategoryPage", type = "redirect", location = "/admin_category_list"), })

public class CategoryAction {

	@Autowired
	CategoryService categoryService;
	List<Category> categorys;
	Page page;
	Category category;
	File img;

	/**
	 * 列表显示商品分类表
	 */
	@Action("admin_category_list")
	public String list() {
		if (page == null) {
			page = new Page();
		}
		int total = categoryService.total();
		page.setTotal(total);
		categorys = categoryService.listByPage(page);
		System.out.println(categorys);
		return "listCategory";
	}

	/**
	 * 添加分类
	 */
	@Action("admin_category_add")
	public String add() {
		categoryService.save(category);
		File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("img/category"));
		File file = new File(imageFolder, category.getId() + ".jpg");
		try {
			FileUtils.copyFile(img, file);
			BufferedImage img = ImageUtil.change2jpg(file);
			ImageIO.write(img, "jpg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 这里如果直接返回“listCategory”,则在刷新页面后会提交两次
		return "listCategoryPage";
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}
}
