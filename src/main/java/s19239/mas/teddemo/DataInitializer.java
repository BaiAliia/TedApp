package s19239.mas.teddemo;


import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import s19239.mas.teddemo.model.*;
import s19239.mas.teddemo.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final CustomerSupportRepository customerSupportRepository;
    private final CustomerSupportOperatorRepository customerSupportOperatorRepository;
    private final ConferenceRepository conferenceRepository;
    private final ConferenceHallRepository hallRepository;
    private final SeatRepository seatRepository;
    private final FacilityRepository facilityRepository;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;


    @EventListener
    public void atStart(ContextRefreshedEvent event) {
     //1conference
     /*   List<String> firsttopics= new ArrayList<>();
        firsttopics.add("Education"); firsttopics.add("Psycology");
        List<String> speakers= new ArrayList<>();
        speakers.add("Aicha Evans"); speakers.add("Nicola Sturgeon");
        Conference conference = new Conference("How to control your free time",
                "There are 168 hours in each week .How do we find time for what matters",
                90,
                "English",
                firsttopics,
                speakers );

        List<String> topics2= new ArrayList<>();
        firsttopics.add("Technology"); firsttopics.add("Space");
        List<String> speakers2= new ArrayList<>();
        speakers.add("Elon Musk"); speakers.add("Grimes");
        Conference conference2 = new Conference("The future we're building",
                "spacex CEO Elon Musk has a talk about the future technology and the goals",
                120,
                "English",
                topics2,
                speakers2, "SpaceX");

        List<String> topics3= new ArrayList<>();
        firsttopics.add("Environment"); firsttopics.add("Future");
        List<String> speakers3= new ArrayList<>();
        speakers.add("Nicola Sturgeon");
        Conference conference3 = new Conference("How small countries can make big impact on climate change",
                "The scintist whoo estimated the impact of the 3 world countries have on climate change",
                120,
                "English",
                topics3,
                speakers3);

        List<String> topics4= new ArrayList<>();
        firsttopics.add("Psycology");
        List<String> speakers4= new ArrayList<>();
        speakers.add("daniel Levitin"); speakers.add("John Green");
        Conference conference4 = new Conference("How to stay calm when you know you will be stressed",
                "One of the most famous psycologiest is going to talk about the psycology of stress",
                120,
                "English",
                topics4,
                speakers4);

        List<String> topics5= new ArrayList<>();
        firsttopics.add("Psycology"); firsttopics.add("Business");
        List<String> speakers5= new ArrayList<>();
        speakers.add("Julian Treasure"); speakers.add("Mark Brown");
        Conference conference5 = new Conference("How to speak so that people want to listen",
                "How to get attention during the busness presentation",
                120,
                "Polish",
                topics5,
                speakers5);
        List<String> topics6= new ArrayList<>();
        firsttopics.add("Psycology");
        List<String> speakers6= new ArrayList<>();
        speakers.add("Julian Treasure"); speakers.add("Mark Brown");
        Conference conference6 = new Conference("What makes you special",
                "How to get undestanding of yourself",
                120,
                "Polish",
                topics6,
                speakers6);
        conferenceRepository.save(conference);
        conferenceRepository.save(conference2);
        conferenceRepository.save(conference3);
        conferenceRepository.save(conference4);
        conferenceRepository.save(conference5);
        conferenceRepository.save(conference6);
//Facility

        Facility facility1= new Facility("PJATK","Koszykowa 86,Warszawa");
        Facility facility2= new Facility("Inter Convential Warsaw","Gorchewska 16,Warszawa");
        Facility facility3= new Facility("university Of Warsaw","Jana Pawla 27A,Warszawa");

        ConferenceHall hall1=new ConferenceHall("Zal1A",150,facility1);
        ConferenceHall hall2=new ConferenceHall("Zal2A",100,facility1);
        ConferenceHall hall3=new ConferenceHall("Zal2B",80,facility2);
        ConferenceHall hall4=new ConferenceHall("ZalA",100,facility2);
        ConferenceHall hall5=new ConferenceHall("ZalD2",100,facility3);
        ConferenceHall hall6=new ConferenceHall("Zal5A",120,facility3);

        Seat seat1=new Seat(hall1,12,100, SeatType.FRONT);
        Seat seat2=new Seat(hall1,34,80, SeatType.REGULAR);
        Seat seat3=new Seat(hall1,56,80, SeatType.REGULAR);
        Seat seat4=new Seat(hall1,23,80, SeatType.REGULAR);
        Seat seat5=new Seat(hall1,32,80, SeatType.REGULAR);
        Seat seat6=new Seat(hall1,22,80, SeatType.REGULAR);
        Seat seat7=new Seat(hall1,10,100, SeatType.FRONT);

        Seat seat8=new Seat(hall2,12,100, SeatType.FRONT);
        Seat seat9=new Seat(hall2,34,80, SeatType.REGULAR);
        Seat seat10=new Seat(hall2,56,80, SeatType.REGULAR);
        Seat seat11=new Seat(hall2,23,80, SeatType.REGULAR);
        Seat seat12=new Seat(hall2,32,80, SeatType.REGULAR);
        Seat seat13=new Seat(hall2,22,80, SeatType.REGULAR);
        Seat seat14=new Seat(hall2,10,100, SeatType.FRONT);

        Seat seat15=new Seat(hall3,12,100, SeatType.FRONT);
        Seat seat16=new Seat(hall3,34,80, SeatType.REGULAR);
        Seat seat17=new Seat(hall3,56,80, SeatType.REGULAR);
        Seat seat18=new Seat(hall3,23,80, SeatType.REGULAR);
        Seat seat19=new Seat(hall3,32,80, SeatType.REGULAR);
        Seat seat20=new Seat(hall3,22,80, SeatType.REGULAR);
        Seat seat21=new Seat(hall3,10,100, SeatType.FRONT);

        Seat seat22=new Seat(hall4,12,100, SeatType.FRONT);
        Seat seat23=new Seat(hall4,34,80, SeatType.REGULAR);
        Seat seat24=new Seat(hall4,56,80, SeatType.REGULAR);
        Seat seat25=new Seat(hall4,23,80, SeatType.REGULAR);
        Seat seat26=new Seat(hall4,32,80, SeatType.REGULAR);
        Seat seat27=new Seat(hall4,22,80, SeatType.REGULAR);
        Seat seat28=new Seat(hall4,10,100, SeatType.FRONT);

        Seat seat29=new Seat(hall5,12,100, SeatType.FRONT);
        Seat seat30=new Seat(hall5,34,80, SeatType.REGULAR);
        Seat seat31=new Seat(hall5,56,80, SeatType.REGULAR);
        Seat seat32=new Seat(hall5,23,80, SeatType.REGULAR);
        Seat seat33=new Seat(hall5,32,80, SeatType.REGULAR);
        Seat seat34=new Seat(hall5,22,80, SeatType.REGULAR);
        Seat seat35=new Seat(hall5,10,100, SeatType.FRONT);

        Seat seat36=new Seat(hall6,12,100, SeatType.FRONT);
        Seat seat37=new Seat(hall6,34,80, SeatType.REGULAR);
        Seat seat38=new Seat(hall6,56,80, SeatType.REGULAR);
        Seat seat39=new Seat(hall6,23,80, SeatType.REGULAR);
        Seat seat40=new Seat(hall6,32,80, SeatType.REGULAR);
        Seat seat41=new Seat(hall6,22,80, SeatType.REGULAR);
        Seat seat42=new Seat(hall6,10,100, SeatType.FRONT);


        facilityRepository.save(facility1);
        facilityRepository.save(facility2);
        facilityRepository.save(facility3);

        hallRepository.save(hall1);
        hallRepository.save(hall2);
        hallRepository.save(hall3);
        hallRepository.save(hall4);
        hallRepository.save(hall5);
        hallRepository.save(hall6);

        Session sess1= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess1.setConference(conference); sess1.setHall(hall1);
        Session sess2= new Session(LocalDateTime.of(2022, 3, 3, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess2.setConference(conference); sess2.setHall(hall2);

        Session sess3= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess3.setConference(conference2); sess3.setHall(hall3);
        Session sess4= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess4.setConference(conference2); sess4.setHall(hall2);

        Session sess5= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess5.setConference(conference3); sess5.setHall(hall4);
        Session sess6= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess6.setConference(conference3); sess6.setHall(hall5);

        Session sess7= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess7.setConference(conference4); sess7.setHall(hall6);
        Session sess8= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess8.setConference(conference4); sess8.setHall(hall5);

        Session sess9= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess9.setConference(conference5); sess9.setHall(hall4);
        Session sess10= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess10.setConference(conference6); sess10.setHall(hall3);
        Session sess11= new Session(LocalDateTime.of(2022, 3, 2, 10, 30), LocalDateTime.of(2022, 3, 2, 12, 30));
        sess11.setConference(conference6); sess11.setHall(hall5);

        sessionRepository.save(sess1);
        sessionRepository.save(sess2);
        sessionRepository.save(sess3);
        sessionRepository.save(sess4);
        sessionRepository.save(sess5);
        sessionRepository.save(sess6);
        sessionRepository.save(sess7);
        sessionRepository.save(sess8);
        sessionRepository.save(sess9);
        sessionRepository.save(sess10);
        sessionRepository.save(sess11);

      Benefit be= new Benefit("Student",20);
        Benefit be2= new Benefit("Pensioner",30);
        Benefit be3= new Benefit("Other",10);
        Benefit be4= new Benefit("Regular",0);


        User u = new User("Aliia Baimur","77386572","aliia@gmail.com","aliiabaimur","moonlight911",be,Type.NOTMEMBER,null);
        userRepository.save(u);


*/
}
}


