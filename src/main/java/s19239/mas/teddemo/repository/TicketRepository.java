package s19239.mas.teddemo.repository;

import org.springframework.data.repository.CrudRepository;
import s19239.mas.teddemo.model.Ticket;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket,Long> {
    public List<Ticket>findAll();
}
