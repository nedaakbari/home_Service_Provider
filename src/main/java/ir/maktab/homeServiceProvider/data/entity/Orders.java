package ir.maktab.homeServiceProvider.data.entity;

import ir.maktab.homeServiceProvider.data.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.enums.OrderState;
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

    @CreationTimestamp
    private Date orderRegistrationDate;//تایمی که سفارش ثبت میشه

    @Column(length = 300)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date doWorkDate;//تاریخانجامکار که کاستومر میخواد انجام بشه


    @ManyToOne//(cascade = CascadeType.PERSIST)
    private Address address;

    @ManyToOne
    private SubCategory subCategory;

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

    private String codeNumber ;

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