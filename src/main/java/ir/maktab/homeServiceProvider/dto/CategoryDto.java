package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {
    private int id;
    private String name;
    private String description;
}
