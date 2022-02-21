package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.enums.OrderState;
import ir.maktab.homeServiceProvider.data.enums.OrderState;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class OrderFilterDto extends BaseDto {

    private OrderState state;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String subTitle;

}
