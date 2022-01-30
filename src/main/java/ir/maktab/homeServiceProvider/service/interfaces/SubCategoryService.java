package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.entity.service.Category;
import ir.maktab.homeServiceProvider.dto.CategoryDto;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;

import java.util.List;
import java.util.Set;

public interface SubCategoryService {
    void save(SubCategoryDto subCategoryDto, CategoryDto categoryDto);

    void delete(SubCategoryDto subCategoryDto);

    List<SubCategoryDto> getAll();

    SubCategoryDto getById(Integer theId);

    SubCategoryDto findByTitle(String title);

    void update(SubCategoryDto subCategoryDto);

    Set<SubCategoryDto> findSubCategoryOfAnExpert(ExpertDto expertDto);

    List<SubCategoryDto> findAllSubCategoryOfACategory(Category category);

}
