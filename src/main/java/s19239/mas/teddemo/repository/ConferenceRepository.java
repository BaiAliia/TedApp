package s19239.mas.teddemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import s19239.mas.teddemo.model.Conference;
import s19239.mas.teddemo.model.Session;

import java.util.List;

public interface ConferenceRepository extends CrudRepository<Conference,Long> {
    public List<Conference> findConferenceByTitle(String title);
    public List<Conference> findConferenceByTopicsLike(String topic);
    public List<Conference>findAll();
    public List<Conference>findConferenceBySessionsContaining(Session sess);


}
