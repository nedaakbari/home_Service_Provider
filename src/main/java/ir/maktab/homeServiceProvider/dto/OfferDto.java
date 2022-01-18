package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class OfferDto {

    private OfferStatus status;
    private Double proposedPrice;
    private String description;
    private Double duringTime;
    private Date startWorkTime;
    private ExpertDto expert;

    @Override
    public String toString() {
        return "OfferDto{" +
                "status=" + status +
                ", proposedPrice=" + proposedPrice +
                ", description='" + description + '\'' +
                ", duringTime=" + duringTime +
                ", startWorkTime=" + startWorkTime +
                ", expert=" + expert.getFirstName() + expert.getLastName()+expert.getScore()+
                '}';
    }
}
