package model.entity;

import lombok.*;
import model.enumration.Role;
import model.enumration.UserRegistrationStatus;
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
@Inheritance(strategy = InheritanceType.JOINED)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    //@NotNull(message = "Name cannot be null")
    //@Size(min = 3, max = 10, message = "length should be in between 2 to 10")
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(nullable = false, length = 8)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRegistrationStatus status;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreationTimestamp
    private Date registerDate;

    @Builder
    public User(String name, String lastName, String email, String password, String username, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
