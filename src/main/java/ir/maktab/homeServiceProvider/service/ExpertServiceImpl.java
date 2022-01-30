package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.entity.Person.Expert;
import ir.maktab.homeServiceProvider.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.enums.Role;
import ir.maktab.homeServiceProvider.enums.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.repository.ExpertRepository;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.SubCategoryDto;
import ir.maktab.homeServiceProvider.service.exception.DuplicateData;
import ir.maktab.homeServiceProvider.service.exception.IncorrectInformation;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.exception.UserNotFoundException;
import ir.maktab.homeServiceProvider.service.interfaces.ExpertService;
import ir.maktab.homeServiceProvider.service.interfaces.ImageFileService;
import ir.maktab.homeServiceProvider.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository expertDao;
    private final UserService userService;
    private final ImageFileService imageFileService;
    private final ModelMapper mapper;

    @Override
    public ExpertDto register(ExpertDto expertDto, CommonsMultipartFile image) {
        Optional<Expert> foundExpert = expertDao.findByUsernameAndPassword(expertDto.getUsername(), expertDto.getPassword());
        if (foundExpert.isPresent()) {
            throw new DuplicateData("this expert is already exist");
        } else {
            boolean duplicateEmail = userService.isDuplicateEmail(expertDto.getEmail());
            if (!duplicateEmail) {
                Expert expert = mapper.map(expertDto, Expert.class);
                expert.setStatus(UserRegistrationStatus.NEW);
                expert.setRole(Role.EXPERT);
                Expert saved = expertDao.save(expert);
                imageFileService.uploadImageFile(image, saved);
                return mapper.map(saved, ExpertDto.class);
            } else
                throw new DuplicateData("this email is already exist");
        }

    }


    @Override
    public ExpertDto login(ExpertDto expertDto) {
        Optional<Expert> expert = expertDao.findByUsernameAndPassword
                (expertDto.getUsername(), expertDto.getPassword());
        if (expert.isEmpty())
            throw new UserNotFoundException();
        return mapper.map(expert.get(),ExpertDto.class);
    }

    @Override
    public void delete(ExpertDto expertDto) {
        Optional<Expert> expert = expertDao.findByEmail(expertDto.getEmail());
        if (expert.isPresent())
            expertDao.delete(expert.get());
        else throw new UserNotFoundException();
    }

    @Override
    public List<ExpertDto> getAll() {
        return expertDao.findAll().stream()
                .map(expert -> mapper.map(expert, ExpertDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExpertDto getById(Integer theId) {
        Optional<Expert> found = expertDao.findById(theId);
        if (found.isPresent())
            return mapper.map(found.get(), ExpertDto.class);
        else throw new NotFoundDta("no expert found");
    }

    @Override
    public ExpertDto findByEmail(String email) {
        Optional<Expert> found = expertDao.findByEmail(email);
        if (found.isPresent()) {
            return mapper.map(found.get(), ExpertDto.class);
        } else throw new NotFoundDta("expert not exist!");
        //return expert.orElseThrow(() -> new NotFoundDta("expert not exist!"));
    }

    @Override
    public ExpertDto findByEmailAndPassWord(ExpertDto dto) {
        Optional<Expert> found = expertDao.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (found.isPresent()) {
            return mapper.map(found, ExpertDto.class);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public ExpertDto findExpertByUseAndPass(String username, String password) {
        Optional<Expert> expert = expertDao.findByUsernameAndPassword(username, password);
        if (expert.isPresent()) {
            return mapper.map(expert.get(), ExpertDto.class);
        } else
            throw new NotFoundDta("no expert found with these use and pass");
    }


    @Override
    public void removeSubCategoryFromExpertList(ExpertDto expertDto, SubCategoryDto subCategoryDto) {
        Expert expert = mapper.map(expertDto, Expert.class);
        SubCategory subCategory = mapper.map(subCategoryDto, SubCategory.class);
        Set<SubCategory> expertList = expert.getSubCategoryList();
        expertList.remove(subCategory);
        expert.setSubCategoryList(expertList);
        expertDao.save(expert);
    }

    @Override
    public void addSubCategoryToExpertList(ExpertDto expertDto, SubCategoryDto subCategoryDto) {
        Expert expert = mapper.map(expertDto, Expert.class);
        SubCategory subCategory = mapper.map(subCategoryDto, SubCategory.class);
        Set<SubCategory> expertList = expert.getSubCategoryList();
        expertList.add(subCategory);
        expert.setSubCategoryList(expertList);
        expertDao.save(expert);
    }

    @Override
    public void updateScore(ExpertDto expertDto, double getScore) {
        double expertScore = expertDto.getScore();
        double newScore = (expertScore + getScore) / 2;
        expertDao.updateScore(expertDto.getEmail(), newScore);
    }

    @Override
    public void updatePassword(String newPassword, String oldPassword, ExpertDto expertDto) {
        if (expertDto.getPassword().equals(oldPassword)) {
            expertDao.updatePassword(newPassword, expertDto.getEmail());
        } else
            throw new IncorrectInformation("incorrect password");
    }

    @Override
    public void updateCreditCart(double amount, ExpertDto expertDto) {
        expertDao.updateCreditCart(expertDto.getEmail(), amount);
    }

    @Override
    public List<ExpertDto> findAllExpertOfSubCategory(String subCategoryTitle) {
        List<Expert> experts = expertDao.findExpertsOfASubCategory(subCategoryTitle);
        return experts.stream().map(expert -> mapper.map(expert, ExpertDto.class))
                .collect(Collectors.toList());

    }
}
