package s19239.mas.teddemo.repository;

import org.springframework.data.repository.CrudRepository;
import s19239.mas.teddemo.model.ConferenceHall;
import s19239.mas.teddemo.model.Facility;
import s19239.mas.teddemo.model.Session;

import java.util.List;

public interface ConferenceHallRepository extends CrudRepository<ConferenceHall, Facility> {
    public List<ConferenceHall>findAll();
    ConferenceHall findConferenceHallsBySessionsContaining(Session sess);
}
