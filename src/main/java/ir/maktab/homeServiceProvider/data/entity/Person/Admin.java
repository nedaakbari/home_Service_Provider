package ir.maktab.homeServiceProvider.data.entity.Person;

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

    private String userName;

    @Column(length = 8)
    private String passWord;

    private String firstName;

    private String lastName;

    private String email;

}
