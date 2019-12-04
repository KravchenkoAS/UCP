package Application.Repositories;

import Application.Entites.Type_delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Type_deliveryRepository extends CrudRepository<Type_delivery, Long> {

    Type_delivery findByName(String name);
}
