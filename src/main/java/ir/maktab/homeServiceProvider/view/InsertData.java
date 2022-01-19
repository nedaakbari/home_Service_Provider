package ir.maktab.homeServiceProvider.view;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Admin;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.Person.User;
import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.Role;
import ir.maktab.homeServiceProvider.data.model.enumeration.UserRegistrationStatus;
import ir.maktab.homeServiceProvider.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InsertData {
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

        /**
         *save an expert customer admin mainService
         * */


             ////////////// save customer
        Customer nima = Customer.builder().firstName("nima").lastName("askari").role(Role.CUSTOMER).username("nima_")
                .password("Nima@12")
                .phoneNumber("9398745644").email("nimaAskari@gmail.com").build();
        customerService.save(nima);

        Customer neda = Customer.builder().firstName("neda").lastName("akbari").role(Role.CUSTOMER).username("neda_ak")
                .password("Neda@137").status(UserRegistrationStatus.WAITING_FOR_CONFIRM)
                .phoneNumber("09370730398").email("neda@gmail.com").build();
        customerService.save(neda);


        Expert sara = Expert.builder().firstName("sara").lastName("saraee").email("sara@gmail.com")
                .phoneNumber("09370000000").username("sara@").password("sara_111").role(Role.EXPERT)
                .score(70.0).status(UserRegistrationStatus.WAITING_FOR_CONFIRM).build();
        expertService.save(sara);

        Expert nader = Expert.builder().firstName("nader").lastName("naderi").email("nader@gmail.com")
                .phoneNumber("09360000000").username("nader_").password("nader_22").role(Role.EXPERT)
                .score(50.0).status(UserRegistrationStatus.WAITING_FOR_CONFIRM).build();
        expertService.save(nader);

        Expert ashkan = Expert.builder().firstName("ashkan").lastName("ashkani").email("ashkan@gmail.com")
                .phoneNumber("09350000000").username("ashkan_").password("ashkan@7").role(Role.EXPERT)
                .score(20.0).status(UserRegistrationStatus.WAITING_FOR_CONFIRM).build();
        expertService.save(ashkan);

        Expert sana = Expert.builder().firstName("sana").lastName("sanaee").email("sana@gmail.com")
                .phoneNumber("09340000000").username("sana_").password("sana@747").role(Role.EXPERT)
                .score(40.0).status(UserRegistrationStatus.WAITING_FOR_CONFIRM).build();
        expertService.save(sana);


        /////////save admin
        Admin mehran = Admin.builder().firstName("mehran").lastName("ebadi")
                .userName("admin1").passWord("admin123").email("mehran@gmail.com").build();

        Admin sanaz = Admin.builder().firstName("sanaz").lastName("salehi")
                .userName("admin2").passWord("admin456").email("sanaz@gmail.com").build();

        adminService.save(mehran);
        adminService.save(sanaz);

        /////////////save mainservice
        Category category = new Category();
        String name = "building decoration";
        String replace = name.toUpperCase().replace(" ", "_");
        category.setTitle(replace);

        Category category2 = new Category();
        category2.setTitle("BUILDING_FACILITIES");

        Category category3 = new Category();
        category3.setTitle("VEHICLES");

        Category category4 = new Category();
        category4.setTitle("MOVING_HELP");

        Category category5 = new Category();
        category5.setTitle("HOME_APPLIANCES");

        Category category6 = new Category();
        category6.setTitle("HOME_CLEANING_AND_HYGIENE");

        categoryService.save(category);
        categoryService.save(category4);
        categoryService.save(category2);
        categoryService.save(category3);
        categoryService.save(category5);
        categoryService.save(category6);

        //find mainService by name
        Category home_appliances = categoryService.findByTitle("HOME_APPLIANCES");
        Category home_cleaning_and_hygiene = categoryService.findByTitle("HOME_CLEANING_AND_HYGIENE");

        // /////////// save subServices
        SubCategory kitchenAppliances = new SubCategory();
        // kitchenAppliances.setMainService(MainServices.HOME_APPLIANCES);
        kitchenAppliances.setCategory(home_appliances);
        kitchenAppliances.setDescription("clean detail of home");
        kitchenAppliances.setTitle("kitchenAppliances");
        kitchenAppliances.setBaseAmount(170000.0);
        subCategoryService.save(kitchenAppliances);

        SubCategory laundry = new SubCategory();
        //laundry.setMainService(MainServices.HOME_APPLIANCES);
        laundry.setCategory(home_appliances);
        laundry.setDescription("we laundry your packet");
        laundry.setTitle("laundry");
        laundry.setBaseAmount(100000.0);
        subCategoryService.save(laundry);

        SubCategory audioAndVideoEquipment = new SubCategory();
        // audioAndVideoEquipment.setMainService(MainServices.HOME_APPLIANCES);
        audioAndVideoEquipment.setCategory(home_appliances);
        audioAndVideoEquipment.setDescription("audio And Video Equipment");
        audioAndVideoEquipment.setBaseAmount(150000.0);
        audioAndVideoEquipment.setTitle("audioAndVideoEquipment");
        subCategoryService.save(audioAndVideoEquipment);


        SubCategory cleaning = new SubCategory();
        // cleaning.setMainService(MainServices.HOME_CLEANING_AND_HYGIENE);
        cleaning.setCategory(home_cleaning_and_hygiene);
        cleaning.setDescription("Leave the cleaning of your house to us");
        cleaning.setTitle("cleaning");
        cleaning.setBaseAmount(140000.0);
        subCategoryService.save(cleaning);

        SubCategory laundryAndCarWash = new SubCategory();
        // laundryAndCarWash.setMainService(MainServices.HOME_CLEANING_AND_HYGIENE);
        laundryAndCarWash.setCategory(home_cleaning_and_hygiene);
        laundryAndCarWash.setTitle("laundryAndCarWash");
        laundryAndCarWash.setDescription("We will deliver your cars clean from the first day");
        laundryAndCarWash.setBaseAmount(130000.0);
        subCategoryService.save(laundryAndCarWash);

        SubCategory carpetAndUpholstery = new SubCategory();
        // carpetAndUpholstery.setMainService(MainServices.HOME_CLEANING_AND_HYGIENE);
        carpetAndUpholstery.setCategory(home_cleaning_and_hygiene);
        carpetAndUpholstery.setTitle("carpetAndUpholstery");
        carpetAndUpholstery.setDescription("Clean carpets softer sofas");
        carpetAndUpholstery.setBaseAmount(120000.0);
        subCategoryService.save(carpetAndUpholstery);

        SubCategory homeSpraying = new SubCategory();
        // homeSpraying.setMainService(MainServices.HOME_CLEANING_AND_HYGIENE);
        homeSpraying.setCategory(home_cleaning_and_hygiene);
        homeSpraying.setTitle("homeSpraying");
        homeSpraying.setDescription("Clean the house from any insects");
        homeSpraying.setBaseAmount(110000.0);
        subCategoryService.save(homeSpraying);




        ////////////////////////////////////update



        /** update a customer=> add to creditCart
         * change password
         */
//روش اول
        /*
        //ابدیت کردن پسورد
        Customer neda_ak = customerService.findCustomerByUseAndPass("neda_ak", "Neda@137");
        customerService.updatePassword("Neda@222", neda_ak.getId());
        //neda_ak.setCreditCart(200000.0);

        //ابدیت کردن کردیت کارت یوزر
        User nima = userService.findUserByUseAndPass("nima_", "Nima@12");
        nima.setCreditCart(300000.00);
        userService.updateCreditCart(nima);

        User sara = userService.getById(3);
        userService.updatePassword("sara_222", sara.getId());

        //دیلیت کردن کاستومر
        //customerDao.delete(neda_ak);*/


        //روش دوم
        // ابدیت کردن پسورد و کردیت کارت کاستومر
        Customer neda_ak = customerService.findCustomerByUseAndPass("neda_ak", "Neda@137");
        customerService.updatePassword("Neda@222", neda_ak);
        customerService.updateCreditCart(200000.0, neda_ak);


        //ابدیت کردن کردیت کارت یوزر
        User nima1 = userService.findUserByUseAndPass("nima_", "Nima@12");
        userService.updateCreditCart(300000.00, nima1);

        Expert sara1 = expertService.getById(3);
        userService.updatePassword("sara_222", sara1);
        //دیلیت کردن کاستومر
        //customerDao.delete(neda_ak);

    }
}
