package s19239.mas.teddemo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import s19239.mas.teddemo.model.ConferenceHall;
import s19239.mas.teddemo.model.Seat;
import s19239.mas.teddemo.model.Session;

import java.util.List;

public interface SeatRepository extends CrudRepository<Seat,ConferenceHall> {

    @Query("from seat as s where not exists (select t from ticket as t where s=t.seat and t.session=:session ) and s.hall=:conferencehall")
    List<Seat> findAvailableSeatsBySession(@Param("session") Session session, @Param("conferencehall") ConferenceHall conferencehall);
    List<Seat> findSeatsByHall(ConferenceHall hall);
    List<Seat>findAll();

}
