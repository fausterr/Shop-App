package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.model.ClientDetails;

public interface ClientDetailsRepository extends JpaRepository<ClientDetails, Long>{

}
