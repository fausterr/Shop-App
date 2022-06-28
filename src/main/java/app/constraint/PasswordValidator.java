package app.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<UserPass, String>{

	@Override
	public void initialize(UserPass constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {		
		if(checkPassword(value) && checkLength(value)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkLength(String value) {
		if(value.length() > 7) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean checkPassword(String value) {
		int a = 0; //equals upperCase
		int b = 0; //equals loweCase
		int c = 0; //equals numbers
		char ch;
		for(int i = 0; i < value.length(); i++) {
			ch = value.charAt(i);
			if(Character.isUpperCase(ch)) {
				a += 1;
			} else if(Character.isLowerCase(ch)) {
				b += 1;
			} else if(Character.isDigit(ch)) {
				c += 1;
			}
		}
		if(a > 0 && b > 0 && c > 1) {
			return true;
		} else {
			return false;
		}
	}
}
