package app.controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

	private ShoppingCartService shoppingCartService;
	
	@Autowired
	public ShoppingCartController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	@PostMapping("/product_sucess")
	public String shoppingCartSucess(@RequestParam String name, @RequestParam int size) {
		shoppingCartService.addToShoppingCart(name, size);
		return "addProductSucess";	
	}
	
	@GetMapping("/shopping_cart")
	public String getShoppingCart(Model model, HttpServletRequest request) {
		boolean isCartEmpty = true;
		if(shoppingCartService.getShoppingCart().getProductList().size() > 0)
			isCartEmpty = false;
		request.getSession().setAttribute("shoopingcart", shoppingCartService.getShoppingCart());
		model.addAttribute("products", shoppingCartService.getShoppingCart().getProductList());
		model.addAttribute("price", shoppingCartService.returnPrice());
		model.addAttribute("isCartEmpty", isCartEmpty);
		return "shoppingCart";		
	}
	
	@PostConstruct
    public void afterCreated() {
        System.out.println("wejscie do koszyka");
    }
	
	@PreDestroy
	public void destroy() {
		shoppingCartService.backItemsToData();
		System.out.println("koniec apki");
	}
}
