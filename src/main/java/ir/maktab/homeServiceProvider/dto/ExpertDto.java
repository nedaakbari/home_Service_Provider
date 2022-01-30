package ir.maktab.homeServiceProvider.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class ExpertDto extends UserDto {

    private Double score;
    private Set<SubCategoryDto> subCategoryList = new HashSet<>();
}
