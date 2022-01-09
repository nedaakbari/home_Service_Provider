package ir.maktab.homeServiceProvider.data.model.entity;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubService;
import ir.maktab.homeServiceProvider.data.model.enumeration.OrderState;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double proposedPrice;
    private long agreedPrice;
    private String description;
    @CreationTimestamp
    private Date orderDate;
    @Temporal(TemporalType.DATE)
    private Date workDay;
    @OneToOne
    private Address address;
    @ManyToOne
    private SubService subService;
    /*@ManyToOne
    private MainService mainService;*/
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;
    @OneToMany(mappedBy = "orders"/*,fetch = FetchType.EAGER*/)
    private Set<Offer> offers = new HashSet<>();
    @ManyToOne
    private Expert expert;
    private Double score;//choose wrapper because I don't want save 0, want null mean that not yet give comment
    private String comment;



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



}