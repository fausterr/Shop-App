package app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import app.model.ClientDetails;
import app.model.Delivery;
import app.model.DiscountCode;
import app.model.Order;
import app.model.Product;
import app.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	private UserService userService;
	
	public OrderService(OrderRepository orderRepository, UserService userService) {
		this.orderRepository = orderRepository;
		this.userService = userService;
	}
	
	public List<Order> findAllByUserId(Long userId) {
		return orderRepository.findAllByUserId(userId);
	}
	
	public List<Order> findByDate(LocalDate date) {
		return orderRepository.findByCreatedDate(date);
	}
	
	public Order findByOrderNumber(String number) {
		return orderRepository.findByOrderNumber(number);
	}
	
	public void createCurrentOrder(ClientDetails clientDetails, Delivery delivery, DiscountCode discountCode,
			List<Product> products) {
		Order order = new Order();
		order.setClientDetails(clientDetails);
		order.setDelivery(delivery);
		order.setDiscountCode(discountCode);
		order.setProducts(products);
		order.setUser(userService.getUserByLogin(UserService.getCurrentUser()));
		order.setFinalPrice(calculateFinalPrise(discountCode, delivery, products));
		order.setCreatedDate(LocalDate.now());
		order.setOrderNumber(orderNumberGenerator());
		orderRepository.save(order).getId(); //to bez sensu
	}
	
	public void saveOrderInSession(Order oderd, HttpServletRequest request) {
		request.getSession().setAttribute(null, request);
	}
	
	public double calculateFinalPrise(DiscountCode discountCode, Delivery delivery, List<Product> products) {
		double productsPrice = 0;
		for(Product p: products) {
			productsPrice += p.getPrice()*p.getQuantity();
		}
		double result = productsPrice * (1 - (0.01*discountCode.getDiscount())) + delivery.getPrice(); 
		return result;
	}
	
	public String orderNumberGenerator() {
		LocalDateTime time= LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
		String formatDateTime = time.format(formatter);		
		Random random=new Random();
		int randomNumber = random.nextInt(89)+10;
		String formatRandomNumber = String.valueOf(randomNumber);
		String orderNumber = formatDateTime + formatRandomNumber;
		return orderNumber;
	}
}
