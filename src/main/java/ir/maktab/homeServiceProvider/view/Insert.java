package ir.maktab.homeServiceProvider.view;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Insert {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CustomerService customerService = context.getBean(CustomerService.class);
        ExpertService expertService = context.getBean(ExpertService.class);
        UserService userService = context.getBean(UserService.class);
        CategoryService mainServiceService = context.getBean(CategoryService.class);
        SubCategoryService subServiceService = context.getBean(SubCategoryService.class);
        AdminService adminService = context.getBean(AdminService.class);
        OrderService orderService = context.getBean(OrderService.class);
        OfferService offerService = context.getBean(OfferService.class);
        TransActionService transActionService = context.getBean(TransActionService.class);
        // Mapper mapper=context.getBean(Mapper.class);


/**
 *save an expert customer admin mainService
 */
        ////////////// save customer

      /*  Customer nima = Customer.builder().firstName("nima").lastName("askari").role(Role.CUSTOMER).username("nima_")
                .password("Nima@12").status(UserRegistrationStatus.WAITING_FOR_CONFIRM)
                .phoneNumber("9398745644").email("nimaAskari@gmail.com").build();
        customerService.saveCustomer(nima);

        Customer neda = Customer.builder().firstName("neda").lastName("akbari").role(Role.CUSTOMER).username("neda_ak")
                .password("Neda@137").status(UserRegistrationStatus.WAITING_FOR_CONFIRM)
                .phoneNumber("09370730398").email("neda@gmail.com").build();
        customerService.saveCustomer(neda);


        Expert sara = Expert.builder().firstName("sara").lastName("saraee").email("sara@gmail.com")
                .phoneNumber("09370000000").username("sara@").password("sara_111").role(Role.EXPERT)
                .status(UserRegistrationStatus.WAITING_FOR_CONFIRM).build();
        expertService.saveExpert(sara);

        Expert nader = Expert.builder().firstName("nader").lastName("naderi").email("nader@gmail.com")
                .phoneNumber("09360000000").username("nader_").password("nader_22").role(Role.EXPERT)
                .status(UserRegistrationStatus.WAITING_FOR_CONFIRM).build();
        expertService.saveExpert(nader);

        Expert ashkan = Expert.builder().firstName("ashkan").lastName("ashkani").email("ashkan@gmail.com")
                .phoneNumber("09350000000").username("ashkan_").password("ashkan@7").role(Role.EXPERT)
                .status(UserRegistrationStatus.WAITING_FOR_CONFIRM).build();
        expertService.saveExpert(ashkan);

        Expert sana = Expert.builder().firstName("sana").lastName("sanaee").email("sana@gmail.com")
                .phoneNumber("09340000000").username("sana_").password("sana@747").role(Role.EXPERT)
                .status(UserRegistrationStatus.WAITING_FOR_CONFIRM).build();
        expertService.saveExpert(sana);


        /////////save admin
        Admin mehran = Admin.builder().firstName("mehran").lastName("ebadi")
                .userName("admin1").passWord("admin123").build();

        Admin sanaz = Admin.builder().firstName("sanaz").lastName("salehi")
                .userName("admin2").passWord("admin456").build();

        adminService.saveAdmin(mehran);
        adminService.saveAdmin(sanaz);

        /////////////save mainservice
        MainService mainService = new MainService();
        String name = "building decoration";
        String replace = name.toUpperCase().replace(" ", "_");
        mainService.setTitle(replace);

        MainService mainService2 = new MainService();
        mainService2.setTitle("BUILDING_FACILITIES");

        MainService mainService3 = new MainService();
        mainService3.setTitle("VEHICLES");

        MainService mainService4 = new MainService();
        mainService4.setTitle("MOVING_HELP");

        MainService mainService5 = new MainService();
        mainService5.setTitle("HOME_APPLIANCES");

        MainService mainService6 = new MainService();
        mainService6.setTitle("HOME_CLEANING_AND_HYGIENE");

        mainServiceService.saveMainService(mainService);
        mainServiceService.saveMainService(mainService2);
        mainServiceService.saveMainService(mainService3);
        mainServiceService.saveMainService(mainService4);
        mainServiceService.saveMainService(mainService5);
        mainServiceService.saveMainService(mainService6);
*/
        /**
         * find a mainServiceDao by Name
         */

       /* MainService home_appliances = mainServiceService.findByName("HOME_APPLIANCES");
        MainService home_cleaning_and_hygiene = mainServiceService.findByName("HOME_CLEANING_AND_HYGIENE");

        // /////////// save subServices
        SubService kitchenAppliances = new SubService();
        // kitchenAppliances.setMainService(MainServices.HOME_APPLIANCES);
        kitchenAppliances.setMain(home_appliances);
        kitchenAppliances.setDescription("clean detail of home");
        kitchenAppliances.setName("kitchenAppliances");
        kitchenAppliances.setBaseAmount(170000);
        subServiceService.saveSubService(kitchenAppliances);

        SubService laundry = new SubService();
        //laundry.setMainService(MainServices.HOME_APPLIANCES);
        laundry.setMain(home_appliances);
        laundry.setDescription("we laundry your packet");
        laundry.setName("laundry");
        laundry.setBaseAmount(100000);
        subServiceService.saveSubService(laundry);

        SubService audioAndVideoEquipment = new SubService();
        // audioAndVideoEquipment.setMainService(MainServices.HOME_APPLIANCES);
        audioAndVideoEquipment.setMain(home_appliances);
        audioAndVideoEquipment.setDescription("audio And Video Equipment");
        audioAndVideoEquipment.setBaseAmount(150000);
        audioAndVideoEquipment.setName("audioAndVideoEquipment");
        subServiceService.saveSubService(audioAndVideoEquipment);


        SubService cleaning = new SubService();
        // cleaning.setMainService(MainServices.HOME_CLEANING_AND_HYGIENE);
        cleaning.setMain(home_cleaning_and_hygiene);
        cleaning.setDescription("Leave the cleaning of your house to us");
        cleaning.setName("cleaning");
        cleaning.setBaseAmount(140000);
        subServiceService.saveSubService(cleaning);

        SubService laundryAndCarWash = new SubService();
        // laundryAndCarWash.setMainService(MainServices.HOME_CLEANING_AND_HYGIENE);
        laundryAndCarWash.setMain(home_cleaning_and_hygiene);
        laundryAndCarWash.setName("laundryAndCarWash");
        laundryAndCarWash.setDescription("We will deliver your cars clean from the first day");
        laundryAndCarWash.setBaseAmount(130000);
        subServiceService.saveSubService(laundryAndCarWash);

        SubService carpetAndUpholstery = new SubService();
        // carpetAndUpholstery.setMainService(MainServices.HOME_CLEANING_AND_HYGIENE);
        carpetAndUpholstery.setMain(home_cleaning_and_hygiene);
        carpetAndUpholstery.setName("carpetAndUpholstery");
        carpetAndUpholstery.setDescription("Clean carpets softer sofas");
        carpetAndUpholstery.setBaseAmount(120000);
        subServiceService.saveSubService(carpetAndUpholstery);

        SubService homeSpraying = new SubService();
        // homeSpraying.setMainService(MainServices.HOME_CLEANING_AND_HYGIENE);
        homeSpraying.setMain(home_cleaning_and_hygiene);
        homeSpraying.setName("homeSpraying");
        homeSpraying.setDescription("Clean the house from any insects");
        homeSpraying.setBaseAmount(110000);
        subServiceService.saveSubService(homeSpraying);*/

        /**
         * update a customer=>  add to creditCart
         * change password
         */
        //ابدیت کردن پسورد

        /*Customer neda_ak = customerService.findCustomerByUseAndPass("neda_ak", "Neda@137");
        neda_ak.setPassword("Neda@222");
        neda_ak.setCreditCart(200000);
        customerService.updateCustomer(neda_ak);*/

        //ابدیت کردن یوزر
        /*User nima = userService.findUserByUseAndPass("nima_", "Nima@12");
        nima.setCreditCart(300000);
        userService.saveUser(nima);*/

        /*User sara =  userService.findUserById(3);
        userService.updatePassword("sara_222",3);*/

        //دیلیت کردن کاستومر
        /* customerDao.delete(neda_ak);*/

        /**
         * find all => customer
         * find all => user
         * find all => expert
         * with all field
         */
        /*adminService.findAll().forEach(System.out::println);
        System.out.println("**********************");
        customerService.findAll().forEach(System.out::println);
        System.out.println("**************************************");
        expertService.findAll().forEach(System.out::println);
        System.out.println("**************************************");
        userService.findAllUser().forEach(System.out::println);*/


//پیدا کردن مین سرویس از طریق اسمش
         /*MainService HOME_APPLIANCES = mainServiceService.findByName("HOME_APPLIANCES".trim());
         MainService HOME_CLEANING_AND_HYGIENE = mainServiceService.findByName("HOME_CLEANING_AND_HYGIENE".trim());

//پیدا کردن همه مین سرویس ها
        mainServiceService.findAll().forEach(System.out::println);

//پیدا کردن همه سابسرویس ها
         subServiceService.findAll().forEach(System.out::println);

        //پیدا کرندن ساب سرویس با نامش
        SubService byName = subServiceService.findByName("kitchenAppliances ".trim());
        System.out.println(byName);

        //پیدا کردن اکسپرت از نامش
        Expert nader = expertService.findExpertByUseAndPass("nader_", "nader_22");
        Expert ashkan = expertService.findExpertByUseAndPass("ashkan_", "ashkan@7");
        Expert sara = expertService.findExpertByUseAndPass("sara@", "sara_111");

        System.out.println(nader);
        System.out.println(ashkan);
        System.out.println(sara);*/

        /**
         * filter
         */
        //فیلتر با رول
        /*UserFilter userFilter = new UserFilter();
        userFilter.setRole(Role.EXPERT);
        List<UserDto> allUsersByFilter = userService.findAllUsersByFilter(userFilter);
        allUsersByFilter.forEach(System.out::println);*/
// و فامیل فیلتر با اسم
       /* UserFilter userFilter = new UserFilter();
        userFilter.setName("nima");
        userFilter.setLastName("askari");
        List<UserDto> allUsersByFilter = userService.findAllUsersByFilter(userFilter);
        allUsersByFilter.forEach(System.out::println);*/

        /*UserFilter userFilter = new UserFilter();
        SubService laundry = subServiceService.findByName("laundry");
        userFilter.setSubService(laundry);
        expertService.findAllUsersByFilter(userFilter).forEach(System.out::println);//todo*/


        ////اضافه کردن اکسپرت به زیرتخصص ها

        /*Expert nader = expertService.findExpertByUseAndPass("nader_", "nader_22");
        Expert ashkan = expertService.findExpertByUseAndPass("ashkan_", "ashkan@7");
        Expert sara = expertService.findExpertByUseAndPass("sara@", "sara_111");*/

        /*
        //1 اول متخصص وارد مین سرویس میشه
        MainService home_appliances = mainServiceService.findByName("HOME_APPLIANCES");
        //میخواد همه زیر خدمت های این خدمت اصلی رو ببینه
        //home_appliances.getSubServiceList().forEach(System.out::println);//این شکلی باید فچ تایپش رو ایگر کنی
        subServiceService.findSubservienceFromMainService(home_appliances.getId()).forEach(System.out::println);
        System.out.println("***********************************8888");
         //انتخاب کنه
        SubService byName = subServiceService.findByName("kitchenAppliances ".trim());
        SubService laundry = subServiceService.findById(2);
        System.out.println(laundry);
        System.out.println("***********************************8888");
        //به اکسپرت ها اضافه میشه
        Set<Expert> experts = laundry.getExperts();
        experts.add(sara);
        experts.add(nader);
        laundry.setExperts(experts);
        subServiceService.updateSubService(laundry);

        MainService home_cleaning_and_hygiene = mainServiceService.findByName("HOME_CLEANING_AND_HYGIENE");
        //میخواد همه زیر خدمت های این خدمت اصلی رو ببینه
        subServiceService.findSubservienceFromMainService(home_cleaning_and_hygiene.getId()).forEach(System.out::println);
         //انتخاب کنه
        //SubService byName = serviceDao.findByName("kitchenAppliances ".trim());
        SubService spray = subServiceService.findById(7);
        System.out.println(spray);
        //به اکسپرت ها اضافه میشه
        Set<Expert> experts = spray.getExperts();
        experts.add( ashkan);
        experts.add(nader);
        spray.setExperts(experts);
        subServiceService.updateSubService(spray);

        //sara=> subservice2 => laundry
        //nader=> subservice2 => laundry  subService7 =>spray
        //ashkan =>subService7 =>spray
*/


        //میخوام ببینم نادر چه خدمت هایی داره
        //nader.getSubServiceList().forEach(System.out::println);//no session نمیخوام ایگر کنمش
        // subServiceService.findSubserivceOfExpert(nader.getId()).forEach(System.out::println);

        //میخوام ببینم سارا چه خدمت هایی داره
        // subServiceService.findSubserivceOfExpert(sara.getId()).forEach(System.out::println);

        //میخوام ببینم چه اکسپرت هایی داخل laundry خدمت میکنن
        /*Mapper mapper=new Mapper();//todo qualifier error
        SubService laundry = subServiceService.findByName("homeSpraying");
        Set<Expert> experts = laundry.getExperts();
        List<ExpertDto> collect = experts.stream().map(mapper::expertDto).collect(Collectors.toList());
        collect.forEach(System.out::println);*/

        //میخوام نادر رو از لاندی حذف کنم
       /* experts.remove(nader);
        laundry.setExperts(experts);
        subServiceService.updateSubService(laundry);*/

        //جستجو حوزه تخصصی متخصصان
        /*Mapper mapper=new Mapper();
        Expert expert = expertService.findByEmail("nader@gmail.com");
        System.out.println(expert);
        Set<SubService> services = expert.getSubServiceList();
        List<SubServiceDto> serviceDto = services.stream().map(mapper::subServiceDto).collect(Collectors.toList());
        System.out.println(serviceDto);*/

        /**
         * کاستومر لیست خدمات رو ببینه و سفارش بده
         */
       /* Customer neda = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
//اول کدوم خدمت ؟
        MainService byId = mainServiceService.findById(6);
        System.out.println("******** " + byId);
        //کدوم زیر خدمت رو از این خدمت میخوای؟
        subServiceService.findSubservienceFromMainService(byId.getId()).forEach(System.out::println);
        System.out.println("******** " + byId);
        //یکی از زیر خدمت ها رو انتخاب کنه
        SubService homeSpraying = subServiceService.findById(7);
        System.out.println("******** " + homeSpraying);

        Address address = new Address();
        address.setCity("shiraz");
        address.setStreet("engheleb");
        address.setZipCode("4785p");

        Orders orders = new Orders();
        orders.setCustomer(neda);
        orders.setProposedPrice(110000);
        orders.setSubService(homeSpraying);
        orders.setDescription("i want done this very good");
        orders.setAddress(address);
        Date date = DateUtil.convertStringToDate("2022-01-09");
        orders.setWorkDay(date);
        orderService.saveOrder(orders);*/

/**
 *متخصص بره لیست سفارش ها رو ببینه و اگر توی تخصصش بود بتونه پیشنهاد بده و کمتر از پایه هم پیشنهاد نده
 */
//از این خدمات کدومش؟
        /*mainServiceService.findAll().forEach(System.out::println);
        MainService main = mainServiceService.findById(5);
        System.out.println("************** ");
        //از کدوم خدمت؟
        subServiceService.findSubservienceFromMainService(main.getId()).forEach(System.out::println);
        System.out.println("************** ");
        //کدوم زیر خدمت رو میخوای لیست سفارشاش رو ببینی؟؟
        SubService homeSpray = subServiceService.findById(7);
        orderService.findOrdersOfSubService(homeSpray.getId()).forEach(System.out::println);
        System.out.println("*******************************************888");
        Orders orderByID = orderService.findOrderByID(1);*/


       /* Offer offer = new Offer();
        offer.setExpert(nader);
        LocalTime localTime = DateUtil.convertStringToTime("12:00");
        offer.setStartWorkTime(localTime);
        offer.setDescription("i can do that perfectly");
        offer.setOrders(orderByID);
        offer.setDuringTime(4);
        offer.setProposedPriceOffer(1500000);
        offer.setStatus(OfferStatus.SUSPENDED);
        offerService.saveOffer(offer, orderByID);*/

        /*Offer offer = new Offer();
        offer.setExpert(sara);
        LocalTime localTime = DateUtil.convertStringToTime("13:00");
        offer.setStartWorkTime(localTime);
        offer.setDescription("i can do in perfectWay");
        offer.setOrders(orderByID);
        offer.setDuringTime(3);
        offer.setProposedPriceOffer(1400000);
        offer.setStatus(OfferStatus.SUSPENDED);
        offerService.saveOffer(offer, orderByID);*/


        /**
         * الان کاستومر میخواد بیاد ببینه چه پیشنهادهایی برای سفارشش ثبت شده
         */
  /*      Customer neda = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
        System.out.println(neda);
        //neda.getOrdersList();//این باید فچ تایپش رو تغییر بدم
        orderService.findOrderOfCustomer(neda).forEach(System.out::println);
        System.out.println("which order do you wanna check offers?");
        Orders orderByID = orderService.findOrderByID(1);
        //orderByID.getOffers().forEach(System.out::println); باید فچ تایپش رو تغییر بدم
        List<OfferDto> allOfferOfOrder = offerService.findAllOfferOfOrder(orderByID);
        allOfferOfOrder.forEach(System.out::println);*/

/**
 *یکی از اینا رو انتخاب میکنه
 */
        /*Offer offerById = offerService.findOfferById(1);
        offerById.setStatus(OfferStatus.ACCEPTED);//todo where i must handle this?
        offerService.updateOffer(offerById);
        Expert expert = offerById.getExpert();
        orderByID.setExpert(expert);
        orderByID.setState(OrderState.WAITING_FOR_EXPERT_TO_COMING_TO_YOUR_PLACE);//todo where i must handle this?
        orderService.update(orderByID);*/

//حالا اکسپرته رفته اونجا تموم شده بعد میخواد امتیاز و نظر بده
        //اکسپرته اردر رو درحال انجام میزنه
        //اکسپرته کار رو تموم میزنه
/*        Orders orderByID = orderService.findOrderByID(1);
        orderByID.setState(OrderState.DONE);*/
        //orderService.update(orderByID);

        //وقتی دان میشه کاستومر باید پرداخت کنه و  پیام میره برای کاستومره که امتیاز بده
        /*Customer neda = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
        System.out.println("do you wanna paid? by catch   by online");
        TransAction transAction = new TransAction();
        //orderByID.getOffers()//todo set Price for this
       // transAction.setAmount();
        transAction.setCustomer(neda);
        transAction.setExpert(orderByID.getExpert());
        transAction.setOrders(orderByID);
        transActionService.save(transAction);*/

 /*       System.out.println("thanks for trust us ...please give score to expert from 1 to 10");
        int scoreByCustomer = 10;
        //orderByID.setScore(scoreByCustomer);
       // orderService.update(orderByID);
        Expert expert = orderByID.getExpert();
        expert.setScore( expert.getScore()+scoreByCustomer);
       // expertService.updateExpert(expert);
        //امتیاز که داد میگه دوست داری کانت بدی؟*/


    }

}