package ir.maktab.homeServiceProvider.view;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Address;
import ir.maktab.homeServiceProvider.data.model.entity.Offer;
import ir.maktab.homeServiceProvider.data.model.entity.Orders;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Customer;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.data.model.enumeration.OfferStatus;
import ir.maktab.homeServiceProvider.service.*;
import ir.maktab.homeServiceProvider.util.DateUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;


public class AddOrder {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        AdminServiceImpl adminService = context.getBean(AdminServiceImpl.class);
        CustomerServiceImpl customerService = context.getBean(CustomerServiceImpl.class);
        ExpertServiceImpl expertService = context.getBean(ExpertServiceImpl.class);
        UserServiceImpl userService = context.getBean(UserServiceImpl.class);
        CategoryServiceImpl categoryService = context.getBean(CategoryServiceImpl.class);
        SubCategoryServiceImpl subCategoryService = context.getBean(SubCategoryServiceImpl.class);
        OrderServiceImpl orderService = context.getBean(OrderServiceImpl.class);
        OfferServiceImpl offerService = context.getBean(OfferServiceImpl.class);
        TransActionServiceImpl transActionService = context.getBean(TransActionServiceImpl.class);

        /*CategoryDto(id=1, title=BUILDING_DECORATION)
        CategoryDto(id=3, title=BUILDING_FACILITIES)
        CategoryDto(id=5, title=HOME_APPLIANCES)
        CategoryDto(id=6, title=HOME_CLEANING_AND_HYGIENE)
        CategoryDto(id=2, title=MOVING_HELP)
        CategoryDto(id=4, title=VEHICLES)*/


        ///////  //category{id=6, title='HOME_CLEANING_AND_HYGIENE'},
/*      SubCategory(id=4, title=cleaning, baseAmount=140000.0
        SubCategory(id=5, title=laundryAndCarWash, baseAmount=130000.0
        SubCategory(id=6, title=carpetAndUpholstery, baseAmount=120000.0
        SubCategory(id=7, title=homeSpraying, baseAmount=110000.0*/


        /**
         * کاستومر لیست خدمات رو ببینه و سفارش بده
         */
        Customer neda = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
        Customer nima = customerService.findCustomerByUseAndPass("nima_", "Nima@12");

        //اول کدوم خدمت ؟
        Category HOME_CLEANING_AND_HYGIENE = categoryService.findById(6);
        System.out.println("******** " + HOME_CLEANING_AND_HYGIENE);

        Category HOME_APPLIANCES = categoryService.findById(5);
        System.out.println("******** " + HOME_APPLIANCES);

        //کدوم زیر خدمت رو از این خدمت میخوای؟
        subCategoryService.findSubservienceOfACategory(HOME_CLEANING_AND_HYGIENE.getId()).forEach(System.out::println);
        System.out.println("******** " + HOME_CLEANING_AND_HYGIENE);
        //یکی از زیر خدمت ها رو انتخاب کنه
        SubCategory homeSpraying = subCategoryService.getById(7);

        SubCategory kitchenAppliances = subCategoryService.findByTitle("kitchenAppliances ".trim());
        SubCategory laundry = subCategoryService.getById(2); //=>5  HOME_APPLIANCES 100000
        SubCategory spray = subCategoryService.getById(7); //=>6 HOME_CLEANING_AND_HYGIENE  110000


        //ثبت سفارش
     /*   Orders nedaOrder = new Orders();

        Address address = new Address();
        address.setCity("shiraz");
        address.setStreet("engheleb");
        address.setZipCode("4785p");

        nedaOrder.setProposedPrice(110000.0);
        nedaOrder.setCategory(HOME_CLEANING_AND_HYGIENE);
        nedaOrder.setCustomer(neda);
        nedaOrder.setAddress(address);
        nedaOrder.setSubCategory(spray);
        nedaOrder.setDescription("i want done this very well");
        Date date = DateUtil.convertStringToDate("2022-01-09");
        nedaOrder.setDoWorkDate(date);
        orderService.save(nedaOrder);


        Orders nimaOrder = new Orders();

        Address nimaAddress = new Address();
        nimaAddress.setCity("tehran");
        nimaAddress.setStreet("azadi");
        nimaAddress.setZipCode("485p");

        nedaOrder.setCategory(HOME_CLEANING_AND_HYGIENE);
        nimaOrder.setSubCategory(spray);

        nimaOrder.setCustomer(nima);
        nimaOrder.setAddress(nimaAddress);
        nimaOrder.setProposedPrice(120000.0);
        nimaOrder.setDescription("i want done this very well");
        Date date2 = DateUtil.convertStringToDate("2022-02-20");
        nimaOrder.setDoWorkDate(date2);
        orderService.save(nimaOrder);*/


        /*Orders nedaOrder = new Orders();

        Address address = new Address();
        address.setCity("shiraz");
        address.setStreet("engheleb");
        address.setZipCode("4785p");

        nedaOrder.setProposedPrice(120000.0);
        nedaOrder.setCategory(HOME_APPLIANCES);
        nedaOrder.setCustomer(neda);
        nedaOrder.setAddress(address);
        nedaOrder.setSubCategory(laundry);
        nedaOrder.setDescription("i want done");
        Date date = DateUtil.convertStringToDate("2022-02-10");
        nedaOrder.setDoWorkDate(date);
        orderService.save(nedaOrder);*/

/**
 *متخصص بره لیست سفارش ها رو ببینه و اگر توی تخصصش بود بتونه پیشنهاد بده و کمتر از پایه هم پیشنهاد نده
 */

        Expert nader = expertService.findExpertByUseAndPass("nader_", "nader_22");
        Expert ashkan = expertService.findExpertByUseAndPass("ashkan_", "ashkan@7");
        Expert sara = expertService.findExpertByUseAndPass("sara@", "sara_222");
        Expert sana = expertService.findExpertByUseAndPass("sana_", "sana@747");


//از این خدمات کدومش؟
        categoryService.getAll().forEach(System.out::println);
        Category main = categoryService.findById(6);
        System.out.println("************** ");

        //از کدوم زیر خدمت؟
        subCategoryService.findSubservienceOfACategory(main.getId()).forEach(System.out::println);
        System.out.println("************** ");

        //کدوم زیر خدمت رو میخوای لیست سفارشاش رو ببینی؟؟
        SubCategory homeSpray = subCategoryService.getById(7);
        orderService.findOrdersOfSubService(homeSpray.getId()).forEach(System.out::println);
        System.out.println("*******************************************888");
        Orders orderByID = orderService.getById(2L);
        Orders orderByIDs = orderService.getById(3L);
        Orders order4 = orderService.getById(4L);


       /* Offer offer = new Offer();
        offer.setExpert(nader);
        Date hours = new SimpleDateFormat("HH:mm").parse("15:30");
        offer.setStartWorkTime(hours);
        offer.setDescription("i can do that perfectly");
        offer.setOrders(orderByID);
        offer.setDuringTime(4.30);
        offer.setProposedPrice(1500000.0);
        offer.setStatus(OfferStatus.SUSPENDED);
        offerService.save(offer);*/

       /* Offer offer = new Offer();
        offer.setExpert(ashkan);
        Date hours = new SimpleDateFormat("HH:mm").parse("12:30");
        offer.setStartWorkTime(hours);
        offer.setDescription("i can ");
        offer.setOrders(orderByID);
        offer.setDuringTime(3.30);
        offer.setProposedPrice(1600000.0);
        offerService.saveOffer(offer,orderByID);*/


/*        temporalValues.setSqlDate(java.sql.Date.valueOf("2017-11-15"));
        temporalValues.setSqlTime(java.sql.Time.valueOf("15:30:14"));
        temporalValues.setSqlTimestamp(
                java.sql.Timestamp.valueOf("2017-11-15 15:30:14.332"));

        temporalValues.setUtilDate(
                new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-15"));
        temporalValues.setUtilTime(
                new SimpleDateFormat("HH:mm:ss").parse("15:30:14"));
        temporalValues.setUtilTimestamp(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                        .parse("2017-11-15 15:30:14.332"));*/


     /*   Offer offer = new Offer();
        offer.setExpert(sara);
        Date hours = new SimpleDateFormat("HH:mm").parse("12:30");
        offer.setStartWorkTime(hours);
        offer.setDescription("i can do in perfectWay");
        offer.setOrders(orderByID);
        offer.setDuringTime(3.15);
        offer.setProposedPrice(1400000.0);
        offerService.save(offer);*/

        /*Offer offer = new Offer();
        offer.setExpert(ashkan);
        Date hours = new SimpleDateFormat("HH:mm").parse("12:30");
        offer.setStartWorkTime(hours);
        offer.setDescription("i can do in perfectWay");
        offer.setOrders(orderByIDs);
        offer.setDuringTime(3.15);
        offer.setProposedPrice(1400000.0);
        offerService.saveOffer(offer, orderByID);

        Offer naderOffer = new Offer();
        naderOffer.setExpert(nader);
        Date hours1 = new SimpleDateFormat("HH:mm").parse("12:30");
        naderOffer.setStartWorkTime(hours1);
        naderOffer.setDescription("i can do in perfectWay");
        naderOffer.setOrders(orderByIDs);
        naderOffer.setDuringTime(3.15);
        naderOffer.setProposedPrice(1700000.0);
        offerService.saveOffer(naderOffer, orderByID);*/


        sara.getSubCategoryList().forEach(System.out::println);

        System.out.println(order4);


        Offer offer = new Offer();
        offer.setExpert(sara);
        Date hours = new SimpleDateFormat("HH:mm").parse("12:30");
        offer.setStartWorkTime(hours);
        offer.setDescription("done in perfectWay");
        offer.setOrders(order4);
        offer.setDuringTime(3.15);
        offer.setProposedPrice(1800000.0);
        offerService.saveOffer(offer, order4);


        /**
         * الان کاستومر میخواد بیاد ببینه چه پیشنهادهایی برای سفارشش ثبت شده
         */
       /* Customer neda = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
        System.out.println(neda);
        //neda.getOrdersList();//این باید فچ تایپش رو تغییر بدم
        orderService.findOrderOfCustomer(neda).forEach(System.out::println);//بدون ترتیب
        System.out.println("which order do you wanna check offers?");
        Orders orderByID1 = orderService.getById(1L);
        //orderByID.getOffers().forEach(System.out::println); باید فچ تایپش رو تغییر بدم
        List<OfferDto> allOfferOfOrder = offerService.findAllOfferOfOrder(orderByID);
        allOfferOfOrder.forEach(System.out::println);

        System.out.println("************* sortByScore ********************88");
        offerService.sortByScore(orderByID1).forEach(System.out::println);
        System.out.println("**************** sortByPrice *****************88");
        offerService.sortByPrice(orderByID1).forEach(System.out::println);*/
/**
 *یکی از اینا رو انتخاب میکنه
 */
       /* Offer offerById = offerService.getById(1L);
        offerById.setStatus(OfferStatus.ACCEPTED);//todo where i must handle this?
        offerService.update(offerById);
        Expert expert = offerById.getExpert();
        orderByID.setExpert(expert);
        orderByID.setAgreedPrice(offerById.getProposedPrice());
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