package ir.maktab.homeServiceProvider.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubCategoryDto {
    private String title;
    private Double basePrice;
    private String description;
    private CategoryDto category;
}
