package app.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.service.DiscountCodeService;

public class DiscountCodeValidator implements ConstraintValidator<Code, String>{

	private DiscountCodeService discountCodeService;
	
	public DiscountCodeValidator(DiscountCodeService discountCodeService) {
		this.discountCodeService = discountCodeService;
	}
	
	@Override
	public void initialize(Code constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(discountCodeService.isExist(value)) {
			discountCodeService.setCodeUnactive(value);
			return true;
		}
		 else {
			 return false;
		 }			
	}
}
