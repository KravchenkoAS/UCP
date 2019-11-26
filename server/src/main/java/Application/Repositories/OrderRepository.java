package Application.Repositories;

import Application.Entites.Order;
import Application.Entites.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByUser(User user);
    List<Order> findAllByUser(User user);
    Order findByNameAndUser(String name, User user);
}
