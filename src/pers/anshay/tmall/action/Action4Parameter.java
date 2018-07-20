package pers.anshay.tmall.action;

import org.apache.struts2.ServletActionContext;

/**
 * @author Anshay
 * @date 2018年7月19日
 * @explain
 */
public class Action4Parameter extends Action4Service {
	// 错误信息
	String msg;

	// 分类页面的排序变量
	protected String sort;

	// 当前所处于的web应用名称
	protected String contextPath;

	// 搜索关键字
	protected String keyword;

	// 购买数量
	protected int num;

	// 立即生成的订单项id
	protected int oiid;

	// 通过购物车选中的多个订单项id
	protected int[] oiidso;

	// 结算页面显示的总金额
	protected float taotal;

	// 在进行评论的页面，是否只显示评论记录，而不提供输入
	protected boolean showonly;

	public Action4Parameter() {
		contextPath = ServletActionContext.getServletContext().getContextPath();
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the contextPath
	 */
	public String getContextPath() {
		return contextPath;
	}

	/**
	 * @param contextPath
	 *            the contextPath to set
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the oiid
	 */
	public int getOiid() {
		return oiid;
	}

	/**
	 * @param oiid
	 *            the oiid to set
	 */
	public void setOiid(int oiid) {
		this.oiid = oiid;
	}

	/**
	 * @return the oiidso
	 */
	public int[] getOiidso() {
		return oiidso;
	}

	/**
	 * @param oiidso
	 *            the oiidso to set
	 */
	public void setOiidso(int[] oiidso) {
		this.oiidso = oiidso;
	}

	/**
	 * @return the taotal
	 */
	public float getTaotal() {
		return taotal;
	}

	/**
	 * @param taotal
	 *            the taotal to set
	 */
	public void setTaotal(float taotal) {
		this.taotal = taotal;
	}

	/**
	 * @return the showonly
	 */
	public boolean isShowonly() {
		return showonly;
	}

	/**
	 * @param showonly
	 *            the showonly to set
	 */
	public void setShowonly(boolean showonly) {
		this.showonly = showonly;
	}

}
