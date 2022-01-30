package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.dto.CategoryDto;
import ir.maktab.homeServiceProvider.entity.service.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryToDtoTest {
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() {
        Category category = new Category();
        category.setTitle("BUILDING_FACILITIES");

        CategoryDto dto = this.mapper.map(category, CategoryDto.class);

        assertEquals(category.getTitle(), dto.getTitle());

    }

    @Test
    public void givenCategoryDtoToCategory_whenMaps_thenCorrect() {
        CategoryDto dto = new CategoryDto();
        dto.setTitle("BUILDING_FACILITIES");

        Category category = this.mapper.map(dto, Category.class);

        assertEquals(dto.getTitle(), category.getTitle());

    }
}

