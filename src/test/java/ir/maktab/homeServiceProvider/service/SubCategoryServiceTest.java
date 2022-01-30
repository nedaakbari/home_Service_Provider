package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.service.CategoryServiceImpl;
import ir.maktab.homeServiceProvider.service.SubCategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubCategoryServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    SubCategoryServiceImpl subServiceService = context.getBean(SubCategoryServiceImpl.class);
    CategoryServiceImpl mainServiceService = context.getBean(CategoryServiceImpl.class);

    SubCategory subCategory;

    @BeforeEach
    public void init() {
        subCategory = new SubCategory();
        subCategory.setBasePrice(140000.0);
    }

    @Test
    void giveNoMainService_SaveMethod_ThrowException() {
        subCategory.setTitle("Electronics Repairs");
        subCategory.setDescription("repair perfectly ");
        RuntimeException result = assertThrows(RuntimeException.class, () ->
                subServiceService.save(subCategory));
        Assertions.assertEquals("❌❌❌ field of mainService can't be empty ❌❌❌", result.getMessage());
    }

    @Test
    void giveSameName_SaveMethod_ThrowException() {
        Category home_appliances = mainServiceService.findByTitle("HOME_APPLIANCES");
        subCategory.setTitle("laundryAndCarWash");
        subCategory.setCategory(home_appliances);

        RuntimeException result = assertThrows(RuntimeException.class, () ->
                subServiceService.save(subCategory));
        Assertions.assertEquals("❌❌❌ this subService is already exist ❌❌❌", result.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"Kitchen appliances"})
    void givenValidName_WhenFindSubServiceByNameCalls_ThenReturnTrueResponse(String name) {
        SubCategory result = subServiceService.findByTitle(name);
        assertNotNull(result);
    }

    @ParameterizedTest
    @CsvSource({"a", "c", "d"})
    void givenInvalidName_WhenFindSubServiceByNameCalls_ThenReturnTrueResponse(String name) {
        Exception exception = assertThrows(RuntimeException.class, () -> subServiceService.findByTitle(name));
        assertEquals("we haven't this subCategory!", exception.getMessage());
    }
}
