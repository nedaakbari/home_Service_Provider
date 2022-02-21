package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.data.entity.Person.User;
import ir.maktab.homeServiceProvider.data.enums.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.data.repository.UserRepository;
import ir.maktab.homeServiceProvider.data.repository.specification.UserSpecifications;
import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.dto.UserFilterDto;
import ir.maktab.homeServiceProvider.service.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.exception.UserNotFoundException;
import ir.maktab.homeServiceProvider.service.interfaces.UserService;
import ir.maktab.homeServiceProvider.service.exception.DuplicateData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userDao;
    private final ModelMapper mapper;

    @Override
    public void register(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        Optional<User> foundUser = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (foundUser.isPresent()) {
            throw new DuplicateData("❌❌❌ Error,user with these use an pass is already exist ❌❌❌");
        } else {
            userDao.save(user);
        }
    }

    @Override
    public UserDto login(UserDto userDto) {
        Optional<User> user = userDao.findByEmailAndPassword
                (userDto.getEmail(), userDto.getPassword());
        if (user.isEmpty())
            throw new UserNotFoundException();
        return mapper.map(user.get(), UserDto.class);
    }

    @Override
    public void delete(UserDto userDto) {
        User user = userDao.findByEmail(userDto.getEmail()).get();
        userDao.delete(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.findAll().stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    public List<UserDto> findAllUserForVerify() {
        return userDao.findAllUserForVerify().stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }


    @Override
    public UserDto getById(Integer theId) {
        Optional<User> foundUser = userDao.findById(theId);
        if (foundUser.isPresent())
            return mapper.map(foundUser.get(), UserDto.class);
        else
            throw new NotFoundDta("❌❌❌ Error not found user ❌❌❌");
    }

    @Override
    public UserDto getByEmail(String email) {//todo
        Optional<User> found = userDao.findByEmail(email);
        if (found.isPresent())
            return mapper.map(found.get(),UserDto.class);
        else throw new NotFoundDta("not user found with this email");
    }

    @Override
    public void updatePassword(String newPassword, UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(newPassword);
        userDao.save(user);
    }

    @Override
    public void updateCreditCart(double amount, UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setCreditCart(amount);
        userDao.save(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userDao.findByEmail(userDto.getEmail()).get();
        userDao.save(user);
    }

    @Override
    public void updateStatus(String userEmail,UserRegistrationStatus status) {
        userDao.updateStatus(userEmail, status);
    }

    @Override
    public UserDto findUserByUseAndPass(String userName, String password) {
        Optional<User> foundUser = userDao.findByUsernameAndPassword(userName, password);
        if (foundUser.isPresent())
            return mapper.map(foundUser.get(), UserDto.class);
        else
            throw new NotFoundDta("❌❌❌ Error not found user ❌❌❌");
    }

    @Override
    public boolean isDuplicateEmail(String email) {
        Optional<User> userByEmail = userDao.findByEmail(email);
        if (userByEmail.isPresent())
            return true;
        else
            return false;
    }

    @Override
    public List<UserDto> findAllUsersByFilter(UserFilterDto dto) {//String firstname, String lastname, String email, Role role
        Specification<User> userSpecification = UserSpecifications.filterUsers(dto);
        return userDao.findAll(userSpecification).stream()
                .map(user -> mapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> searchUsers(UserFilterDto dto) {
        Sort sort = Sort.by("lastName").ascending();
        Pageable pageable = PageRequest.of(dto.getPageNumber(), dto.getPageSize(), sort);//برای پیجینیشنه هست
        Specification<User> specification = UserSpecifications.filterUsers(dto);
        return userDao
                .findAll(Specification.where(specification), pageable)
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return userDao.findAll(pageable);
    }

}
