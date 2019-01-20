package com.rocketboys.hd.Database.CodeFactory;

import com.rocketboys.hd.Database.InterfaceFactory.Interface_MenuFunction;

public class Class_MenuFunction implements Interface_MenuFunction {
    private Class_Handle classHandle;
    public Class_MenuFunction(Class_Handle h){
        classHandle =h;
    }

    //添加菜品
    public boolean addMenu(String mealsname,String mealsprice,String mealstype,String mealsstock) {
        if(!classHandle.select("meals", "mealsname", "where mealsname='" + mealsname + "' and isdeleted='1'").equals("[]")){
            classHandle.update("meals","isdeleted","0","where mealsname='"+mealsname+"'");
            alterMenu(mealsname,mealstype,mealsprice);
            classHandle.update("meals","mealsstock",mealsstock,"where mealsname='"+mealsname+"'");
            return true;
        }
        else if (!classHandle.select("meals", "mealsname", "where mealsname='" + mealsname + "' and isdeleted='0'").equals("[]"))
            return false;
        else {
            classHandle.insert("meals", "'"+mealsname + "','" + mealstype + "','" + mealsprice + "','" + mealsstock + "',default,default,'楞子'");
            return true;
        }
    }

    //修改菜品
    public boolean alterMenu(String mealsname,String mealstype,String mealsprice) {
        if( classHandle.select("meals","*","where mealsname='"+mealsname+"' and isdeleted='0'").equals("[]"))
            return false;
        else
        {
            classHandle.update("meals","mealsname" ,mealsname,"where mealsname='"+mealsname+"'");
            classHandle.update("meals","mealstype" ,mealstype,"where mealsname='"+mealsname+"'");
            classHandle.update("meals","mealsprice" ,mealsprice,"where mealsname='"+mealsname+"'");
            return true;
        }
    }

    //删除菜品
    public boolean deleteMenu(String mealsname) {
        classHandle.delete("meals", "where mealsname='" + mealsname+"'");
        return classHandle.select("meals","mealsname","where mealsname='"+mealsname+"'"+ " and isdeleted='0'").equals("[]");
    }


    //根据关键字筛选菜品
    public String keywordSelectMenu(String mealsname) {
        return classHandle.select("meals","mealsname,mealstype,mealsprice,purchasefrequency","where mealsname LIKE "+"'%"+mealsname+"%' and isdeleted='0'");
    }

    //根据类型筛选菜品
    public String typeSelectMenu(String mealstype) {
        return classHandle.select("meals","mealsname,mealstype,mealsprice,purchasefrequency","where mealstype='" + mealstype + "' and isdeleted='0'");
    }
}
