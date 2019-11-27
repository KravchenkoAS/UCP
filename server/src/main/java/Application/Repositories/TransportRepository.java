package Application.Repositories;

import Application.Entites.Transport;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransportRepository extends CrudRepository<Transport, Long> {

    Optional<Transport> findByName(String name);
}
