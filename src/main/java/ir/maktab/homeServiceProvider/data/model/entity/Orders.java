package ir.maktab.homeServiceProvider.data.model.entity;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double proposedPrice;
    //private long agreedPrice;
    private String description;
    @CreationTimestamp
    private Date orderDate;
    @Temporal(TemporalType.DATE)
    private Date workDay;
    @Embedded
    private Address address;
    @ManyToOne
    private SubService subService;
    /*@ManyToOne
    private MainService mainService;*/
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @ManyToOne
    @Column(nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "orders"/*,fetch = FetchType.EAGER*/)
    private Set<Offer> offers = new HashSet<>();
    @ManyToOne
    private Expert expert;
    private Double score;//choose wrapper because I don't want save 0, want null mean that not yet give comment
    private String comment;

    @Builder
    public Orders(Double proposedPrice, String description, Date workDay, Address address, SubService subService, Customer customer) {
        this.proposedPrice = proposedPrice;
        this.description = description;
        this.workDay = workDay;
        this.address = address;
        this.subService = subService;
        this.customer = customer;
    }

    public Orders() {

    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", proposedPrice=" + proposedPrice +
                ", description='" + description + '\'' +
                ", orderDate=" + orderDate +
                ", workDay=" + workDay +
                ", address=" + address +
                ", subService=" + subService.getName() +
                ", state=" + state +
                ", customer=" + customer +
                ", expert=" + expert +
                ", score=" + score +
                // ", agreedPrice= "+agreedPrice+
                ", comment='" + comment + '\'' +
                '}';
    }


    public String prints() {
        return "id=" + this.id +
                ", proposedPrice=" + proposedPrice +
                ", description='" + description + '\'' +
                ", orderDate=" + orderDate +
                ", workDay=" + workDay +
                '}';
    }


}