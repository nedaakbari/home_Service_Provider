package ir.maktab.homeServiceProvider.model.entity;

import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.model.entity.Person.Expert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    private int id;
    @Column(nullable = false)
    private Double rate;
    @Column(length = 150)
    private String comment;
    /*@ManyToOne
    @Column(nullable = false)
    private Customer customer;
    @ManyToOne
    @Column(nullable = false)
    private Expert expert;*/
}
