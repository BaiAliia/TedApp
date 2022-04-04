package s19239.mas.teddemo.repository;

import org.springframework.data.repository.CrudRepository;
import s19239.mas.teddemo.model.Facility;

public interface FacilityRepository extends CrudRepository<Facility,Long> {
}
