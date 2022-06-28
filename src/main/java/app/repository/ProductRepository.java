package app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public Product findByName(String name);
	
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.quantity = :x WHERE p.name = :name")
	public void subtracteQuantity(@Param(value = "name")String name, @Param(value = "x") int x);
	
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.quantity = p.quantity + :x WHERE p.name = :name")
	public void addQuantity(@Param(value = "name") String name, @Param(value = "x") int x);
	
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.discount = :discount WHERE p.name = :name")
	public void setDiscount(@Param(value = "name") String name, @Param(value = "discount") double discount);
	
	@Modifying
	@Transactional
	@Query("UPDATE Product p SET p.category = :category, p.quantity = :quantity, p.price = :price, p.description = :description WHERE p.name = :name")
	public void updateProduct(@Param(value = "name") String name, 
			@Param(value = "category") String category,
			@Param(value = "quantity") int quantity, 
			@Param(value = "price") double price,
			@Param(value = "description") String description);
}
