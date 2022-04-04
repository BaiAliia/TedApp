package s19239.mas.teddemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Person {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank(message = "Telephone number is mandatory")
    @Size(min = 6, max = 20)
    private String phoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
}
