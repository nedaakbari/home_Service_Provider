package ir.maktab.homeServiceProvider.data.model.entity.Person;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "userName cannot be null")
    private String userName;

    @NotNull(message = "passWord cannot be null")
    private String passWord;

    private String firstName;

    private String lastName;


}
