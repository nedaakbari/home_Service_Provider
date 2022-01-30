package ir.maktab.homeServiceProvider.dto;

import ir.maktab.homeServiceProvider.entity.service.Category;
import ir.maktab.homeServiceProvider.entity.service.SubCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubCategoryToDtoTest {
    private ModelMapper mapper;

    @BeforeEach
    public void setup() {
        this.mapper = new ModelMapper();
    }

    @Test
    public void whenMapGameWithExactMatch_thenConvertsToDTO() {
        Category HOME_CLEANING_AND_HYGIENE = new Category();
        HOME_CLEANING_AND_HYGIENE.setTitle("HOME_CLEANING_AND_HYGIENE");

        SubCategory homeSpraying = SubCategory.builder()
                .category(HOME_CLEANING_AND_HYGIENE).title("homeSpraying")
                .description("Clean the house from any insects").basePrice(110000.0)
                .build();

        SubCategoryDto dto = this.mapper.map(homeSpraying, SubCategoryDto.class);

        assertEquals(homeSpraying.getBasePrice(), dto.getBasePrice());
        assertEquals(homeSpraying.getTitle(), dto.getTitle());
        assertEquals(homeSpraying.getDescription(), dto.getDescription());
        assertEquals(homeSpraying.getCategory(), dto.getCategory());
    }


    @Test
    public void givenSubCategoryDtoToSubCategory_whenMaps_thenCorrect() {
        Category HOME_CLEANING_AND_HYGIENE = new Category();
        HOME_CLEANING_AND_HYGIENE.setTitle("HOME_CLEANING_AND_HYGIENE");

        SubCategoryDto dto = SubCategoryDto.builder()
                .category(mapper.map(HOME_CLEANING_AND_HYGIENE,CategoryDto.class)).title("homeSpraying")
                .description("Clean the house from any insects").basePrice(110000.0)
                .build();

        SubCategory subCategory = this.mapper.map(dto, SubCategory.class);

        assertEquals(dto.getBasePrice(), subCategory.getBasePrice());
        assertEquals(dto.getTitle(), subCategory.getTitle());
        assertEquals(dto.getDescription(), subCategory.getDescription());
        assertEquals(dto.getCategory(), subCategory.getCategory());

    }
}

