package s19239.mas.teddemo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;
@Entity(name = "session")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Session {
    public Session(@NotNull @FutureOrPresent LocalDateTime startingDateTime,@Future @NotNull LocalDateTime endingDateTime) {
        this.startingDateTime = startingDateTime;
        this.endingDateTime = endingDateTime;
    }

    @Id
    @SequenceGenerator(
            name = "session_sequence",
            sequenceName = "session_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "session_sequence"
    )
    private Long id;

    @NotNull
    @FutureOrPresent
    private LocalDateTime startingDateTime;

    @Future
    @NotNull
    private LocalDateTime endingDateTime;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(optional = false)
    @JoinColumn(name = "conference_id", nullable = false, updatable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Conference conference;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(optional = false)
    @JoinColumn(name="conferencehall_id", nullable = true, updatable = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ConferenceHall hall;

    @OneToMany(mappedBy = "session", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<@NotNull Ticket> tickets = new HashSet<>();


    public void setConference(Conference c) {

        if (this.conference == c) {
            return;
        }
        if (this.conference != null && c == null) {
            reassignConference();
        } else if (this.conference == null && c != null) {
            assignConference(c);
        } else if (this.conference != null && c != null) {
            reassignConference();
            assignConference(c);
        }

    }

    public void setHall(ConferenceHall h) {

        if (this.hall == h) {
            return;
        }
        if (this.hall != null && h == null) {
            reassignHall();
        } else if (this.hall == null && h != null) {
            assignHall(h);
        } else if (this.hall != null && h != null) {
            reassignHall();
            assignHall(h);
        }

    }

    public void delete() {

        if (this.conference == null) {
            return;
        }
        reassignConference();
        reassignHall();
        for (Ticket t : this.tickets) {
            removeTicket(t);
        }
    }


    public void assignConference(Conference c) {
        this.conference = c;
        c.addSession(this);
    }

    public void reassignConference() {
        Conference conference = this.conference;
        this.conference = null;
        conference.removeSession(this);
    }

    public void assignHall(ConferenceHall h) {
        this.hall = h;
        h.addSession(this);
    }

    public void reassignHall() {
        ConferenceHall hall = this.hall;
        this.hall = null;
        hall.removeSession(this);
    }


    public void addTicket(Ticket t) {
        if (t.getSession() != this) {
            throw new ValidationException("You are trying to add ticket, which does not belong to this session!");
        }
        this.tickets.add(t);
    }

    public void removeTicket(Ticket t) {
        if (this.tickets.contains(t)) {
            this.tickets.remove(t);
            t.delete();
        }

    }
}
