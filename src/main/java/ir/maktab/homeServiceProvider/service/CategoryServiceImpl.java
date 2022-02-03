package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.entity.service.Category;
import ir.maktab.homeServiceProvider.data.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.repository.CategoryRepository;
import ir.maktab.homeServiceProvider.dto.CategoryDto;
import ir.maktab.homeServiceProvider.service.exception.DuplicateData;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper mapper;
    private final CategoryRepository categoryDao;

    @Override
    public void save(CategoryDto categoryDto) {
        Category category = mapper.map(categoryDto, Category.class);
        Optional<Category> foundMainService = categoryDao.findByTitle(category.getTitle());
        if (foundMainService.isPresent()) {
            throw new DuplicateData("❌❌❌ this main service is already exist ❌❌❌");
        } else {
            categoryDao.save(category);
        }
    }

    @Override
    public void delete(CategoryDto categoryDto) {
        categoryDao.deleteByTitle(categoryDto.getTitle());

    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> all = categoryDao.findAll();
        if (all.size() != 0) {
            return all.stream()
                    .map(category -> mapper.map(category, CategoryDto.class))
                    .collect(Collectors.toList());
        } else
            throw new NotFoundDta("❌❌❌ no mainService Exist  ❌❌❌");
    }

    @Override
    public CategoryDto findById(int theId) {
        Optional<Category> category = categoryDao.findById(theId);
        if (category.isPresent()) {
            return mapper.map(category.get(), CategoryDto.class);
        } else
            throw new NotFoundDta("there is no Category With this id");
    }

    @Override
    public CategoryDto findByTitle(String title) {
        Optional<Category> category = categoryDao.findByTitle(title);
        if (category.isPresent()) {
            return mapper.map(category.get(), CategoryDto.class);
        } else
            throw new NotFoundDta("there is no Category With this title");
    }

    // @Transactional
    @Override
    public void updateSubCategory(CategoryDto categoryDto, Set<SubCategory> subCategorySet) {
        categoryDao.updateSubCategory(categoryDto.getTitle(), subCategorySet);
    }
}
