package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.dao.ExpertDao;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.dto.ExpertDto;
import ir.maktab.homeServiceProvider.dto.mapper.ExpertMapper;
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
public class ExpertService implements Services<Expert, ExpertDto, Integer> {
    private final ExpertMapper mapper;
    private final ExpertDao expertDao;


    @Override
    public void save(Expert expert) {
        Optional<Expert> foundUser = expertDao.findByUsernameAndPassword(expert.getUsername(), expert.getPassword());
        if (foundUser.isPresent()) {
            throw new DuplicateData("this expert is already exist");
        } else {
            expert.setStatus(UserRegistrationStatus.NEW);
            expertDao.save(expert);
        }
    }

    @Override
    public void delete(Expert expert) {
        expertDao.delete(expert);

    }

    @Override
    public List<ExpertDto> getAll() {
        List<Expert> all = expertDao.findAll();
        return all.stream().map(mapper::expertDto).collect(Collectors.toList());
    }

    @Override
    public Expert getById(Integer theId) {
        Optional<Expert> found = expertDao.findById(theId);
        if (found.isPresent())
            return found.get();
        else throw new NotFoundDta("no expert found");
    }


    public Expert findExpertByUseAndPass(String username, String password) {
        Optional<Expert> expert = expertDao.findByUsernameAndPassword(username, password);
        if (expert.isPresent()) {
            return expert.get();
        } else
            throw new RuntimeException("no expert found with these use and pass");
    }

}
