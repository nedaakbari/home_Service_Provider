package ir.maktab.homeServiceProvider.entity;

import ir.maktab.homeServiceProvider.entity.Person.Customer;
import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.entity.service.Category;
import ir.maktab.homeServiceProvider.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.enums.OrderState;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


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

    @CreationTimestamp
    private Date orderRegistrationDate;//تایمی که سفارش ثبت میشه

    @Column(length = 300)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date doWorkDate;//تاریخانجامکار که کاستومر میخواد انجام بشه

    private Double proposedPrice;//قیمت پیشنهادی مشتری

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    private Category category;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private Set<Offer> offers = new HashSet<>();

    @ManyToOne
    private Expert expert;//اکسپرتی که انتخاب میشه هست

    private Double agreedPrice;//قیمت پذیرفته شده برای این سفارش

    private Double score;

    private String comment;

    private String codeNumber = UUID.randomUUID().toString();

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", doWorkDate=" + doWorkDate +
                ", address=" + address +
                ", subCategory=" + subCategory+
                ", state=" + state +
                ", customer=" + customer +
                ", expert=" + expert +
                ", score=" + score +
                ", agreedPrice= " + agreedPrice +
                ", comment='" + comment + '\'' +
                '}';
    }

}