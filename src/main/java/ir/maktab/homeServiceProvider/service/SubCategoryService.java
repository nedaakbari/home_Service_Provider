package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.SubCategoryDao;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import ir.maktab.homeServiceProvider.dto.mapper.SubCategoryMapper;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService implements Services<SubCategory, SubCategoryDto, Integer> {
    private final SubCategoryMapper mapper;
    private final SubCategoryDao subCategoryDao;

    @Override
    public void save(SubCategory subCategory) {
        Optional<SubCategory> foundSubService = subCategoryDao.findByTitle(subCategory.getTitle());
        if (foundSubService.isPresent()) {
            throw new DuplicateData("❌❌❌ this subService is already exist ❌❌❌");
        } else {
            subCategoryDao.save(subCategory);
        }
    }

    @Override
    public void delete(SubCategory subCategory) {
        subCategoryDao.delete(subCategory);
    }

    @Override
    public List<SubCategoryDto> getAll() {
        return subCategoryDao.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public SubCategory getById(Integer theId) {
        Optional<SubCategory> foundService = subCategoryDao.findById(theId);
        if (foundService.isPresent()) {
            return foundService.get();
        } else {
            throw new NotFoundDta("❌❌❌ this subService is not exist ❌❌❌");
        }
    }


  /*  @Transactional
    public void updateSubService(SubCategory subCategory) {
        subCategoryDao.update(subCategory);
    }*/


    public SubCategory findByTitle(String title) {
        Optional<SubCategory> found = subCategoryDao.findByTitle(title);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new NotFoundDta("❌❌❌ this subCategory is not exist ❌❌❌");
        }
    }

    public List<SubCategory> findSubservienceFromCategory(int categoryId) {
        return subCategoryDao.findSubCategoryByCategoryTitle(categoryId);
    }


    public List<SubCategory> findSubServiceOfExpert(int expertId) {
        return subCategoryDao.findSubCategoryOfExpert(expertId);
    }


}
