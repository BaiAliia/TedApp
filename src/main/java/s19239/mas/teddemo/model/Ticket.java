package s19239.mas.teddemo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
    public Ticket(@NotNull Session session, @NotNull User user, @NotNull Seat seat) {
        setSession(session);
        setUser(user);
        setSeat(seat);
        setPurchaseDate();
        setState(StateTicket.ACTIVE);
    }

    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "ticket_sequence"
    )
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StateTicket state;

    @NotNull
    private LocalDate purchaseDate;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(optional = false)
    @JoinColumn(name = "session_id", nullable = false, updatable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Session session;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(optional = false)
    @JoinColumn(name = "seat_id", nullable = false, updatable = false)
    @NotNull
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Seat seat;

    private Boolean isDeleted = false;


    private void setSeat(Seat s) {
        this.seat = s;
        s.addTicket(this);

    }

    private void setSession(Session s) {
        this.session = s;
        s.addTicket(this);
    }

    private void setUser(User u) {
        this.user = u;
        u.addTicket(this);
    }


    public void delete() {
        if (isDeleted) {
            return;
        }
        this.isDeleted = true;
        this.seat.removeTicket(this);
        this.session.removeTicket(this);
        this.user.removeTicket(this);

    }

    private void setPurchaseDate() {
        LocalDateTime d = this.session.getEndingDateTime();
        LocalDate endingDate = LocalDate.of(d.getYear(), d.getMonth(), d.getDayOfMonth());
        if (LocalDate.now().isAfter(endingDate)) {
            throw new ValidationException("You cant buy ticket to past conferences");
        }
        this.purchaseDate = LocalDate.now();
    }

    public double CalculateFinalPrice() {
        double seatPrice = this.seat.getRegularPrice();
        int discount = this.user.getBenefit().getDiscount();
        int per=this.user.getpercentage();

        double finalPrice = (seatPrice * (discount+per)) / 100;
        return finalPrice;
    }

}
