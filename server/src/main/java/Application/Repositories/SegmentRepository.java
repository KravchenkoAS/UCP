package Application.Repositories;

import Application.Entites.Segment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends CrudRepository<Segment, Long> {



}
