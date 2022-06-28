package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import app.model.Product;
import app.repository.ProductRepository;

@Controller
public class ProductController {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@GetMapping("/admin/addProduct")
	public String addProduct(Model model) {		
		model.addAttribute("product", new Product());
		return "addProduct";
	}
	
	@PostMapping("/admin/saveProduct")
	public String saveProduct(@ModelAttribute Product product) {
		productRepository.save(product);
		return "redirect:/admin/profile";
	}

//---------------------------------------------------------------------------

	@GetMapping("/admin/updateProduct")
	public String updateProduct(Model model) {
		model.addAttribute("product", new Product());
		return "updateProduct";
	}
	
	@PostMapping("/admin/updateProduct")
	public String postUpdateProduct(@ModelAttribute Product product) {
		productRepository.updateProduct(product.getName(), 
				product.getCategory(), 
				product.getQuantity(),
				product.getPrice(), 
				product.getDescription());
		return "redirect:/admin/profile";
	}

//---------------------------------------------------------------------------
	@GetMapping("/showAllProducts")
	public String getAllProducts(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "getAllProducts";
	}
	
	@GetMapping("showAllProducts/sorted/{sortParam}")
	public String getSortedProducts(@PathVariable String sortParam, Model model) { 
		model.addAttribute("sortedProducts", getSortedProducts(sortParam));
		return "getAllSortedProducts";
	}
	
	private List<Product> getSortedProducts(String sortParam) {
		List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, sortParam));
		return products;
	}
}
