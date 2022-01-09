package ir.maktab.homeServiceProvider.data.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MainServices {
    BUILDING_DECORATION(1,"building decoration"),//دکوراسیون ساختمان
    BUILDING_FACILITIES(2,"building facilities"),//تاسیسات ساختمان
    VEHICLES(3,"vehicles"),//وسایل نقلیه
    MOVING_HELP(4,"moving help"),//اسباب کشی و باربری
    HOME_APPLIANCES(5,"home appliances"),//لوازم خانگی
    HOME_CLEANING_AND_HYGIENE(6,"home cleaning and hygiene");//نظافت و بهداشت
    int id;
    String name;

}
