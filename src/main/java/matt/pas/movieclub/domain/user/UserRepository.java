package matt.pas.movieclub.domain.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    Optional<User> findByEmailIgnoreCaseAndActivTrue(String email);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByNickIgnoreCase(String nick);
}
