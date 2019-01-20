package com.rocketboys.hd.Database.InterfaceFactory;

public interface Interface_MenuFunction {
    public boolean addMenu(String mealsname, String mealsprice, String mealstype, String mealsstork);
    public boolean alterMenu(String mealsname,String mealstype,String mealsprice);
    public boolean deleteMenu(String mealsname);
    public String keywordSelectMenu(String mealsname);
    public String typeSelectMenu(String mealstype);
}
