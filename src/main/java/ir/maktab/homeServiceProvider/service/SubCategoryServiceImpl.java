package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.SubCategoryDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl /*implements SubCategoryService*/ {
    private ModelMapper mapper = new ModelMapper();
    private final SubCategoryDao subCategoryDao;

    public void save(SubCategory subCategory) {
        Optional<SubCategory> foundSubService = subCategoryDao.findByTitle(subCategory.getTitle());
        if (foundSubService.isPresent()) {
            throw new DuplicateData("❌❌❌ this subService is already exist ❌❌❌");
        } else {
            subCategoryDao.save(subCategory);
        }
    }

    public void delete(SubCategory subCategory) {
        subCategoryDao.delete(subCategory);
    }

    public void update(SubCategory subCategory) {
        subCategoryDao.save(subCategory);
    }

    public List<SubCategoryDto> getAll() {
        return subCategoryDao.findAll().stream()
                .map(subCategory -> mapper.map(subCategory, SubCategoryDto.class))
                .collect(Collectors.toList());
    }

    public SubCategory getById(Integer theId) {
        Optional<SubCategory> foundService = subCategoryDao.findById(theId);
        if (foundService.isPresent()) {
            return foundService.get();
        } else {
            throw new NotFoundDta("❌❌❌ this subService is not exist ❌❌❌");
        }
    }

    public SubCategory findByTitle(String title) {
        Optional<SubCategory> found = subCategoryDao.findByTitle(title);
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new NotFoundDta("❌❌❌ this subCategory is not exist ❌❌❌");
        }
    }

    public List<SubCategory> findSubservienceOfACategory(int categoryId) {//پیدا کردن همه زیرخدمت های یک خدمت
        return subCategoryDao.findSubCategoryByCategoryId(categoryId);
    }

    public Set<SubCategory> findSubServiceOfExpert(Expert expert) {//پیدا کردن زیر خدمت های یک اکسپرت
        return expert.getSubCategoryList(); //=>eager
       // return subCategoryDao.findSubCategoryOfExpert(expert.getId());////=>without eager
    }



    /*public Set<Expert> findSubCategoryOfExpert(int categoryId) {
        return subCategoryDao.findSubCategoryOfExpert(categoryId);
    }*/

/*    public void addExpertToSubCategory(Expert expert, SubCategory subCategory) {
        Set<Expert> experts = subCategoryDao.findExpertsOfASubCategory(subCategory.getId());
        experts.add(expert);
        subCategory.setExperts(experts);
        subCategoryDao.save(subCategory);
    }

    public void removeExpertFromCategory(Expert expert, SubCategory subCategory) {
        Set<Expert> experts = subCategoryDao.findExpertsOfASubCategory(subCategory.getId());
        experts.remove(expert);
        subCategory.setExperts(experts);
        subCategoryDao.save(subCategory);
    }

    public List<ExpertDto> findSubCategoryExpertsBySubCategoryTitle(String title) {
        SubCategory subCategory = findByTitle(title);
        Set<Expert> experts = subCategory.getExperts();
        return experts.stream()
                .map(item -> mapper.map(item, ExpertDto.class)).collect(Collectors.toList());
    }*/

}
