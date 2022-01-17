package ir.maktab.homeServiceProvider.dto.mapper;


import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto categoryToDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .build();
    }
}
