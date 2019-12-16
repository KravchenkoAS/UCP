package Application.Repositories;

import Application.Entites.Transporter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporterRepository extends CrudRepository<Transporter, Long> {

}
