package Application.Repositories.UserRepositories;

import Application.Entites.Users.Staff;
import Application.Entites.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository  extends /*UserRepository*/ JpaRepository<Staff, Long> {

    Optional<Staff> findByUsername(String username);
    Staff findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
