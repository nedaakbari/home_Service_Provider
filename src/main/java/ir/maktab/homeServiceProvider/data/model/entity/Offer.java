package ir.maktab.homeServiceProvider.data.model.entity;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    private Double proposedPrice;//قیمت پیشنهادی

    private String description;

    private Double duringTime;//مدت زمان انجام کار

    @Temporal(TemporalType.TIME)
    private Date startWorkTime;//ساعت شروع کار

    @CreationTimestamp
    private Date submissionDate;//زمان ثبت پیشنهاد

    @ManyToOne
    @JoinColumn(nullable = false)
    private Expert expert;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Orders orders;

    private long codeNumber = makeRandomNumber();//کد رهگیری

    public long makeRandomNumber() {
        long leftLimit = 100000000000000L;
        long rightLimit = 1000000000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", proposedPriceOffer=" + proposedPrice +
                ", description='" + description + '\'' +
                ", duringTime=" + duringTime +
                ", startWorkTime=" + startWorkTime +
                ", submissionDate=" + submissionDate +
                ", expert=" + expert +
                '}';
    }
}
