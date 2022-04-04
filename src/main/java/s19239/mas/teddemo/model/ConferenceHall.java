package s19239.mas.teddemo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "conferencehall")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConferenceHall {
    public ConferenceHall(@NotBlank(message = "Name is mandatory") @Size(min = 3, max = 255) String name, @Min(1) int capacity, Facility facility) {
        this.name = name;
        this.capacity=capacity;
        setFacility(facility);
    }

    @Id
    @SequenceGenerator(
            name = "conferencehall_sequence",
            sequenceName = "conferencehall_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "conferencehall_sequence"
    )
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 255)
    private String name;

    @Min(1)
    private int capacity;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(optional = false)
    @JoinColumn(name = "facility_id",nullable = false,updatable =false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Facility facility;

    private Boolean isDeleted = false;

    @Size(min=1)
    @OneToMany(orphanRemoval = true, mappedBy = "hall", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<@NonNull Seat> seats = new HashSet<>();

    @OneToMany(mappedBy = "hall", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<@NonNull Session> sessions = new HashSet<>();

    public void addSeat(Seat s) {
        if (s.getHall() != this) {
            throw new ValidationException("This seat belongs to another hall");
        }
        this.seats.add(s);
    }

    public void removeSeat(Seat s) {
        if (this.seats.contains(s)) {
            this.seats.remove(s);
            s.delete();
        }

    }

    private void setFacility(Facility facility) {
        this.facility = facility;
        facility.addHall(this);
    }

    public void delete() {
        if (this.isDeleted == true) {
            return;
        }
        this.isDeleted = true;
        this.facility.removeHall(this);
        for (Seat s : this.seats) {
            removeSeat(s);
        }
        for (Session s : this.sessions) {
            removeSession(s);
        }
    }


    public void addSession(Session s) {
        if (s.getHall() != this) {
            throw new ValidationException("You are trying to add session, which does not belong to this hall!");
        }
        this.sessions.add(s);
    }

    public void removeSession(Session s) {
        if (this.sessions.contains(s)) {
            this.sessions.remove(s);
            s.reassignHall();
        }

    }

}
