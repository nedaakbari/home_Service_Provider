package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.SubCategoryDao;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.dto.mapper.SubCategoryMapper;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final SubCategoryMapper mapper;
    private final SubCategoryDao subCategoryDao;

    public void deleteSubService(SubCategory subCategory) {
        subCategoryDao.delete(subCategory);
    }

    public void saveSubCategory(SubCategory subCategory) {
        Optional<SubCategory> foundSubService = subCategoryDao.findByTitle(subCategory.getTitle());
        if (foundSubService.isPresent()) {
            throw new DuplicateData("❌❌❌ this subService is already exist ❌❌❌");
        } else {
            subCategoryDao.save(subCategory);
        }
    }

  /*  @Transactional
    public void updateSubService(SubCategory subCategory) {
        subCategoryDao.update(subCategory);
    }*/

    public List<SubCategory> findAll() {
        return subCategoryDao.findAll();
    }

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

    public SubCategory findById(int id) {
        Optional<SubCategory> foundService = subCategoryDao.findById(id);
        if (foundService.isPresent()) {
            return foundService.get();
        } else {
            throw new NotFoundDta("❌❌❌ this subService is not exist ❌❌❌");
        }
    }

    public List<SubCategory> findSubServiceOfExpert(int expertId) {
        return subCategoryDao.findSubCategoryOfExpert(expertId);
    }

}
