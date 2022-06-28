package app.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.dto.OrderDTO;
import app.dto.TimeDTO;
import app.model.DiscountCode;
import app.model.Order;
import app.model.Product;
import app.model.User;
import app.repository.ProductRepository;
import app.service.DiscountCodeService;
import app.service.OrderService;
import app.service.UserService;

@Controller
public class AdminProfileController {

	private UserService userService;
	private OrderService orderService;
	private DiscountCodeService discountCodeService;
	private ProductRepository productRepository;
	
	AdminProfileController(UserService userService, OrderService orderService, 
			DiscountCodeService discountCodeService, ProductRepository productRepository) {
		this.userService = userService;
		this.orderService = orderService;
		this.discountCodeService = discountCodeService;
		this.productRepository = productRepository;
	}
	
	@GetMapping("/admin/profile")
	public String adminProfile() {
		return "adminProfile";
	}
//-----------------------------------------------------------------------	
	@GetMapping("/admin/secretRegister")
	public String adminRegister(Model model) {
		model.addAttribute("user", new User());
		return "adminRegisterForm";
	}
	
	@PostMapping("/admin/secretRegister")
	public String addAdmin(@ModelAttribute @Valid User user,
			BindingResult bindResult) {
		if(bindResult.hasErrors())
			return "adminRegisterForm";
		else {
			userService.addAdminWithAdminRole(user);
			return "registerSuccess2";
		}
	}	
//-----------------------------------------------------------------------
	@GetMapping("/admin/addDiscountCode")
	public String addDiscountCode(Model model) {
		model.addAttribute("discountCode", new DiscountCode());
		return "addCode";
	}
	
	@PostMapping("/admin/saveDiscountCode")
	public String postDiscountCode(@ModelAttribute DiscountCode discountCode) {
		discountCode.setActive(true);
		discountCodeService.addDiscountCode(discountCode);
		return "addCodeSucess";
	}			
//-----------------------------------------------------------------------
	@GetMapping("/admin/orderDate")
	public String getOrderByDate(Model model) {
		model.addAttribute("time", new TimeDTO());
		return "orderDateForm";
	}
	
	@PostMapping("/admin/orderDate")
	public String postOrderByDate(@ModelAttribute TimeDTO time, Model model) {
		
		LocalDate date = LocalDate.parse(time.toString());
		List<Order> orders = orderService.findByDate(date);
		model.addAttribute("orders", orders); 
		return "orderByDate";
	}
//-----------------------------------------------------------------------
	@GetMapping("/admin/orderNumber")
	public String getOrderByOrderNumber(Model model) {
		model.addAttribute("order", new OrderDTO());
		return "orderByNumberForm";
	}
	
	@PostMapping("/admin/orderNumber")
	public String postOrderByOrderNumber(@ModelAttribute OrderDTO orderDTO, Model model) {
		Order order = orderService.findByOrderNumber(orderDTO.getOrderNumber());
		System.out.println("NUMBER!!!!   " + orderDTO.getOrderNumber());
		System.out.println(order.getOrderNumber());
		System.out.println(order.getFinalPrice());
		System.out.println(order.getCreatedDate());
		model.addAttribute("order", order);
		return "orderByNumber";
	}

//---------------------------------------------------------------------------
	@GetMapping("/admin/updateDiscount")
	public String setDiscountOdProduct(Model model) {
		model.addAttribute("product", new Product());
		return "updateProductDiscount";
	}
		
	@PostMapping("/admin/updateDiscount")
	public String postDiscountOnProduct(@ModelAttribute Product product) {
		productRepository.setDiscount(product.getName(), product.getDiscount());
		return "redirect:/admin/profile";
	}
}
