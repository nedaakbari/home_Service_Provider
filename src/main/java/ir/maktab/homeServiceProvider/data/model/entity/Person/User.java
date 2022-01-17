package ir.maktab.homeServiceProvider.data.model.entity.Person;

import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@SuperBuilder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @NotNull(message = "firstName cannot be null")
    @Size(min = 3, max = 10, message = "length should be in between 3 to 10")
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    private String lastName;

    @Column(unique = true)
    @NotEmpty(message = "Email field should not be empty")
    @Email(regexp = "^(.+)@(.+)$", message = "Invalid email pattern")
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 8)
    @NotNull(message = "password cannot be null")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRegistrationStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private Date registerDate;

    private Long creditCart;


    @Override
    public String toString() {
        return "user " + id + " firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", registerDate=" + registerDate +
                ", creditCart=" + creditCart;
    }
}
