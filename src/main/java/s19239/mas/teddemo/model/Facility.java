package s19239.mas.teddemo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "facility")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Facility {
    public Facility(@NotBlank(message = "Name is mandatory") @Size(min = 3, max = 255) String name, @NotBlank(message = "Address is mandatory") @Size(min = 3, max = 500) String address) {
        this.name = name;
        this.address = address;
    }
    @Id
    @SequenceGenerator(
            name = "facility_sequence",
            sequenceName = "facility_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "facility_sequence"
    )
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank(message = "Address is mandatory")
    @Size(min = 3, max = 500)
    private String address;

    @Size(min=1)
    @OneToMany(orphanRemoval = true, mappedBy = "facility", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<@NonNull ConferenceHall> halls = new HashSet<>();

    public void addHall(ConferenceHall h) {
        if (h.getFacility() != this) {
            throw new ValidationException("This hall belongs to another facility");
        }
        this.halls.add(h);

    }

    public void removeHall(ConferenceHall h) {
        if (this.halls.contains(h)) {
            this.halls.remove(h);
            h.delete();
        }

    }

    public void delete() {
        for (ConferenceHall h : this.halls) {
            removeHall(h);
        }
    }
}
