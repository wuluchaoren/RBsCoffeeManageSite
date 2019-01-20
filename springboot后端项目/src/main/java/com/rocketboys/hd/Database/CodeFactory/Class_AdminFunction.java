package com.rocketboys.hd.Database.CodeFactory;

import com.rocketboys.hd.Database.InterfaceFactory.Interface_AdminFunction;

import java.sql.Timestamp;

/*
 * 管理员对用户的功能
 * 增加、修改、删除
 * 根据名字查找
 */


public class Class_AdminFunction implements Interface_AdminFunction {
    private Class_Handle classHandle;
    public Class_AdminFunction(Class_Handle h){
        classHandle =h;
    }


    //管理员增加用户
    public boolean addUser(String values){
        classHandle.insert("users",values);
        return true;
    }

    //修改用户信息
    public boolean alterUser(String usersaccountnumber,String email, String name,String balance){
        if(classHandle.select("users","*","where usersaccountnumber="+usersaccountnumber+" and isdeleted='0'").equals("[]")) return false;
        else {
            classHandle.update("users", "usersname", name, " where usersaccountnumber=" + usersaccountnumber);
            classHandle.update("users", "usersemail", email, " where usersaccountnumber=" + usersaccountnumber);
            classHandle.update("users", "usersbalance", balance, " where usersaccountnumber=" + usersaccountnumber);
            return true;
        }
    }

    //根据用户账户删除用户
    public boolean deleteUser(String name){
        classHandle.delete("users","where usersaccount" + "number='" + name + "'");
        return classHandle.select("users","*"," where usersaccountnumber='" + name + "'" + " and isdeleted='0'").equals("[]");
    }

    //根据用户姓名查找
    public String selectUserByName(String usersname){
        return classHandle.select("users","*"," where usersname LIKE "+"'%"+usersname+"%'"+"and isdeleted='0'");
    }

    //根据用户账户查找
    public String selectUserByAccount(String usersaccountnumber){
        return classHandle.select("users","*"," where usersaccountnumber = "+"'"+usersaccountnumber+"'"+"and isdeleted='0'");
    }

    //根据用户账户查询历史订单
    public String selectOrderHistory(String usersaccountnumber){
        if(classHandle.select("orderhistory","*"," where usersaccountnumber='"+usersaccountnumber+"'").equals("[]")) 
            return classHandle.select("orderhistory","*","");
        return classHandle.select("orderhistory","*"," where usersaccountnumber='"+usersaccountnumber+"'");
    }
    
    //根据用户账户和菜品姓名和下单时间上餐
    public boolean serve(String usersaccountnumber, String mealsname, String paytime){
        if(classHandle.select("orderhistory","*"," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"' and paytime='"+paytime+"' and state='N'").equals("[]"))
            return false;
        else {
            classHandle.update("orderhistory","state","Y"," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"' and paytime='"+paytime+"'");
            return true;
        }
    }
    
    //根据用户账户和菜品姓名和下单时间删除订单
    public boolean deleteOrder(String usersaccountnumber, String mealsname, String paytime){
        return classHandle.deletedir("orderhistory"," where usersaccountnumber='"+usersaccountnumber+"' and mealsname='"+mealsname+"' and paytime='"+paytime+"'");
    }
}
