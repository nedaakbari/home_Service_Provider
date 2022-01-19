package ir.maktab.homeServiceProvider.view;

import ir.maktab.homeServiceProvider.config.SpringConfig;
import ir.maktab.homeServiceProvider.data.model.entity.Person.Expert;
import ir.maktab.homeServiceProvider.data.model.entity.service.Category;
import ir.maktab.homeServiceProvider.data.model.entity.service.SubCategory;
import ir.maktab.homeServiceProvider.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Set;

public class AddSubservice {
    public static void main(String[] args) {
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

        ////اضافه کردن اکسپرت به زیرتخصص ها
        Expert nader = expertService.findExpertByUseAndPass("nader_", "nader_22");
        Expert ashkan = expertService.findExpertByUseAndPass("ashkan_", "ashkan@7");
        Expert sara = expertService.findExpertByUseAndPass("sara@", "sara_222");
        Expert sana = expertService.findExpertByUseAndPass("sana_", "sana@747");

        System.out.println(nader);
        System.out.println(ashkan);
        System.out.println(sara);
        System.out.println(sana);

        SubCategory kitchenAppliances = subCategoryService.findByTitle("kitchenAppliances ".trim());
        SubCategory laundry = subCategoryService.getById(2);
        SubCategory spray = subCategoryService.getById(7);
        System.out.println(laundry);
        System.out.println(kitchenAppliances);
        System.out.println(spray);


        //1 اول متخصص وارد مین سرویس میشه
        Category home_appliances1 = categoryService.findByTitle("HOME_APPLIANCES");
        System.out.println(home_appliances1);

        //میخواد همه زیر خدمت های این خدمت اصلی رو ببینه
        //home_appliances.getSubServiceList().forEach(System.out::println);//این شکلی باید فچ تایپش رو ایگر کنی
        subCategoryService.findSubservienceOfACategory(home_appliances1.getId()).forEach(System.out::println);
        System.out.println("***********************************8888");

        //انتخاب کنه
        SubCategory laundrys = subCategoryService.getById(2);
        System.out.println(laundrys);
        System.out.println("**************** subServiceOfExpert *******************8888");

      /*  expertService.addSubCategoryToExpertList(nader,laundry);
        expertService.addSubCategoryToExpertList(ashkan,laundry);
        expertService.addSubCategoryToExpertList(sara,laundry);
        expertService.addSubCategoryToExpertList(sana,laundry);

        expertService.addSubCategoryToExpertList(nader,spray);
        expertService.addSubCategoryToExpertList(ashkan,spray);
        expertService.addSubCategoryToExpertList(sara,spray);*/
        //expertService.addSubCategoryToExpertList(sara,kitchenAppliances);

///////////////////////////////////////////////////////////////////////حذف از زیرخدمت

          expertService.removeSubCategoryFromExpertList(sana,laundry);

        /////////////////////////////////////////////////////////////////////////////////////

//پیدا کردن زیر خدمت های یک اکسپرت
        sara.getSubCategoryList().forEach(System.out::println);

//پیدا کردن اکسپرت های  یک زیرخدمت
      // expertService.findExpertsOfASubCategory(2).forEach(System.out::println);


        /////////////////////////////////////////////////////////////////////////////////////







       /* //naders.getSubCategoryList().forEach(System.out::println);
        System.out.println("************** expertsOfASubCategory *********************8888");
        List<Expert> expertsOfASubCategory = expertService.findExpertsOfASubCategory(laundry.getId());
        System.out.println(expertsOfASubCategory);*/



        //اضافه کردن ساب سرویس به اکسپرت


         /*subCategoryService.addExpertToSubCategory(sara,laundry);=>exception
        subCategoryService.removeExpertFromCategory(nader,laundry);==>collection was evicted; nested exception is org.hibernate.HibernateException: collection was evicted

*/

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



    }
}
