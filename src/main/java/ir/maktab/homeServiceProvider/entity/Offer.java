package ir.maktab.homeServiceProvider.entity;

import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

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

    private String codeNumber = UUID.randomUUID().toString();
    ;//کد رهگیری

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
