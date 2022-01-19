package ir.maktab.homeServiceProvider.view;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.dto.UserDto;
import ir.maktab.homeServiceProvider.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class findAllFilter {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        /*AddressDao addressDao = context.getBean(AddressDao.class);
        AddressService bean = context.getBean(AddressService.class);
        AdminDao adminService = context.getBean(AdminDao.class);
        CustomerDao customerService = context.getBean(CustomerDao.class);
        ExpertDao expertService = context.getBean(ExpertDao.class);
        UserDao userService = context.getBean(UserDao.class);
        CategoryDao categoryService = context.getBean(CategoryDao.class);
        SubCategoryDao subCategoryService = context.getBean(SubCategoryDao.class);
        OrderDao orderService = context.getBean(OrderDao.class);
        OfferDao offerService = context.getBean(OfferDao.class);*/
        AdminServiceImpl adminService = context.getBean(AdminServiceImpl.class);
        CustomerServiceImpl customerService = context.getBean(CustomerServiceImpl.class);
        ExpertServiceImpl expertService = context.getBean(ExpertServiceImpl.class);
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);
        CategoryServiceImpl categoryService = context.getBean(CategoryServiceImpl.class);
        SubCategoryServiceImpl subCategoryService = context.getBean(SubCategoryServiceImpl.class);
        OrderServiceImpl orderService = context.getBean(OrderServiceImpl.class);
        OfferServiceImpl offerService = context.getBean(OfferServiceImpl.class);
        TransActionServiceImpl transActionService = context.getBean(TransActionServiceImpl.class);


        /** find all => customer
         * find all => user
         * find all => expert
         * with all field
         */
        adminService.getAll().forEach(System.out::println);
        System.out.println("**********************");
        customerService.getAll().forEach(System.out::println);
        System.out.println("**************************************");
        expertService.getAll().forEach(System.out::println);
        System.out.println("**************************************");
        userService.getAll().forEach(System.out::println);


//پیدا کردن مین سرویس از طریق اسمش
        Category HOME_APPLIANCES = categoryService.findByTitle("HOME_APPLIANCES".trim());
        Category HOME_CLEANING_AND_HYGIENE = categoryService.findByTitle("HOME_CLEANING_AND_HYGIENE".trim());
        System.out.println(HOME_APPLIANCES);
        System.out.println(HOME_CLEANING_AND_HYGIENE);

//پیدا کردن همه مین سرویس ها
        categoryService.getAll().forEach(System.out::println);

        //پیدا کردن ساب سرویس با نامش
        SubCategory kitchenAppliance = subCategoryService.findByTitle("kitchenAppliances ".trim());
        System.out.println(kitchenAppliance);

        SubCategory spray = subCategoryService.findByTitle("homeSpraying ".trim());
        System.out.println(spray);

//پیدا کردن همه سابسرویس ها
        subCategoryService.getAll().forEach(System.out::println);

        //پیدا کردن اکسپرت از نامش
        Expert nader1 = expertService.findExpertByUseAndPass("nader_", "nader_22");
        Expert ashkan1 = expertService.findExpertByUseAndPass("ashkan_", "ashkan@7");
        //Expert sara2 = expertService.findExpertByUseAndPass("sara@", "sara_111");
        Expert sara2 = expertService.findExpertByUseAndPass("sara@", "sara_222");


        System.out.println(nader1);
        System.out.println(ashkan1);
        System.out.println(sara2);
        //System.out.println(sara2);


        /**
         * filter
         */

        //فیلتر با رول
        List<UserDto> allUsersByFilter = userService.findAllUsersByFilter(null, null, null, Role.EXPERT);
        allUsersByFilter.forEach(System.out::println);
// و فامیل فیلتر با اسم
        List<UserDto> allUsersByFilter2 = userService.findAllUsersByFilter("nima", "askari", null, null);
        allUsersByFilter2.forEach(System.out::println);

        /*SubService laundry = subCategoryService.findByName("laundry");
        userFilter.setSubService(laundry);
        expertService.findAllUsersByFilter(userFilter).forEach(System.out::println);//todo*/


    }
}
