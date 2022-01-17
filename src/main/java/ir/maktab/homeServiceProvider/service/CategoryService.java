package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.CategoryDao;
import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.dto.CategoryDto;
import ir.maktab.homeServiceProvider.dto.mapper.CategoryMapper;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements Services<Category, CategoryDto, Integer> {
    private final CategoryMapper mapper;
    private final CategoryDao categoryDao;

    @Override
    public void save(Category category) {
        Optional<Category> foundMainService = categoryDao.findByTitle(category.getTitle());
        if (foundMainService.isPresent()) {
            throw new DuplicateData("❌❌❌ this main service is already exist ❌❌❌");
        } else {
            categoryDao.save(category);
        }
    }

    @Override
    public void delete(Category category) {
        categoryDao.delete(category);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<Category> all = categoryDao.findAll();
        if (all.size() != 0) {
            return all.stream().map(mapper::categoryToDto).collect(Collectors.toList());
        } else
            throw new NotFoundDta("❌❌❌ no mainService Exist  ❌❌❌");
    }

    @Override
    public Category getById(Integer theId) {

        return null;
    }


/*   public void updateMainService(MainService mainService) {
        mainServiceDao.update(mainService);
    }*/


    public Category findByTitle(String title) {
        Optional<Category> main = categoryDao.findByTitle(title);
        if (main.isPresent()) {
            return main.get();
        } else
            throw new NotFoundDta("there is no Category With this title");
    }

    public Category findById(int mainId) {
        Optional<Category> main = categoryDao.findById(mainId);
        if (main.isPresent()) {
            return main.get();
        } else
            throw new NotFoundDta("there is no Category With this id");
    }

}
