package app.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Delivery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_delivery")
	private Long id;
	private TypeDelivery typeDelivery;
	private double price;
	private int maxTimeInDays;  
	@OneToMany(mappedBy = "delivery")
	private List<Order> orders = new ArrayList<>();

	public Delivery() {}
	
	public Delivery(TypeDelivery typeDelivery, double price, int maxTimeInDays) {
		this.typeDelivery = typeDelivery;
		this.price = price;
		this.maxTimeInDays = maxTimeInDays;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeDelivery getTypeDelivery() {
		return typeDelivery;
	}

	public void setTypeDelivery(TypeDelivery typeDelivery) {
		this.typeDelivery = typeDelivery;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getMaxTimeInDays() {
		return maxTimeInDays;
	}

	public void setMaxTimeInDays(int maxTimeInDays) {
		this.maxTimeInDays = maxTimeInDays;
	}
}
