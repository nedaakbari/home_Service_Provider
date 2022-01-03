package ir.maktab.homeServiceProvider.model.entity;

import ir.maktab.homeServiceProvider.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.model.enumration.OrderState;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long proposedPrice;
    private String description;
    @CreationTimestamp
    private Date orderDate;
    @Embedded
    private Address address;

    @ManyToOne
    private SubService subService;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "orders")
    private List<Offer> offers = new ArrayList<>();

    @ManyToOne
    private Expert expert;

    private Integer score;//choose wrapper because I don't want save 0, want null mean that not yet give comment

    private String comment;

    /*@OneToOne
    private TransAction transAction;*/
}