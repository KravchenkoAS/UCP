package Application.Repositories;

import Application.Entites.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<Point, Long> {
    Point findByCityAndCountry(String city, String country);
}
