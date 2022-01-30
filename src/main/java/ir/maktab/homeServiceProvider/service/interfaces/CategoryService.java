package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.dto.CategoryDto;

import java.util.List;
import java.util.Set;


public interface CategoryService {

    void save(CategoryDto categoryDto);

    void delete(CategoryDto categoryDto);

    List<CategoryDto> getAll();

    CategoryDto findByTitle(String title);

    CategoryDto findById(int theId);

    void updateSubCategory(CategoryDto categoryDto, Set<SubCategory> subCategorySet);

}
