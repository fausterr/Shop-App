package app.service;

import org.springframework.stereotype.Service;

import app.model.DiscountCode;
import app.repository.DiscountCodeRepository;

@Service
public class DiscountCodeService {

	private DiscountCodeRepository discountCodeRepository;
	
	public DiscountCodeService(DiscountCodeRepository discountCodeRepository) {
		this.discountCodeRepository = discountCodeRepository;
	}
	
	public DiscountCode getByValue(String value) {
		return discountCodeRepository.findByValue(value);
	}

	public boolean isExist(String code) {
		return discountCodeRepository.existsByValue(code);
	}
	
	public void setCodeUnactive(String value) {
		discountCodeRepository.setCodeUnactive(value);
	}
	
	public void addDiscountCode(DiscountCode discountCode) {
		discountCodeRepository.save(discountCode);
	}
}
