package s19239.mas.teddemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Benefit {
     //student,pensioner
    @NotBlank(message = "Benefit name is mandatory")
    @Size(min = 5, max = 255)
    private String benefitName;

    @Min(0)
    private int discount;
}
