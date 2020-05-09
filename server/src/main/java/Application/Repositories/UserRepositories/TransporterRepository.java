package Application.Repositories.UserRepositories;

import Application.Entites.Users.Staff;
import Application.Entites.Users.Transporter;
import Application.Entites.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransporterRepository extends /*UserRepository*/ JpaRepository<Transporter, Long> {

    Optional<Transporter> findByUsername(String username);
    Transporter findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
