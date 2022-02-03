package ir.maktab.homeServiceProvider.data.entity;

import ir.maktab.homeServiceProvider.data.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.entity.Person.Expert;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class TransActions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Expert expert;

    private long amount;

    private long trackingNumber = makeRandomNumber();

    @CreationTimestamp
    private Date transferDate;

    @OneToOne
    private Orders orders;

    public long makeRandomNumber() {
        long leftLimit = 100000000000000L;
        long rightLimit = 1000000000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
