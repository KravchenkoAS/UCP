package Application.Repositories;

import Application.Entites.Dictionary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends CrudRepository<Dictionary, Long> {



}
