package matt.pas.movieclub.domain.user.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserEmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserEmailConstraint {
    String message() default "Podany adres email jest już zajęty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
