package matt.pas.movieclub.domain.user.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import matt.pas.movieclub.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserEmailValidator implements ConstraintValidator<UserEmailConstraint, String> {

    private UserRepository userRepository;

    public UserEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserEmailConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByEmailIgnoreCase(email);
    }
}
