package s19239.mas.teddemo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "conference")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Conference {

    public Conference(@NotBlank(message = "Title is mandatory") @Size(min = 3, max = 255) String title,  @NotBlank(message = "Description is mandatory") @Size(min = 3, max = 500) String description,@Positive int duration, @NotBlank(message = "Language  is mandatory") @Size(min = 3, max = 30) String language, @NotNull @Size(min = 1) List<@NotBlank @Size(min = 3, max = 100) String> topics , @NotNull @Size(min = 1) List<@NotBlank @Size(min = 3, max = 100) String> speakers,String company){
        this.title = title;
        this.description = description;
        this.duration=duration;
        this.language=language;
        this.topics = topics;
        this.speakers = speakers;
        this.company = company;
    }
    public Conference(@NotBlank(message = "Title is mandatory") @Size(min = 3, max = 255) String title,  @NotBlank(message = "Description is mandatory") @Size(min = 3, max = 500) String description,@Positive int duration, @NotBlank(message = "Language  is mandatory") @Size(min = 3, max = 30) String language, @NotNull @Size(min = 1) List<@NotBlank @Size(min = 3, max = 100) String> topics , @NotNull @Size(min = 1) List<@NotBlank @Size(min = 3, max = 100) String> speakers){
        this.title = title;
        this.description = description;
        this.duration=duration;
        this.language=language;
        this.topics = topics;
        this.speakers = speakers;
    }

    @Id
    @SequenceGenerator(
            name = "conference_sequence",
            sequenceName = "conference_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "conference_sequence"
    )
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 3, max = 255)
    private String title;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 3, max = 500)
    private String description;

    @Positive
    private int duration;

    @NotBlank(message = "Language  is mandatory")
    @Size(min = 3, max = 30)
    private String language;

    @ElementCollection
    @NotNull
    @Size(min = 1)
    private List<@NotBlank @Size(min = 3, max = 100) String> topics = new ArrayList<>();

    @ElementCollection
    @NotNull
    @Size(min = 1)
    private List<@NotBlank @Size(min = 3, max = 100) String> speakers = new ArrayList<>();


    @OneToMany(mappedBy = "conference", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<@NonNull Session> sessions = new HashSet<>();


    private String company;

    public void addTopic(String top) { this.topics.add(top); }

    public void removeTopic(String top) { this.topics.remove(top); }

    public void addSpeaker(String speaker) { this.speakers.add(speaker); }

    public void removeSpeaker(String speaker) { this.speakers.remove(speaker); }

    public void addSession(Session s) {
        if (s.getConference() != this) {
            throw new ValidationException("This session belongs to another conference");
        }
        this.sessions.add(s);
    }

    public void removeSession(Session s) {
        if (this.sessions.contains(s)) {
            this.sessions.remove(s);
            s.reassignConference();
        }

    }
}
