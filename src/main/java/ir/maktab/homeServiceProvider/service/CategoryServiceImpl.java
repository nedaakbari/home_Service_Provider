package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.CategoryDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.dto.CategoryDto;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper mapper;
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
            return all.stream()
                    .map(category -> mapper.map(category, CategoryDto.class))
                    .collect(Collectors.toList());
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

    @Override
    public Category findByTitle(String title) {
        Optional<Category> main = categoryDao.findByTitle(title);
        if (main.isPresent()) {
            return main.get();
        } else
            throw new NotFoundDta("there is no Category With this title");
    }

    @Override
    public Category findById(int mainId) {
        Optional<Category> main = categoryDao.findById(mainId);
        if (main.isPresent()) {
            return main.get();
        } else
            throw new NotFoundDta("there is no Category With this id");
    }

}
