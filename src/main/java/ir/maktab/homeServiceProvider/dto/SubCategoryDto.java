package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubCategoryDto {
    private String name;
    private Double basePrice;
    private String description;
    private Category category;
}
