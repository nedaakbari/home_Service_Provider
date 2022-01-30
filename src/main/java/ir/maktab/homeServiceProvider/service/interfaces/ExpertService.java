package ir.maktab.homeServiceProvider.service.interfaces;

import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface ExpertService {

    ExpertDto register(ExpertDto expertDto, CommonsMultipartFile image);

    ExpertDto login(ExpertDto expertDto);

    void delete(ExpertDto expertDto);

    List<ExpertDto> getAll();

    ExpertDto getById(Integer theId);

    ExpertDto findExpertByUseAndPass(String username, String password);

    void updateCreditCart(double amount, ExpertDto expertDto);

    void updatePassword(String newPassword, String oldPassword, ExpertDto expertDto);

    void updateScore(ExpertDto expertDto, double getScore);

    void removeSubCategoryFromExpertList(ExpertDto expertDto, SubCategoryDto subCategoryDto);

    void addSubCategoryToExpertList(ExpertDto expertDto, SubCategoryDto subCategoryDto);

    ExpertDto findByEmail(String email);

    ExpertDto findByEmailAndPassWord(ExpertDto expertDto);

    List<ExpertDto> findAllExpertOfSubCategory(String subCategoryTitle);
}