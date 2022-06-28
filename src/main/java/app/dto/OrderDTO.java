package app.dto;

public class OrderDTO {
	
	private String orderNumber;
	
	public OrderDTO() {}
	
	public OrderDTO(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}	
}
