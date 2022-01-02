package ir.maktab.homeServiceProvider.model.entity;

import ir.maktab.homeServiceProvider.model.enumeration.Role;
import ir.maktab.homeServiceProvider.model.enumeration.UserRegistrationStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = "user"))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
    @Column(nullable = false, length = 8 )
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRegistrationStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;
    @CreationTimestamp
    private Date registerDate;

    private long creditCart;

/*    @Builder
    public User(String name, String lastName, String email, String password, String username, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }*/

    @Override
    public String toString() {
        return ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", registerDate=" + registerDate +
                ", creditCart=" + creditCart ;
    }
}
