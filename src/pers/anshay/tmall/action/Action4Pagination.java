package pers.anshay.tmall.action;

import pers.anshay.tmall.util.Page;

/**
 * @author Anshay
 * @date 2018年6月5日
 * @explain 处理分页，且继承上传专用类
 */
public class Action4Pagination extends Action4Upload {
	protected Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}
