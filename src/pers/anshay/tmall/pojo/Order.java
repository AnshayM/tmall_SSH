package pers.anshay.tmall.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import pers.anshay.tmall.service.OrderService;

/**
 * @author Anshay
 * @date 2018年7月17日
 * @explain 已经生成的订单
 */
@Entity
@Table(name = "order_")

public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "uid")
	private User user;

	private String orderCode;
	private String address;
	private String post;
	private String receiver;
	private String mobile;
	private String userMessage;
	private Date createDate;
	private Date payDate;
	private Date deliveryDate;
	private Date confirmDate;
	private String status;

	// Order有三个瞬时字段，他们都是不参与持久化的
	@Transient
	private List<OrderItem> orderItems;
	@Transient
	private float total;
	@Transient
	private int totalNumber;

	// 用于返回订单状态对应的中文解释
	public String getStatusDesc() {
		String desc = "未知";
		switch (status) {
		case OrderService.waitPay:
			desc = "待付";
			break;
		case OrderService.waitDelivery:
			desc = "待发";
			break;
		case OrderService.waitConfirm:
			desc = "待收";
			break;
		case OrderService.waitReview:
			desc = "待评";
			break;
		case OrderService.finish:
			desc = "完成";
			break;
		case OrderService.delete:
			desc = "删除";
			break;
		default:
			desc = "未知";
		}
		return desc;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode
	 *            the orderCode to set
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the post
	 */
	public String getPost() {
		return post;
	}

	/**
	 * @param post
	 *            the post to set
	 */
	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver
	 *            the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * @return the mobile
	 */
	public String getmobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setmobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the userMessage
	 */
	public String getUserMessage() {
		return userMessage;
	}

	/**
	 * @param userMessage
	 *            the userMessage to set
	 */
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the payDate
	 */
	public Date getPayDate() {
		return payDate;
	}

	/**
	 * @param payDate
	 *            the payDate to set
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate
	 *            the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the confirmDate
	 */
	public Date getConfirmDate() {
		return confirmDate;
	}

	/**
	 * @param confirmDate
	 *            the confirmDate to set
	 */
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the orderItems
	 */
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	/**
	 * @param orderItems
	 *            the orderItems to set
	 */
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	/**
	 * @return the total
	 */
	public float getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(float total) {
		this.total = total;
	}

	/**
	 * @return the totalNumber
	 */
	public int getTotalNumber() {
		return totalNumber;
	}

	/**
	 * @param totalNumber
	 *            the totalNumber to set
	 */
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

}
