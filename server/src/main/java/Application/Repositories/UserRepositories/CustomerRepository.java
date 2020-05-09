package Application.Repositories.UserRepositories;

import Application.Entites.Users.Customer;
import Application.Entites.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository  extends /*UserRepository*/ JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);
    Customer findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
