package ir.maktab.homeServiceProvider.data.entity.Person;

import ir.maktab.homeServiceProvider.data.enums.Role;
import ir.maktab.homeServiceProvider.data.enums.Role;
import lombok.*;

import javax.persistence.*;

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

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
