package app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findAllByUserId(Long id);
	
	List<Order> findByCreatedDate(LocalDate date);

	Order findByOrderNumber(String number);
}
