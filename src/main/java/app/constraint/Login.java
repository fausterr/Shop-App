package app.constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = LoginValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface Login {

	String message() default "Login musi sie skladac z malej i wielkiej litery "
			+ "oraz miec co najmniej 6 znaków";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
