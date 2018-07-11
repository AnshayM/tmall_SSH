package pers.anshay.tmall.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Anshay
 * @date 2018年7月10日
 * @explain 产品图片
 */

@Entity
@Table(name = "productImage")
public class ProductImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "pid")
	private Product product;

	private String type;
	 
	public int getId() {
		return id;
	}
	 
	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}
 
	public void setProduct(Product product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
