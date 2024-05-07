package matt.pas.movieclub.domain.user.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import matt.pas.movieclub.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserNickValidator implements ConstraintValidator<UserNickConstraint, String> {
    private UserRepository userRepository;

    public UserNickValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(UserNickConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String nick, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByNickIgnoreCase(nick);
    }
}
