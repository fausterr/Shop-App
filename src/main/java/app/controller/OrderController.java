package app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.component.ShoppingCart;
import app.model.ClientDetails;
import app.model.Delivery;
import app.model.DiscountCode;
import app.model.Product;
import app.repository.ClientDetailsRepository;
import app.service.DeliveryService;
import app.service.DiscountCodeService;
import app.service.OrderService;

@Controller
public class OrderController {

	private ClientDetailsRepository clientDetailsRepository;
	private DeliveryService deliveryService;
	private OrderService orderService;
	private DiscountCodeService discountCodeService;
	
	public OrderController(ClientDetailsRepository clientDetailsRepository, 
			DeliveryService deliveryService,
			OrderService orderService,
			DiscountCodeService discountCodeService) {			
		this.clientDetailsRepository = clientDetailsRepository;
		this.deliveryService = deliveryService;
		this.orderService  = orderService;
		this.discountCodeService = discountCodeService;
	}
	
	@GetMapping("/orderDetails")
	public String getOrder(Model model) {
		Map<String, Object> modelMap = new HashMap<>();	
		modelMap.put("discountCode", new DiscountCode());
		modelMap.put("clientDetails", new ClientDetails());
		modelMap.put("delivery", new Delivery());
		model.addAllAttributes(modelMap);	
		return "orderDetails";
	}	
	
	@PostMapping("/orderDetails") 
	public String saveOrder(
			@ModelAttribute Delivery delivery,
			@ModelAttribute @Valid ClientDetails clientDetails,
			@ModelAttribute @Valid DiscountCode discountCode,
			BindingResult bindResult, HttpServletRequest request) {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoopingcart");
		List<Product> products = shoppingCart.getProductList();	
		DiscountCode code = discountCodeService.getByValue(discountCode.getValue());
		if(bindResult.hasErrors()) {
			return "orderDetails";
		} else {
			clientDetailsRepository.save(clientDetails);
			deliveryService.saveDeliveryFromPost(delivery);
			orderService.createCurrentOrder(clientDetails, delivery, code, products);
			return "summary"; 
		}
	}
}
