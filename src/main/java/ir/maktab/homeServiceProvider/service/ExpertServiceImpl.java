package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.ExpertDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.EntityNotExistException;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.interfaces.ExpertService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl /*implements ExpertService*/ {
    private ModelMapper mapper = new ModelMapper();
    private final ExpertDao expertDao;

    public void save(Expert expert) {
        Optional<Expert> foundUser = expertDao.findByUsernameAndPassword(expert.getUsername(), expert.getPassword());
        if (foundUser.isPresent()) {
            throw new DuplicateData("this expert is already exist");
        } else {
            expert.setStatus(UserRegistrationStatus.NEW);
            expertDao.save(expert);
        }
    }

    public void delete(Expert expert) {
        expertDao.delete(expert);

    }

    public List<ExpertDto> getAll() {
        return expertDao.findAll().stream()
                .map(expert -> mapper.map(expert, ExpertDto.class))
                .collect(Collectors.toList());
    }

    public Expert getById(Integer theId) {
        Optional<Expert> found = expertDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no expert found");
    }


    public Expert findByEmail(String email) {
        Optional<Expert> found = expertDao.findByEmail(email);
 /*       if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no expert found");
*/ //todo which one is better
        return found.orElseThrow(() -> new NotFoundDta("expert not exist!"));
    }

    public Expert findExpertByUseAndPass(String username, String password) {
        Optional<Expert> expert = expertDao.findByUsernameAndPassword(username, password);
        if (expert.isPresent()) {
            return expert.get();
        } else
            throw new RuntimeException("no expert found with these use and pass");
    }

    public void addSubCategoryToExpertList(Expert expert, SubCategory subCategory) {
        //Set<SubCategory> subCategoryList = expert.getSubCategoryList();
        /*Set<SubCategory> subCategoryList=new HashSet<>();
         expertDao.findSubCategoryOfExpert(expert.getId()).stream()
                 .forEach(category-> subCategoryList.add(category) );*/
        Set<SubCategory> subCategoryList = expertDao.findSubCategoryOfExpert(expert.getId());
        subCategoryList.add(subCategory);
        expert.setSubCategoryList(subCategoryList);
        expertDao.save(expert);
    }

    public void removeSubCategoryFromExpertList(Expert expert, SubCategory subCategory) {
        Set<SubCategory> subCategoryList = expert.getSubCategoryList();
        subCategoryList.remove(subCategory);
        expert.setSubCategoryList(subCategoryList);
        expertDao.save(expert);
    }

    public void updateExpert(Expert expert) {
        expertDao.save(expert);
    }

    public void updatePassword(String newPassword, Expert expert) {
        expert.setPassword(newPassword);
        expertDao.save(expert);
    }

    public void updateCreditCart(double amount, Expert expert) {
        expert.setCreditCart(amount);
        expertDao.save(expert);
    }

    public void updateScore(Expert expert, double getScore) {
        double expertScore = expert.getScore();
        double newScore = (expertScore + getScore) / 2;
        expert.setScore(newScore);
        expertDao.save(expert);
    }

    //TODO by expert
    public List<SubCategoryDto> findServicesByEmail(String email) {
        Expert expert = findByEmail(email);
        Set<SubCategory> services = expert.getSubCategoryList();
        return services.stream()
                .map(item -> mapper.map(item, SubCategoryDto.class)).collect(Collectors.toList());
    }


        public List<Expert> findExpertsOfASubCategory(int subId) {
        return expertDao.findExpertsOfASubCategory(subId);
    }

}
