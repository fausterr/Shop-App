package app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private Long id;
	@NotEmpty
	@Size(max = 30)
	private String name;
	@NotEmpty
	@Size(max = 20)
	private String category;
	//@NotEmpty
	@PositiveOrZero
	@Max(1000)
	private int quantity;
	//@NotEmpty
	@Positive
	@Digits(integer = 5, fraction = 2)
	private double price;
	//@NotEmpty
	@PositiveOrZero
	@Max(99)
	private double discount;
	@NotEmpty
	@Size(max = 1000)
	private String description;
	
	public Product() {}

	public Product(String name, String category, int quantity, double price, double discount, String description) {
		this.name = name;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", quantity=" + quantity + ", price="
				+ price + ", discount=" + discount + ", description=" + description + "]";
	}
}
