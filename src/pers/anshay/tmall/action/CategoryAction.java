package pers.anshay.tmall.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import pers.anshay.tmall.util.ImageUtil;
import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年5月29日
 * @explain  商品分类的控制器
 */

public class CategoryAction extends Action4Result {

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
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 这里如果直接返回“listCategory”,则在刷新页面后会提交两次
		return "listCategoryPage";
	}

	/**
	 * 删除分类
	 */
	@Action("admin_category_delete")
	public String delete() {
		categoryService.delete(category);
		return "listCategoryPage";

	}

	/**
	 * 对指定的分类实体进行编辑
	 */
	@Action("admin_category_edit")
	public String edit() {
		// int id = category.getId();
		// // category = categoryService.get(Category.class, id);
		// category = (Category) categoryService.get(id);
		t2p(category);
		return "editCategory";
	}

	/**
	 * 更新
	 */
	@Action("admin_category_update")
	public String update() {
		categoryService.update(category);
		if (null != img) {
			File imageFolder = new File(ServletActionContext.getServletContext().getRealPath("img/category"));
			File file = new File(imageFolder, category.getId() + ".jpg");
			try {
				FileUtils.copyFile(img, file);
				BufferedImage img = ImageUtil.change2jpg(file);
				ImageIO.write(img, "jpg", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "listCategoryPage";
	}

}
