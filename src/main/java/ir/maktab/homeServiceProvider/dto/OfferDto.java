package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.enums.OfferStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OfferDto {

    private OfferStatus status;
    private Double proposedPrice;
    private String description;
    private Double duringTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "nou null")
    private Date startWorkTime;
    private ExpertDto expert;
    private String codeNumber;

}
