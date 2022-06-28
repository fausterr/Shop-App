package app.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.service.UserService;

public class ChangePasswordValidator implements ConstraintValidator<UserChangePass, String>{

	private UserService userService;
	
	public ChangePasswordValidator(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void initialize(UserChangePass constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(userService.checkPassword(value))
			return true;
		else
			return false;
	}
}
