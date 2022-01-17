package ir.maktab.homeServiceProvider.data.model.entity;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
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
    private Long id;

    private Double agreedPrice;//قیمت پذیرفته شده برای این سفارش

    @Column(length = 300)
    private String description;

    @CreationTimestamp
    private Date orderRegistration;//تایمی که سفارش ثبت میشه

    @Temporal(TemporalType.DATE)
    private Date workDay;//تایمی که میخواد انجام بشه

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToOne
    private SubCategory subService;

    @ManyToOne
    private Category mainService;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders",fetch = FetchType.LAZY)
    private Set<Offer> offers = new HashSet<>();

    @ManyToOne
    private Expert expert;

    private Double score;

    private String comment;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", workDay=" + workDay +
                ", address=" + address +
                ", subService=" + subService.getTitle() +
                ", state=" + state +
                ", customer=" + customer +
                ", expert=" + expert +
                ", score=" + score +
                // ", agreedPrice= "+agreedPrice+
                ", comment='" + comment + '\'' +
                '}';
    }



}