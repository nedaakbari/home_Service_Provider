package ir.maktab.homeServiceProvider.model.entity;

import ir.maktab.homeServiceProvider.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.model.entity.Person.Expert;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Entity
@Data
public class TransAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Expert expert;

    private long amount;
    private long trackingNumber=makeRandomNumber();

    @CreationTimestamp
    private Date transferDate;

    @OneToOne
    private Orders orders;

    public long makeRandomNumber(){
        long leftLimit = 100000000000000L;
        long rightLimit = 1000000000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
