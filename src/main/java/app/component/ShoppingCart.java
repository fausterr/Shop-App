package app.component;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.web.context.WebApplicationContext;

import app.model.Product;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart {

	private List<Product> productList;

	public ShoppingCart() {
		productList = new LinkedList<>();
	}
	
	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public void addProduct(Product product) {
        this.productList.add(product);
    }
	
	@PostConstruct
    public void afterCreated() {
        System.out.println("shoppingcart created");
    }
	
	@PreDestroy
	public void print() {
		System.out.println("shoppingcart closed");
	}
}
