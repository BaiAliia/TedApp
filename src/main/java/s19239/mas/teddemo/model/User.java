package s19239.mas.teddemo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.executable.ValidateOnExecution;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User extends Person implements Member,NotMember,VIPMEMBER{


    public User(@NotBlank(message = "Name is mandatory") @Size(min = 3, max = 255) String name, @NotBlank(message = "Telephone number can not be empty!") @Size(min = 6, max = 20) String telephoneNumber, @NotBlank(message = "Email can not be empty!") @Email String email, @NotBlank(message = "Username is mandatory") @Size(min = 5, max = 255) String username, @NotBlank(message = "Password is mandatory") @Size(min = 8, max = 255) String password, @NotNull Benefit benefit, @NotNull Type userType, Integer memberPoints) {
        super(name, telephoneNumber, email);
        this.username = username;
        this.password = password;
        this.benefit = benefit;
        setType(userType);

        if (isMember()) {
            setmemberpoints(memberPoints);
        }

    }

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @NotBlank(message = "Username is mandatory")
    @Size(min = 5, max = 255)
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 255)
    private String password;
    @Embedded
    @NotNull
    private Benefit benefit;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Type type;
    private Integer memberPoints;
    private Integer percentage=0;
    private Boolean isDeleted = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<@NotNull Ticket> tickets = new HashSet<>();

    @SuppressWarnings("JpaDataSourceORMInspection")
    @ManyToOne(optional = true)
    @JoinColumn(name = "customer_support_id", nullable = true, updatable = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CustomerSupport contacts;

    @Override
    public Integer getmemberpoints() {
        return this.memberPoints;
    }

    @Override
    public void setmemberpoints(int points) {
        if (!isMember()) {
            throw new ValidationException("The user doesnt have mambership can't assign points");
        }
        this.memberPoints = points;


    }

    public Boolean isMember() {
        boolean res=(this.type==Type.MEMBER||this.type==Type.VIPMEMBER);
        return res;
    }

    @Override
    public Integer getpercentage() {
        return this.percentage;
    }

    @Override
        public void setpercentage(int percentage) {
    if(this.type!=Type.VIPMEMBER){
        throw new ValidationException("This user is not VIP member cant sett percentage");
    }
    this.percentage=percentage;
        }

    public void becomeMember(int points) {
        if (isMember()) {
            throw new ValidationException("This user is already a member");
        }
        setType(Type.MEMBER);
        setmemberpoints(points);

    }

    public void becomeNonMember() {
        if (!isMember()) {
            throw new ValidationException("This user is not a member");
        }
        setType(Type.NOTMEMBER);
        this.memberPoints = null;
    }
    public void becomeVipMember(){
            if(this.type==Type.VIPMEMBER){
                throw new ValidationException("This user is already a VIP member");
            }
            if(this.tickets.size()<20){
                throw new ValidationException("This user doesnt have 20 ticket purchases in history cant become vip member");
            }
            setType(Type.VIPMEMBER) ;
            setpercentage(5);
    }
    public void setContacts(CustomerSupport c) {

        if (this.contacts == c) {
            return;
        }
        if (this.contacts != null && c == null) {
            reassignContacts();
        } else if (this.contacts == null && c != null) {
            assignContacts(c);
        } else if (this.contacts != null && c != null) {
            reassignContacts();
            assignContacts(c);
        }

    }

    public void assignContacts(CustomerSupport c) {
        this.contacts = c;
        c.addUser(this);
    }

    public void reassignContacts() {
        CustomerSupport cs = this.contacts;
        this.contacts = null;
        cs.removeUser(this);
    }

    public void addTicket(Ticket t) {
        if (t.getUser() != this) {
            throw new ValidationException("You are trying to add ticket, which does not belong to this user!");
        }
        this.tickets.add(t);
    }

    public void removeTicket(Ticket t) {
        if (this.tickets.contains(t)) {
            this.tickets.remove(t);
            t.delete();
        }

    }

    public void delete() {
        if (isDeleted) {
            return;
        }
        this.isDeleted = true;
        reassignContacts();
        for (Ticket t : this.tickets) {
            removeTicket(t);
        }
    }

}
