package Application.Repositories;

import Application.Entites.Fuel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelRepository extends CrudRepository<Fuel, Long> {

    Fuel findByName(String name);
}
