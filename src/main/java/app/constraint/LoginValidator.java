package app.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<Login, String>{

	@Override
	public void initialize(Login constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(checkLength(value) && checkLogin(value)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkLength(String value) {
		if(value.length() > 5) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkLogin(String value) {
		int a = 0; //equals upperCase
		int b = 0; //equals lowerCase
		char ch;
		for(int i = 0; i < value.length(); i++) {
			ch = value.charAt(i);
			if(Character.isUpperCase(ch)) {
				a += 1;
			} else if(Character.isLowerCase(ch)) {
				b += 1;
			}
		}
		if(a > 0 && b > 0) {
			return true;
		} else {
			return false;
		}
	}
}
