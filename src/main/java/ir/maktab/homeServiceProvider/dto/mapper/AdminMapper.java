package ir.maktab.homeServiceProvider.dto.mapper;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class AdminMapper {
    public AdminDto adminToDto(Admin admin) {
        return AdminDto.builder()
                .id(admin.getId())
                .userName(admin.getUserName())
                .firstName(admin.getUserName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .build();
    }

    public Admin dtoToAdmin(AdminDto adminDto) {
        return Admin.builder()
                .userName(adminDto.getUserName())
                .firstName(adminDto.getFirstName())
                .lastName(adminDto.getLastName())
                .email(adminDto.getEmail())
                .build();
    }
}
