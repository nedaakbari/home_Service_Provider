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

    @CreationTimestamp
    private Date orderRegistrationDate;//تایمی که سفارش ثبت میشه

    @Column(length = 300)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date doWorkDate;//تاریخانجامکار که کاستومر میخواد انجام بشه

    private Double proposePrice;//قیمت پیشنهادی مشتری

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

    private long codeNumber = makeRandomNumber();//کد رهگیری

    public long makeRandomNumber() {
        long leftLimit = 100000000000000L;
        long rightLimit = 1000000000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", doWorkDate=" + doWorkDate +
                ", address=" + address +
                ", subCategory=" + subCategory/*.getTitle()*/ +
                ", state=" + state +
                ", customer=" + customer +
                ", expert=" + expert +
                ", score=" + score +
                 ", agreedPrice= "+agreedPrice+
                ", comment='" + comment + '\'' +
                '}';
    }

}