package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.data.enums.Gender;
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

    private Gender gender;
    private Double score;
    private Set<SubCategoryDto> subCategoryList = new HashSet<>();
}
