package s19239.mas.teddemo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "customer_support")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerSupport {
    public CustomerSupport(@NotBlank(message = "Telephone number is mandatory") @Size(min = 6, max = 20) String telephoneNumber, @NotBlank(message = "Email is mandatory") @Email String email) {
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }

    @Id
    @SequenceGenerator(
            name = "customer_support_sequence",
            sequenceName = "customer_support_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "customer_support_sequence"
    )
    private Long id;

    @NotBlank(message = "Telephone number is mandatory")
    @Size(min = 6, max = 20)
    private String telephoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @OneToMany(mappedBy = "customerSupport", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Size(min=1)
    private Set<@NonNull CustomerSupportOperator> employees = new HashSet<>();

    @OneToMany(mappedBy = "contacts", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<@NotNull User> users = new HashSet<>();


    public void addEmployee(CustomerSupportOperator c) {
        if (c.getCustomerSupport() != this) {
            throw new ValidationException("This operator doesn't belong to this customer support");
        }
        this.employees.add(c);
    }

    public void removeEmployee(CustomerSupportOperator c) {
        if (this.employees.contains(c)) {
            this.employees.remove(c);
            c.reassignCustomerSupport();
        }

    }

    public void addUser(User u) {
        if (u.getContacts() != this) {
            throw new ValidationException("This user didnt contact this customer support");
        }
        this.users.add(u);
    }

    public void removeUser(User u) {
        if (this.users.contains(u)) {
            this.users.remove(u);
            u.reassignContacts();
        }
    }

    public void AssignUserRequest() {
        for (CustomerSupportOperator e : this.employees) {
            if (e.getIsAvailable()) {
                e.takeRequest();
            }
        }


    }

}
