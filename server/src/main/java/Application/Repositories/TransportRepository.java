package Application.Repositories;

import Application.Entites.Transport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportRepository extends CrudRepository<Transport, Long> {

    Optional<Transport> findByName(String name);
}
