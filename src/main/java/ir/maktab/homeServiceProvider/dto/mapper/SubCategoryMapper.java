package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import org.springframework.stereotype.Component;


@Component
public class SubCategoryMapper {
    public SubCategoryDto entityToDto(SubCategory subCategory) {
        return SubCategoryDto.builder()
                .title(subCategory.getTitle())
                .description(subCategory.getDescription())
                .category(subCategory.getCategory())
                .basePrice(subCategory.getBaseAmount())
                .build();
    }

    public SubCategory dtoToEntity(SubCategoryDto subCategoryDto) {
        return SubCategory.builder()
                .title(subCategoryDto.getTitle())
                .description(subCategoryDto.getDescription())
                .category(subCategoryDto.getCategory())
                .baseAmount(subCategoryDto.getBasePrice())
                .build();
    }
}
