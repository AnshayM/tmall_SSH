package pers.anshay.tmall.action;

import java.io.File;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain 处理图片上传
 */
public class Action4Upload {
	protected File img;
	protected String imgFileName;
	protected String imgContentType;

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

}
