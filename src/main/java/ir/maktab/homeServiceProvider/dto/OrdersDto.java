package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.service.validation.OnRegister;
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
public class OrdersDto {

    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "not null", groups = OnRegister.class)
    private Date doWorkDate;//تاریخانجامکار که کاستومر میخواد انجام بشه
    private Double ProposedPrice;//قیمت پیشنهادی مشتری
    private AddressDto address;
    private SubCategoryDto subCategory;
    private CategoryDto category;
    private CustomerDto customer;
    private ExpertDto expert;// به خاطر نال شدنش باید اینو نذاشت
    private String codeNumber;
}
