package s19239.mas.teddemo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s19239.mas.teddemo.model.Conference;
import s19239.mas.teddemo.model.Session;
import s19239.mas.teddemo.repository.ConferenceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConferenceService {
    private final ConferenceRepository conferenceRepository;



    public List<Conference> getAllConferences() {
        Iterable<Conference> all = conferenceRepository.findAll();
        List<Conference> conferences = new ArrayList<>();
        all.forEach(conferences::add);
        return conferences;
    }
}
