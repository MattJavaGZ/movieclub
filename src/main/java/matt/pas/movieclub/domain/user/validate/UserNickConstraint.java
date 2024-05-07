package matt.pas.movieclub.domain.user.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNickValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNickConstraint {
    String message() default "Podana nazwa użytkownika jest już zajęta";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
