package pers.anshay.tmall.util;

/**
 * @author Anshay
 * @date 2018年5月30日
 * @explain 为分类提供必要信息
 */
/**
 * @author Anshay
 * @date 2018年5月30日
 * @explain
 */
public class Page {
	int start;// 开始页数
	int count;// 每页显示页数
	int total;// 条目总个数
	String param;// 参数

	private static final int DEFAULE_COUNT = 5;// 每页默认显示5条。

	public Page() {
		count = DEFAULE_COUNT;
	}

	public Page(int start, int count) {
		super();
		this.start = start;
		this.count = count;
	}

	/**
	 * 该条目之前是否还有其他条目
	 */
	public boolean isHasPreviouse() {
		if (start == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 通过和最后一个条目比较判断该条目之后是否还有其他条目
	 */
	public boolean isHasNext() {
		if (start == getLast()) {
			return false;
		}
		return true;
	}

	/**
	 * 获取总页数
	 */
	public int getTotalPage() {
		System.out.println("在这里调用了getTotalPage()方法");
		int totalPage = 1;
		if (0 == total % count) {
			totalPage = total / count;
		} else {
			totalPage = total / count + 1;
		}
		return totalPage;
	}

	/**
	 * 获取最后一页页码
	 */
	public int getLast() {
		int last;
		// 假设总数是50，可以被5整除，则最后一页开始是45
		if (0 == total % count) {
			last = total - count;
		} else {// 假设总数是51，不可被5整除，则最后一页开始是50
			last = total - total % count;
		}
		last = last < 0 ? 0 : last;
		return last;
	}

	public int getStart() {
		return start;
	}

	void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "Page [start=" + start + ", count=" + count + ", total=" + total + ", param=" + param
				+ ", isHasPreviouse()=" + isHasPreviouse() + ", isHasNext()=" + isHasNext() + ", getTotalPage()="
				+ getTotalPage() + ", getLast()=" + getLast() + ", getStart()=" + getStart() + ", getCount()="
				+ getCount() + ", getTotal()=" + getTotal() + ", getParam()=" + getParam() + "]";
	}

}
