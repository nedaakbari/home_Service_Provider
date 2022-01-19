package ir.maktab.homeServiceProvider.view;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;


public class Insert {
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







        //به اکسپرت ها اضافه میشه

        System.out.println("***********************************8888");
     /*   expertService.addSubCategoryToExpertList(nader,laundry);
        expertService.addSubCategoryToExpertList(sara,laundry);
        expertService.addSubCategoryToExpertList(ashkan,laundry);
        expertService.addSubCategoryToExpertList(nader,spray);
        expertService.addSubCategoryToExpertList(sana,spray);


        Set<SubCategory> naderList = naders.getSubCategoryList();
        naderList.add(laundrys);
        naders.setSubCategoryList(naderList);
        expertService.updateExpert(naders);

        System.out.println(naders.getSubCategoryList());

        Set<SubCategory> saraList = sara_222.getSubCategoryList();
        saraList.add(laundrys);
        sara_222.setSubCategoryList(saraList);
        expertService.updateExpert(sara_222);

//اضافه کردن ساب سرویس به اکسپرت












        expertService.addSubCategoryToExpertList(nader,laundry);

        List<Expert> expertsOfASubCategory1 = expertService.findExpertsOfASubCategory(laundry.getId());

        Set<Expert> experts = new HashSet<>();
        for (Expert e : expertsOfASubCategory1) {
            experts.add(e);
        }
        expertsOfASubCategory1.add(nader);
        laundry.setExperts(experts);

        subCategoryService.update(laundry);

         subCategoryService.addExpertToSubCategory(nader,laundry);
         subCategoryService.addExpertToSubCategory(sara,laundry);=>exception
        subCategoryService.removeExpertFromCategory(nader,laundry);==>collection was evicted; nested exception is org.hibernate.HibernateException: collection was evicted




        Category home_cleaning_and_hygiene = categoryService.findByTitle("HOME_CLEANING_AND_HYGIENE");
        //میخواد همه زیر خدمت های این خدمت اصلی رو ببینه
        subCategoryService.findSubservienceOfACategory(home_cleaning_and_hygiene.getId()).forEach(System.out::println);
         //انتخاب کنه
        //SubService byName = serviceDao.findByName("kitchenAppliances ".trim());
        SubCategory spray = subCategoryService.getById(7);
        System.out.println(spray);
        //به اکسپرت ها اضافه میشه
        Set<Expert> experts = spray.getExperts();
        experts.add( ashkan);
        experts.add(nader);
        spray.setExperts(experts);
        subCategoryService.updateSubService(spray);*/

        //sara=> subservice2 => laundry
        //nader=> subservice2 => laundry  subService7 =>spray
        //ashkan =>subService7 =>spray


        //میخوام ببینم نادر چه خدمت هایی داره
        //nader.getSubServiceList().forEach(System.out::println);//no session نمیخوام ایگر کنمش
        // subCategoryService.findSubServiceOfExpert(nader.getId()).forEach(System.out::println);
/*
        List<Expert> expertsOfASubCategory = expertService.findExpertsOfASubCategory(laundry.getId());
        System.out.println(expertsOfASubCategory);*/

        //subCategoryService.findSubServiceOfExpert(nader.getId());
        //میخوام ببینم سارا چه خدمت هایی داره
        // subCategoryService.findSubserivceOfExpert(sara.getId()).forEach(System.out::println);

        //میخوام ببینم چه اکسپرت هایی داخل laundry خدمت میکنن
        /*Mapper mapper=new Mapper();//todo qualifier error
        SubService laundry = subCategoryService.findByName("homeSpraying");
        Set<Expert> experts = laundry.getExperts();
        List<ExpertDto> collect = experts.stream().map(mapper::expertDto).collect(Collectors.toList());
        collect.forEach(System.out::println);*/

        //میخوام نادر رو از لاندی حذف کنم
       /* experts.remove(nader);
        laundry.setExperts(experts);
        subCategoryService.updateSubService(laundry);*/

        //جستجو حوزه تخصصی متخصصان
        /*Expert nader = expertService.findByEmail("nader@gmail.com");
        subCategoryService.findSubServiceOfExpert(nader.getId()).forEach(System.out::println);*/


        /**
         * کاستومر لیست خدمات رو ببینه و سفارش بده
         */
/*        Customer neda = customerService.findCustomerByUseAndPass("neda_ak", "Neda@222");
//اول کدوم خدمت ؟
        Category byId = categoryService.findById(6);
        System.out.println("******** " + byId);
        //کدوم زیر خدمت رو از این خدمت میخوای؟
        subCategoryService.findSubservienceOfACategory(byId.getId()).forEach(System.out::println);
        System.out.println("******** " + byId);
        //یکی از زیر خدمت ها رو انتخاب کنه
        SubCategory homeSpraying = subCategoryService.getById(7);
        System.out.println("******** " + homeSpraying);

        Address address = new Address();
        address.setCity("shiraz");
        address.setStreet("engheleb");
        address.setZipCode("4785p");

        Orders orders = new Orders();
        orders.setCustomer(neda);
        orders.setProposePrice(110000.0);
        orders.setSubCategory(homeSpraying);
        orders.setDescription("i want done this very well");
        orders.setAddress(address);
        Date date = DateUtil.convertStringToDate("2022-01-09");
        orders.setDoWorkDate(date);
        orderService.save(orders);*/
/*
        Orders orders2 = new Orders();
        orders2.setCustomer(neda);
        orders2.setProposePrice(150000.0);
        orders2.setSubCategory(laundry);
        orders2.setDescription("i want done this very well");
        orders2.setAddress(address);
        Date date = DateUtil.convertStringToDate("2022-01-09");
        orders2.setDoWorkDate(date);
        orderService.save(orders2);*/

/**
 *متخصص بره لیست سفارش ها رو ببینه و اگر توی تخصصش بود بتونه پیشنهاد بده و کمتر از پایه هم پیشنهاد نده
 */
//از این خدمات کدومش؟
     /*   categoryService.getAll().forEach(System.out::println);
        Category main = categoryService.findById(5);
        System.out.println("************** ");
        //از کدوم خدمت؟
        subCategoryService.findSubservienceOfACategory(main.getId()).forEach(System.out::println);
        System.out.println("************** ");
        //کدوم زیر خدمت رو میخوای لیست سفارشاش رو ببینی؟؟
        SubCategory homeSpray = subCategoryService.getById(7);
        orderService.findOrdersOfSubService(homeSpray.getId()).forEach(System.out::println);
        System.out.println("*******************************************888");
        Orders orderByID = orderService.getById(1L);
*/

     /*   Offer offer = new Offer();
        offer.setExpert(nader);
        Date hours = new SimpleDateFormat("HH:mm").parse("15:30");
        offer.setStartWorkTime(hours);
        offer.setDescription("i can do that perfectly");
        offer.setOrders(orderByID);
        offer.setDuringTime(4.30);
        offer.setProposedPrice(1500000.0);
        offer.setStatus(OfferStatus.SUSPENDED);
        offerService.save(offer);*/

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
        offer.setStatus(OfferStatus.SUSPENDED);
        offerService.save(offer);*/


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