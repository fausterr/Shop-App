package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{

}
