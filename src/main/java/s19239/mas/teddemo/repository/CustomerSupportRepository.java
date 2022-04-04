package s19239.mas.teddemo.repository;

import org.springframework.data.repository.CrudRepository;
import s19239.mas.teddemo.model.CustomerSupport;

public interface CustomerSupportRepository extends CrudRepository<CustomerSupport,Long> {
}
