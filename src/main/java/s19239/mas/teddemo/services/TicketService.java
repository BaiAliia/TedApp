package s19239.mas.teddemo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s19239.mas.teddemo.model.Seat;
import s19239.mas.teddemo.model.Session;
import s19239.mas.teddemo.model.Ticket;
import s19239.mas.teddemo.model.User;
import s19239.mas.teddemo.repository.TicketRepository;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;


    public Ticket CreateTicket(User user, Session session, Seat seat) {
        Ticket t = new Ticket(session, user, seat);
        ticketRepository.save(t);
        return t;
    }
}
