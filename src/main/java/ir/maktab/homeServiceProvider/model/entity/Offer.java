package ir.maktab.homeServiceProvider.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
/**
 * author: neda akbari
 */
@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long proposedPriceOffer;
    private String description;
    private int duringTime;

    private LocalTime startWorkTime;

    @CreationTimestamp
    private Date submissionDate;

    @Temporal(TemporalType.DATE)
    private Date offerDate;

    @ManyToOne
    private Expert expert;

    @ManyToOne
    private Orders orders;
}
