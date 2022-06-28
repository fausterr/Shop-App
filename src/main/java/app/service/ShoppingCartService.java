package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import app.component.ShoppingCart;
import app.model.Product;
import app.repository.ProductRepository;

@Service
public class ShoppingCartService {

	private ShoppingCart shoppingCart;
	private ProductRepository productRepo;	
	
	@Autowired
	public ShoppingCartService(ShoppingCart shoppingCart, ProductRepository productRepo) {	
		this.shoppingCart = shoppingCart;
		this.productRepo = productRepo;
	}
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void addToShoppingCart(String name, int size) {
		Product product = new Product();
		product = productRepo.findByName(name);
		int size2 = product.getQuantity() - size;
		product.setQuantity(size);
		shoppingCart.addProduct(product);
		productRepo.subtracteQuantity(name, size2);
		System.out.print(product.toString());
	}
	
	public void backItemsToData() {  
		for(Product p: shoppingCart.getProductList()) {
			String name = productRepo.findByName(p.getName()).getName();
			System.out.println(name);
			productRepo.addQuantity(name, p.getQuantity());
		}
	}
	
	public double returnPrice() {
		List<Product> products = shoppingCart.getProductList();
		double price = 0;
		double temp;
		for(Product p: products) {
			if(p.getDiscount()>0) {
				temp = p.getPrice()*p.getQuantity()*(1-p.getDiscount());
				price += temp;
			} else {
				temp = p.getPrice()*p.getQuantity();
				price += temp;
			}
		}
		return price;
	}
}
