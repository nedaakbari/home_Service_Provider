package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;

public class SubCategoryMapper {
    public SubCategoryDto subServiceDto(SubCategory subCategory) {
        return SubCategoryDto.builder()
                .name(subCategory.getTitle())
                .description(subCategory.getDescription())
                .mainService(subCategory.getMain())
                .basePrice(subCategory.getBaseAmount())
                .build();
    }
}
