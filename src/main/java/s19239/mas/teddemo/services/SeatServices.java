package s19239.mas.teddemo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s19239.mas.teddemo.model.ConferenceHall;
import s19239.mas.teddemo.model.Seat;
import s19239.mas.teddemo.model.Session;
import s19239.mas.teddemo.repository.SeatRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServices {
    private final SeatRepository seatRepository;


    public List<Seat> getAvailableSeatBySession(Session sess) {
        return seatRepository.findAvailableSeatsBySession(sess,sess.getHall());

    }

}
