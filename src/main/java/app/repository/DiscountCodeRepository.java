package app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.model.DiscountCode;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Long>{
	
	boolean existsByValue(String value);	
	
	DiscountCode findByValue(String value);
	
	@Modifying
	@Transactional
	@Query("UPDATE DiscountCode c SET c.isActive = FALSE where c.value = :value")
	public void setCodeUnactive(@Param(value = "value") String value);	
}
