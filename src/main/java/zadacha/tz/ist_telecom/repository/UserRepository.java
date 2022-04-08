package zadacha.tz.ist_telecom.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import zadacha.tz.ist_telecom.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
