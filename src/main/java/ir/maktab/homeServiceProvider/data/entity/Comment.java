package ir.maktab.homeServiceProvider.data.entity;

import ir.maktab.homeServiceProvider.data.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private Long id;

    @Column(length = 150)
    private String comment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Expert expert;

    @OneToOne
    private Orders order;

    private String codeNumber = UUID.randomUUID().toString();

}
