package ir.maktab.homeServiceProvider.service;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.dto.AdminDto;
import ir.maktab.homeServiceProvider.exception.DuplicateData;
import ir.maktab.homeServiceProvider.exception.IncorrectInformation;
import ir.maktab.homeServiceProvider.exception.NotFoundDta;
import ir.maktab.homeServiceProvider.service.AdminServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AdminServiceTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    AdminServiceImpl adminService = context.getBean(AdminServiceImpl.class);

    Admin admin;

    @Test
    void saveAnAdmin_save() {
        admin = Admin.builder()
                .firstName("mehran").lastName("mehrani")
                .userName("admin1").passWord("admin@12")
                .email("mehran@gmail.com").build();
        adminService.save(admin);
        List<AdminDto> adminDtoList = adminService.getAll();
        assertEquals(1, adminDtoList.size());
       /* Admin admin = adminService.save(admin);
        assertNotNull(admin);*/

    }

    @Test
    public void saveAdminWithIncorrectPassword_saveMethod_throwsException() {
        admin = Admin.builder()
                .firstName("mehran").lastName("ebadi")
                .userName("admin1").passWord("11111111111111")
                .email("mehran@gmail.com").build();
        adminService.save(admin);
        assertThrows(IncorrectInformation.class, () -> adminService.save(admin));//todo
    }

    @Test
    public void SaveAdminWithDuplicateEmail_saveAdmin_throwsException() {
        admin = Admin.builder()
                .firstName("neda").lastName("akbari")
                .userName("admin1").passWord("admin@12")
                .email("mehran@gmail.com").build();
        assertThrows(DuplicateData.class, () -> adminService.save(admin));
    }

    @Test
    void getData_findAminByEmail_thenCompare() {
        String email = "neda@gmail.com";
        admin = adminService.findAminByEmail(email);
        assertEquals("neda@gmail.com", admin.getEmail());
    }

    @Test
    public void getData_getById_notBeNull() {
        admin = adminService.getById(1);
        assertNotNull(admin);
    }

    @Test
    public void getByIdIsNotExist_getById_throwException() {
        assertThrows(NotFoundDta.class, () -> adminService.getById(10));
    }

    @Test
    public void getALlAdmin_getAll_findSizeToCompare() {
        List<AdminDto> getAll = adminService.getAll();
        assertEquals(1, getAll.size());
    }

    @Test
    public void changePassword_updatePassword_getEquals() {
        admin = adminService.getById(1);
        adminService.updatePassword(admin.getPassWord(), "mehran@1", admin);
        admin = adminService.getById(1);
        assertEquals("Mehran@1", admin.getPassWord());
    }

/*    @Test
   public void ChangePassWithIncorrectOldPass_updatePassword_throwException() {
   admin = adminService.getById(1);
        assertThrows(IncorrectInformation.class, () ->
                adminService.updatePassword(admin.getPassWord()+"25", "Mehran@5", admin)
        );
    }*/

    @Test
    public void changePasswordWithIncorrectNewPassword_updatePassword_throwException() {
        admin = adminService.getById(1);
        assertThrows(IncorrectInformation.class, () ->
                adminService.updatePassword(admin.getPassWord(), "Mehran", admin)
        );
    }

    @Test
    public void updateAdmin_update_thenCompareChange() {
        admin = adminService.getById(1);
        admin.setUserName("neda_123");
        adminService.update(admin);
        admin = adminService.getById(1);
        assertEquals("neda_123", admin.getUserName());
    }

    @Test
    public void updateAdminWithDuplicateEmail_update_() {
        admin = adminService.getById(1);
        admin.setEmail(admin.getEmail());
        assertThrows(DuplicateData.class, () -> adminService.update(admin));
    }

    @Test
    public void deleteAdmin_delete_findSizeToCompare() {
        admin = adminService.getById(1);
        adminService.delete(admin);
        List<AdminDto> addressList = adminService.getAll();
        assertEquals(0, addressList.size());
    }

    @Test
    public void deleteAddressIsNotExist_delete_throwException() {
        admin = adminService.getById(1);
        adminService.delete(admin);
        assertThrows(NotFoundDta.class, () -> adminService.delete(admin));
    }
}
