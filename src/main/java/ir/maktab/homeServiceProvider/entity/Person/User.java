package ir.maktab.homeServiceProvider.entity.Person;

import ir.maktab.homeServiceProvider.enums.Gender;
import ir.maktab.homeServiceProvider.enums.Role;
import ir.maktab.homeServiceProvider.enums.UserRegistrationStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@SuperBuilder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 8)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRegistrationStatus status;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreationTimestamp
    private Date registerDate;

    private Double creditCart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, username, password);
    }
}
