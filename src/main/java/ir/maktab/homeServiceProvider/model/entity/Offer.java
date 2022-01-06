package ir.maktab.homeServiceProvider.model.entity;

import ir.maktab.homeServiceProvider.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.model.enumeration.OfferStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;//want to give long because of very suggest
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
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

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", proposedPriceOffer=" + proposedPriceOffer +
                ", description='" + description + '\'' +
                ", duringTime=" + duringTime +
                ", startWorkTime=" + startWorkTime +
                ", submissionDate=" + submissionDate +
                ", offerDate=" + offerDate +
                ", expert=" + expert.getName() + " " + expert.getLastName() +
                '}';
    }
}
