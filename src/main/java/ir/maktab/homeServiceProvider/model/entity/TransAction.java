package ir.maktab.homeServiceProvider.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @CreationTimestamp
    private Date transferDate;

    @OneToOne
    private Orders orders;
}
