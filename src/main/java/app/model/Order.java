package app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private Long id;
	private double finalPrice;
	private String orderNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	@OneToOne
	private ClientDetails clientDetails;
	@ManyToOne
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;
	@OneToOne
	private DiscountCode discountCode; 
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToMany
	@JoinTable(name = "order_products",
	joinColumns = {@JoinColumn(name="order_id", referencedColumnName="id_order")},
	inverseJoinColumns = {@JoinColumn(name="product_id", referencedColumnName="id_product")})
	private List<Product> products;
	
	public Order() {}

	public Order(double finalPrice, LocalDate createdDate, ClientDetails clientDetails, Delivery delivery,
			DiscountCode discountCode, User user, List<Product> products) {
		this.finalPrice = finalPrice;
		this.createdDate = createdDate;
		this.clientDetails = clientDetails;
		this.delivery = delivery;
		this.discountCode = discountCode;
		this.user = user;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public ClientDetails getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(ClientDetails clientDetails) {
		this.clientDetails = clientDetails;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public DiscountCode getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(DiscountCode discountCode) {
		this.discountCode = discountCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}