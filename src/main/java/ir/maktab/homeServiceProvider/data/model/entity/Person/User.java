package ir.maktab.homeServiceProvider.data.model.entity.Person;

import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@SuperBuilder
//@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    //@NotNull(message = "Name cannot be null")
    //@Size(min = 3, max = 10, message = "length should be in between 2 to 10")
    @Column(length = 50)
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false, length = 8)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRegistrationStatus status;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreationTimestamp
    private Date registerDate;

    private long creditCart;



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
