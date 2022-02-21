package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.entity.service.Category;
import ir.maktab.homeServiceProvider.data.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.repository.CategoryRepository;
import ir.maktab.homeServiceProvider.data.repository.SubCategoryRepository;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import ir.maktab.homeServiceProvider.service.interfaces.SubCategoryService;
import ir.maktab.homeServiceProvider.service.exception.DuplicateData;
import ir.maktab.homeServiceProvider.service.exception.NoSubCategory;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private final ModelMapper mapper;
    private final SubCategoryRepository subCategoryDao;
    private final CategoryRepository categoryDao;

    @Override
    public void save(SubCategoryDto subCategoryDto, String categoryTitle) {
        if (subCategoryDto.getTitle().isBlank())
            throw new NoSubCategory("title can not be blank");
        else {
            SubCategory subCategory = mapper.map(subCategoryDto, SubCategory.class);
            Optional<SubCategory> foundSubService = subCategoryDao.findByTitle(subCategory.getTitle());
            Optional<Category> category = categoryDao.findByTitle(categoryTitle);
            if (category.isPresent()) {
                if (foundSubService.isEmpty()) {
                    subCategory.setCategory(category.get());
                    subCategoryDao.save(subCategory);
                } else throw new DuplicateData("❌❌❌ this subService is already exist ❌❌❌");
            } else
                throw new NotFoundDta("❌❌❌ no category exist for this subtitle ❌❌❌");//todo این نیاز هست؟ چون باید حتما کتگوری باشه که بشه بهش اضافه کرد
        }
    }

    @Override
    public void delete(SubCategoryDto subCategoryDto) {
        SubCategory subCategory = mapper.map(subCategoryDao.findByTitle(subCategoryDto.getTitle()),
                SubCategory.class);
        subCategoryDao.delete(subCategory);
    }

    @Override
    public void update(SubCategoryDto subCategoryDto) {
        SubCategory subCategory = mapper.map(subCategoryDao.findByTitle(subCategoryDto.getTitle()),
                SubCategory.class);
        subCategoryDao.save(subCategory);
    }

    @Override
    public void update(String title, double basePrice, String description) {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setTitle(title);
        if (basePrice != 0)
            subCategoryDto.setBasePrice(basePrice);
        if (description != null || !description.isBlank())
            subCategoryDto.setDescription(description);
        update(subCategoryDto);
    }

    @Override
    public List<SubCategoryDto> getAll() {
        return subCategoryDao.findAll().stream()
                .map(subCategory -> mapper.map(subCategory, SubCategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SubCategoryDto getById(Integer theId) {
        Optional<SubCategory> found = subCategoryDao.findById(theId);
        if (found.isPresent()) {
            return mapper.map(found.get(), SubCategoryDto.class);
        } else {
            throw new NotFoundDta("❌❌❌ this subService is not exist ❌❌❌");
        }
    }

    @Override
    public SubCategoryDto findByTitle(String title) {
        Optional<SubCategory> found = subCategoryDao.findByTitle(title);
        if (found.isPresent()) {
            return mapper.map(found.get(), SubCategoryDto.class);
        } else {
            throw new NotFoundDta("❌❌❌ this subCategory is not exist ❌❌❌");
        }
    }

    @Override
    public List<SubCategoryDto> findAllSubCategoryOfACategory(String categoryTitle) {
        Optional<Category> category = categoryDao.findByTitle(categoryTitle);
        if (category.isPresent())
            return subCategoryDao.findSubCategoryByCategory(category.get())
                    .stream().map(subCategory -> mapper.map(subCategory, SubCategoryDto.class))
                    .collect(Collectors.toList());
        else
            throw new NotFoundDta("no data has found");
    }

    @Override
    public Set<SubCategoryDto> findSubCategoryOfAnExpert(ExpertDto expertDto) {
        return expertDto.getSubCategoryList();
    }
}