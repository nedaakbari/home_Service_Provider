package ir.maktab.homeServiceProvider.data.model.entity;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    private int id;
    @Column(nullable = false)
    private Double rate;
    @Column(length = 150)
    private String comment;
    @ManyToOne
    @Column(nullable = false)
    private Customer customer;
    @ManyToOne
    @Column(nullable = false)
    private Expert expert;
    @OneToOne
    Orders orders;
}
