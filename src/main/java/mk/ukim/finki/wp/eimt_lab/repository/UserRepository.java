package mk.ukim.finki.wp.eimt_lab.repository;

import mk.ukim.finki.wp.eimt_lab.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}