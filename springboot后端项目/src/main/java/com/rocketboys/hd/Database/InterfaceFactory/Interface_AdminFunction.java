package com.rocketboys.hd.Database.InterfaceFactory;

import java.sql.Timestamp;

public interface Interface_AdminFunction {
    public boolean addUser(String values);
    public boolean alterUser(String usersaccountnumber,String email, String name,String balance);
    public boolean deleteUser(String name);
    public String selectUserByName(String name);
    public String selectUserByAccount(String usersaccountnumber);
    //根据用户账户查询历史订单
    public String selectOrderHistory(String usersaccountnumber);
    public boolean serve(String usersaccountnumber, String mealsname, String paytime);

}
