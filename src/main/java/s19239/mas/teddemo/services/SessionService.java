package s19239.mas.teddemo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s19239.mas.teddemo.model.Conference;
import s19239.mas.teddemo.model.Session;
import s19239.mas.teddemo.repository.SessionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public List<Session>findByConference(Conference con){
        return sessionRepository.findByConference(con);
    }

    public List<Session>getAllSessions(){
        Iterable<Session> all = sessionRepository.findAll();
        List<Session> sessions = new ArrayList<>();
        all.forEach(sessions::add);
        return sessions;
    }
}
