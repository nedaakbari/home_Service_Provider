package ir.maktab.homeServiceProvider.dto.mapperInterface;

import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import org.mapstruct.Mapper;

@Mapper
public interface AdminMapper {
    AdminDto adminToAdminDto(Admin admin);

    Admin AdminDtoToAdmin(AdminDto adminDto);
}
