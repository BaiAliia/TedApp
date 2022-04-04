package s19239.mas.teddemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import s19239.mas.teddemo.model.Conference;
import s19239.mas.teddemo.model.Session;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session,Conference> {

   // @Query("from session as e where e.conference.id=:conference")
   // List<Session> findSessionsByConference(@Param("conference") Long conference);

    List<Session>findAll();

    List<Session>findByConference(Conference con);

    List<Session>findSessionById(Long id);


}
