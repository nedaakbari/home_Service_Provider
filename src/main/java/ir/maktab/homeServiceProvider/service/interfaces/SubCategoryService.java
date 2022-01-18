package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.data.dao.SubCategoryDao;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface SubCategoryService {
    void save(SubCategory subCategory);

    void delete(SubCategory subCategory);

    List<SubCategoryDto> getAll();

    public SubCategory getById(Integer theId);

    SubCategory findByTitle(String title);

    List<SubCategory> findSubservienceFromCategory(int categoryId);

    List<SubCategory> findSubServiceOfExpert(int expertId);

}
