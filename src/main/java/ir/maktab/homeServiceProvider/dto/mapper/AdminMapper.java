/*package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AdminMapper {
    AdminDto entityToDto(Admin admin);

    Admin dtoToEntity(AdminDto adminDto);
}

@Component
class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminDto entityToDto(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .userName(admin.getUserName())
                .firstName(admin.getUserName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .build();
    }

    @Override
    public Admin dtoToEntity(AdminDto adminDto) {
        return Admin.builder()
                .userName(adminDto.getUserName())
                .firstName(adminDto.getFirstName())
                .lastName(adminDto.getLastName())
                .email(adminDto.getEmail())
                .build();
    }
}*/
