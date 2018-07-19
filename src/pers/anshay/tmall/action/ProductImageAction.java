package pers.anshay.tmall.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import pers.anshay.tmall.service.ProductImageService;
import pers.anshay.tmall.util.ImageUtil;

/**
 * @author Anshay
 * @date 2018年7月10日
 * @explain 产品图片的控制器
 */
public class ProductImageAction extends Action4Result {

	@Action("admin_productImage_list")
	public String list() {
		productSingleImages = productImageService.list("product", product, "type", productImageService.type_single);
		productDetailImages = productImageService.list("product", product, "type", productImageService.type_detail);
		t2p(product);
		return "listProductImage";
	}

	/*
	 * 1.把productImage对象插入到数据库 2.根据productImage的type字段值，确定文件存放的目录名称
	 * 3.根据ServletActionContext.getServletContext().getRealPath(folder) 定位硬盘上的真正位置
	 * 4.根据插入到数据库之后，productImage的id，确定文件名称，并结合第2条的目录位置，确定文件的绝对路径
	 * 5.因为ProductAction间接地继承了上传专用Action4Upload类，所以提供了一个img对象以接受上传的文件，不过这个img是一个临时文件
	 * ，通过FileUtils. copyFile把这个临时文件复制到第4条描述的文件绝对路径。 6. 通过ImageUtil确保图片的真正格式是jpg.
	 * 7.如果图片的类型是type_single,借助ImageUtil.resizeImage把正常大小的图片，改变大小之后，
	 * 分别复制到productSingle_middle和productSingle_small目录下。
	 * 8.最后，返回listProductImagePage，在完整版的Action4Result中，
	 * 会导致客户端跳转到admin_productImage_list,并带上产品的id。
	 */
	@Action("admin_productImage_add")
	public String add() {
		productImageService.save(productImage);
		String folder = "img/";
		if (ProductImageService.type_single.equals(productImage.getType())) {
			folder += "productSingle";
		} else {
			folder += "productDetail";
		}

		File imageFolder = new File(ServletActionContext.getServletContext().getRealPath(folder));
		File file = new File(imageFolder, productImage.getId() + ".jpg");
		String fileName = file.getName();
		try {
			FileUtils.copyFile(img, file);
			BufferedImage img = ImageUtil.change2jpg(file);
			ImageIO.write(img, "jpg", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (ProductImageService.type_single.equals(productImage.getType())) {
			String imageFolder_small = ServletActionContext.getServletContext().getRealPath("img/productSingle_small");
			String imageFolder_middle = ServletActionContext.getServletContext()
					.getRealPath("img/productSingel_middle");

			File f_small = new File(imageFolder_small, fileName);
			File f_middle = new File(imageFolder_middle, fileName);

			f_small.getParentFile().mkdirs();
			f_middle.getParentFile().mkdirs();

			ImageUtil.resizeImage(file, 56, 56, f_small);
			ImageUtil.resizeImage(file, 217, 190, f_middle);
		}
		return "listProductImagePage";
	}

	@Action("admin_productImage_delete")
	public String delete() {
		t2p(productImage);
		productService.delete(productImage);
		return "listProductImagePage";
	}

}
