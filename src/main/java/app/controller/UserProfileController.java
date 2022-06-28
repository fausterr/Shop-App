package app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.dto.UserDTO;
import app.model.Order;
import app.service.OrderService;
import app.service.UserService;

@Controller
public class UserProfileController {
	
	private UserService userService;
	private OrderService orderService;
	
	public UserProfileController(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}
	
	@GetMapping("/profile/password")
	public String changePassword(Model model) {
		model.addAttribute("user", new UserDTO());
		return "changePassword";
	}
	
	@PostMapping("/profile/password")
	public String changePasswordForm(@ModelAttribute @Valid UserDTO user,
			BindingResult bindResult) {
			user.setLogin(UserService.getCurrentUser());
			if(bindResult.hasErrors()) {
				return "changePassword";
			} else {
				userService.changePassword(user);
				return "print";
			}	
	}
	
	@GetMapping("/profile")
	public String getUserHomePage(Model model) {
		String login = UserService.getCurrentUser();
		boolean isAdmin = UserService.hasRole("ROLE_ADMIN");
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("user", userService.getUserByLogin(login));
		return "profile";
	}
	
	@GetMapping("/profile/orders")
	public String showOrders(Model model) {
		Long userId = userService.getUserByLogin(UserService.getCurrentUser()).getId();
		List<Order> orders = orderService.findAllByUserId(userId);
		model.addAttribute("orders", orders);
		return "orders2";	
	}
}
