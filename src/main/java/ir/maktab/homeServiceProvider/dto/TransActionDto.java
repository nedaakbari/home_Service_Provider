package ir.maktab.homeServiceProvider.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class TransActionDto {
  //  private CustomerDto customer;
   // private ExpertDto expert;
    private long amount;
    private long trackingNumber;
    private Date transferDate;
   // private OrdersDto orders;
}
