package ir.maktab.homeServiceProvider.data.model.entity.Person;

import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
//@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    //@NotNull(message = "Name cannot be null")
    //@Size(min = 3, max = 10, message = "length should be in between 2 to 10")
    @Column(length = 50)
    private String name;
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

    public User(String name, String lastName, String email, String phoneNumber, String username, String password, UserRegistrationStatus status, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "user " + id + " name='" + name + '\'' +
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
