package Application.Repositories;

import Application.Entites.Order;
import Application.Entites.Users.Customer;
import Application.Entites.Users.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByCustomer(Customer customer);
    List<Order> findAllByCustomer(Customer customer);
    Order findByNameAndCustomer(String name, Customer customer);
}
