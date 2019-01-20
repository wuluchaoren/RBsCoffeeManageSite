package com.rocketboys.hd.Database.InterfaceFactory;

public interface Interface_UserFunction {
    public boolean alterUser(String usersaccountnumber, String name);
    //加入购物车
    public boolean addShoppingTrolley(String usersaccountnumber,String mealsname,int amount);
    //修改购物车商品数量
    public boolean alterShoppingTrolley(String usersaccountnumber,String mealsname,int amount);
    //删除购物车商品
    public boolean dropShoppingTrolley(String usersaccountnumber,String mealsname);
    //清空购物车商品
    public boolean emptyShoppingTrolley(String usersaccountnumber);
    //提交订单，库存数量修改
    public boolean submitToOrder(String usersaccountnumber,String mealsname,int amount);
    public boolean alterPassword(String usersaccountnumber,String oldpassword,String newpassword);
    public String selectOrderHistory(String usersaccountnumber);
    public String selectOrderHistoryN(String usersaccountnumber);
    public String selectOrderHistoryY(String usersaccountnumber);
}
