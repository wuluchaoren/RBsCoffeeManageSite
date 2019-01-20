package com.rocketboys.hd.Database.CodeFactory;

import com.rocketboys.hd.Database.InterfaceFactory.Interface_UserFunction;

public class Class_UserFunction implements Interface_UserFunction {

    private Class_Handle classHandle;
    public Class_UserFunction(Class_Handle handle){
        classHandle=handle;
    }

    //用户修改个人信息
    public boolean alterUser(String usersaccountnumber, String name){
        if(classHandle.select("users","usersname"," where usersaccountnumber='"+usersaccountnumber+"' and isdeleted='0'").equals("[]")) return false;
        else {
            classHandle.update("users", "usersname", name, " where usersaccountnumber='" + usersaccountnumber+"'");
            return true;
        }
    }

    //用户修改密码
    public boolean alterPassword(String usersaccountnumber,String oldpassword,String newpassword){
        if(classHandle.select("users","*"," where usersaccountnumber='"+usersaccountnumber+"' and userspassword='"+oldpassword+"'").equals("[]")) return false;
        else {
            classHandle.update("users","userspassword",newpassword," where usersaccountnumber='"+usersaccountnumber+"'");
            return true;
        }
    }
    
    
    //加入购物车
    public boolean addShoppingTrolley(String usersaccountnumber,String mealsname,int amount){
        if(classHandle.select("shoppingtrolley","*"," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"'").equals("[]")) {
            classHandle.insert("shoppingtrolley", "'" + usersaccountnumber + "','" + mealsname + "','" + amount + "',default");
        }
        else {
            classHandle.selfupdate("shoppingtrolley","amount","amount+"+amount," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"'");
        }
        return !classHandle.select("shoppingtrolley","*"," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"'").equals("[]");
    }

    //修改购物车商品数量
    public boolean alterShoppingTrolley(String usersaccountnumber,String mealsname,int amount){
        classHandle.update("shoppingtrolley","amount",amount+""," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"'");
        return !classHandle.select("shoppingtrolley","*"," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"' and amount='"+(amount+"")+"'").equals("[]");
    }

    //删除购物车商品
    public boolean dropShoppingTrolley(String usersaccountnumber,String mealsname){
        return classHandle.deletedir("shoppingtrolley"," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"'");
    }

    //清空购物车商品
    public boolean emptyShoppingTrolley(String usersaccountnumber){
        return classHandle.deletedir("shoppingtrolley"," where usersaccountnumber='"+usersaccountnumber+"'");
    }

    //返回历史订单
    public String selectOrderHistory(String usersaccountnumber){
        return classHandle.select("orderhistory","*"," where usersaccountnumber='"+usersaccountnumber+"'");
    }


    //根据用户账户查询未上菜历史订单
    public String selectOrderHistoryN(String usersaccountnumber){
        return classHandle.select("orderhistory","*"," where usersaccountnumber='"+usersaccountnumber+"' and state='N'");
    }

    //根据用户账户查询已上菜历史订单
    public String selectOrderHistoryY(String usersaccountnumber){
        return classHandle.select("orderhistory","*"," where usersaccountnumber='"+usersaccountnumber+"' and state='Y'");
    }
    
    
    
    //提交订单，库存数量减少
    //删除购物车表 插入历史订单表
    public boolean submitToOrder(String usersaccountnumber,String mealsname,int amount){
        classHandle.selfupdate("meals",",mealsstock","mealsstock-"+amount," where mealsname='"+mealsname+"'");
        dropShoppingTrolley(usersaccountnumber,mealsname);
        String values="select usersaccountnumber,mealsname,amount,addtime from shoppingtrolley where usersaccountnumber='"+usersaccountnumber+"' and mealsname='" + mealsname + "'";
        classHandle.insert("orderhistory",values);
        //b1应该为假
        boolean b1=classHandle.select("meals","*", " where mealsname='"+mealsname+"' and mealsstock=mealsstock-"+amount).equals("[]");
        //b2应该为真
        boolean b2=classHandle.select("shoppingtrolley","*"," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"'").equals("[]");
        return b2&&!b1;
    }
    
    
}
