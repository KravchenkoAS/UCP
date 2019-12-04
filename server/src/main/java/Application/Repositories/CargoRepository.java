package Application.Repositories;

import Application.Entites.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Long> {
    //Cargo findCargoByOrder(Long id_cargo);
}
