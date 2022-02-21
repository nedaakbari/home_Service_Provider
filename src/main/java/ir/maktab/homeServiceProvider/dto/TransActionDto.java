package ir.maktab.homeServiceProvider.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransActionDto {

    private CustomerDto customer;

    private long expertAccNumber;

    private long accNumber;

    @Size(max = 4)
    private int cvv2;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;

    private OrdersDto orders;

    private double amount;

}
