package com.rocketboys.hd.Controller;

import com.rocketboys.hd.Database.CodeFactory.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.*;
import com.rocketboys.hd.Database.*;


@RestController
public class Controller {
    private JDBC jdbc=new JDBC();
    private Class_Handle classHandle =new Class_Handle(jdbc);
    private Class_Function classFunction =new Class_Function(classHandle);
    private Class_AdminFunction classAdminFunction =new Class_AdminFunction(classHandle);
    private Class_UserFunction classUserFunction=new Class_UserFunction(classHandle);
    private Class_MenuFunction classMenuFunction=new Class_MenuFunction(classHandle);


    //用户注册
    @CrossOrigin
    @RequestMapping(value="/userRegister", method=RequestMethod.GET)
    @ResponseBody
    public boolean UserRegister(String accountnumber,String password,String name,String email){
        return classFunction.register(accountnumber,password,name,email);
    }



    //用户登陆
    @CrossOrigin
    @RequestMapping(value="/userLogin", method=RequestMethod.GET)
    @ResponseBody
    public boolean UserLogin(String username,String password){
        //提取前端传来的具体参数
        return classFunction.login("users",username,password);
    }

    //管理员登陆
    @CrossOrigin
    @RequestMapping(value="/adminLogin", method=RequestMethod.GET)
    @ResponseBody
    public boolean AdminLogin(String username,String password){
        //提取前端传来的具体参数
        return classFunction.login("administrators",username,password);
    }





    //显示所有用户
    @CrossOrigin
    @RequestMapping(value="/allusers",method = RequestMethod.GET)
    @ResponseBody
    public String allUsers(){
        return classHandle.select("users","*","where isdeleted = 0");
    }

    //管理员删除用户
    @CrossOrigin
    @RequestMapping(value="/deleteUser",method = RequestMethod.GET)
    @ResponseBody
    public boolean DeleteUsers(String userAccount){
        System.out.println(userAccount);
        return classAdminFunction.deleteUser(userAccount);
    }

    //管理员增加用户
    @CrossOrigin
    @RequestMapping(value="/addUser",method = RequestMethod.GET)
    @ResponseBody
    public boolean AddUsers(String usersaccountnumber,String  userspassword,String usersname, String usersemail,String usersbalance){
        return classAdminFunction.addUser(usersaccountnumber+","+userspassword+","+usersname+","+usersemail+","+usersbalance+",default");
    }

    //管理员编辑用户
    @CrossOrigin
    @RequestMapping(value="/alterUserByAdmin",method = RequestMethod.GET)
    @ResponseBody
    public boolean Alter_Users(String usersaccountnumber,String email, String name,String balance){
        return classAdminFunction.alterUser(usersaccountnumber,email,name,balance);
    }

    //管理员根据名字搜索用户
    @CrossOrigin
    @RequestMapping(value="/selectUserByName",method = RequestMethod.GET)
    @ResponseBody
    public String SelectUser (String name){
        return classAdminFunction.selectUserByName(name);
    }

    //管理员根据账号搜索用户
    @CrossOrigin
    @RequestMapping(value="/selectUserByAccount",method = RequestMethod.GET)
    @ResponseBody
    public String SelectUserByAccount (String accountnumber){
        return classAdminFunction.selectUserByAccount(accountnumber);
    }




    //用户编辑个人信息
    @CrossOrigin
    @RequestMapping(value="/alterUser",method = RequestMethod.GET)
    @ResponseBody
    public boolean AlterUsers(String usersaccountnumber, String name){
        return classUserFunction.alterUser(usersaccountnumber,name);
    }

    //用户修改密码
    @CrossOrigin
    @RequestMapping(value="/alterUserPassword",method = RequestMethod.GET)
    @ResponseBody
    public boolean AlterUserPassword(String usersaccountnumber,String oldpassword,String newpassword){
        return classUserFunction.alterPassword(usersaccountnumber,oldpassword,newpassword);
    }



    //所有菜品
    @CrossOrigin
    @RequestMapping(value="/allMenu",method = RequestMethod.GET)
    @ResponseBody
    public String AllMenu(){
        return classHandle.select("meals","mealsname,mealstype,mealsprice,purchasefrequency","where isdeleted =0");
    }

    //根据关键字筛选菜单
    @CrossOrigin
    @RequestMapping(value="/keywordSelect",method = RequestMethod.GET)
    @ResponseBody
    public String KeywordSelect(String keyword){
        return classMenuFunction.keywordSelectMenu(keyword);
    }

    //根据种类筛选菜单
    @CrossOrigin
    @RequestMapping(value="/typeSelect",method = RequestMethod.GET)
    @ResponseBody
    public String TypeSelect(String type){
        return classMenuFunction.typeSelectMenu(type);
    }

    //管理员根据名字修改菜品
    @CrossOrigin
    @RequestMapping(value="/alterMenu",method = RequestMethod.GET)
    @ResponseBody
    public boolean AlterMenu(String mealsname,String mealstype,String mealsprice){
        return classMenuFunction.alterMenu(mealsname,mealstype,mealsprice);
    }

    //管理员根据名字删除菜品
    @CrossOrigin
    @RequestMapping(value="/deleteMenu",method = RequestMethod.GET)
    @ResponseBody
    public boolean DeleteMenu(String mealsname){
        return classMenuFunction.deleteMenu(mealsname);
    }

    //管理员新增菜品
    @CrossOrigin
    @RequestMapping(value="/addMenu",method = RequestMethod.GET)
    @ResponseBody
    public boolean AddMenu(String mealsname,String mealsprice,String mealstype,String mealsstork){
        return classMenuFunction.addMenu(mealsname,mealsprice,mealstype,mealsstork);
    }


    //根据分类筛选菜品
    @CrossOrigin
    @RequestMapping(value="/allCoffee",method = RequestMethod.GET)
    @ResponseBody
    public String showCoffee(){
        return classMenuFunction.typeSelectMenu("莱茵河畔");
    }

    @CrossOrigin
    @RequestMapping(value="/allTea",method = RequestMethod.GET)
    @ResponseBody
    public String showTea(){
        return classMenuFunction.typeSelectMenu("栈语茗茶");
    }

    @CrossOrigin
    @RequestMapping(value="/allSoup",method = RequestMethod.GET)
    @ResponseBody
    public String showSoup(){
        return classMenuFunction.typeSelectMenu("浓情蜜意");
    }

    @CrossOrigin
    @RequestMapping(value="/allIce",method = RequestMethod.GET)
    @ResponseBody
    public String showIce() {
        return classMenuFunction.typeSelectMenu("甜在心扉");
    }





    //根据用户账户返回用户购物车内容
    @CrossOrigin
    @RequestMapping(value="/allShoppingTrolley",method = RequestMethod.GET)
    @ResponseBody
    public String AllShoppingTrolley(String usersaccountnumber){
        return classHandle.select("shoppingtrolley","mealsname,amount,addtime","where usersaccountnumber='"+usersaccountnumber+"'");
    }

    //加入购物车
    @CrossOrigin
    @RequestMapping(value="/addShoppingTrolley",method = RequestMethod.GET)
    @ResponseBody
    public boolean AddShoppingTrolley(String usersaccountnumber,String mealsname,int amount){
        return classUserFunction.addShoppingTrolley(usersaccountnumber,mealsname,amount);
    }

    //修改购物车商品数量
    @CrossOrigin
    @RequestMapping(value="/alterShoppingTrolley",method = RequestMethod.GET)
    @ResponseBody
    public boolean AlterShoppingTrolley(String usersaccountnumber,String mealsname,int amount){
       return classUserFunction.alterShoppingTrolley(usersaccountnumber,mealsname,amount);
    }

    //删除购物车商品
    @CrossOrigin
    @RequestMapping(value="/dropShoppingTrolley",method = RequestMethod.GET)
    @ResponseBody
    public boolean DropShoppingTrolley(String usersaccountnumber,String mealsname){
        return classUserFunction.dropShoppingTrolley(usersaccountnumber,mealsname);
    }

    //清空购物车商品
    @CrossOrigin
    @RequestMapping(value="/emptyShoppingTrolley",method = RequestMethod.GET)
    @ResponseBody
    public boolean EmptyShoppingTrolley(String usersaccountnumber){
        return classUserFunction.emptyShoppingTrolley(usersaccountnumber);
    }

    //提交订单
    @CrossOrigin
    @RequestMapping(value="/submitToOrder",method = RequestMethod.GET)
    @ResponseBody
    public boolean SubmitToOrder(String usersaccountnumber,String mealsname,int amount){
        return classUserFunction.submitToOrder(usersaccountnumber,mealsname,amount);
    }



    //返回所有历史订单(前端请按时间排序)
    //其中属性列state:N代表未上餐 Y代表已上餐
    @CrossOrigin
    @RequestMapping(value="/allOrderHistory",method = RequestMethod.GET)
    @ResponseBody
    public String AllOrderHistory(){
        return classHandle.select("orderhistory","*","");
    }

    //根据用户账户返回该用户的历史订单
    @CrossOrigin
    @RequestMapping(value="/userOrderHistory1",method = RequestMethod.GET)
    @ResponseBody
    public String UserOrderHistory1(String usersaccountnumber){
        System.out.println(usersaccountnumber);
        return classAdminFunction.selectOrderHistory(usersaccountnumber);
    }


    @CrossOrigin
    @RequestMapping(value="/userOrderHistory2",method = RequestMethod.GET)
    @ResponseBody
    public String UserOrderHistory2(String usersaccountnumber){
        System.out.println(usersaccountnumber);
        return classUserFunction.selectOrderHistory(usersaccountnumber);
    }
    
    
    //根据用户账户返回该用户未上餐的历史订单
    @CrossOrigin
    @RequestMapping(value="/userOrderHistoryN",method = RequestMethod.GET)
    @ResponseBody
    public String UserOrderHistoryN(String usersaccountnumber){
        System.out.println(usersaccountnumber+12);
        return classUserFunction.selectOrderHistoryN(usersaccountnumber);
    }

    //根据用户账户返回该用户的已上餐的历史订单
    @CrossOrigin
    @RequestMapping(value="/userOrderHistoryY",method = RequestMethod.GET)
    @ResponseBody
    public String UserOrderHistoryY(String usersaccountnumber){
        System.out.println(usersaccountnumber+123);
        return classUserFunction.selectOrderHistoryY(usersaccountnumber);
    }
    
    //管理员确认上餐
    @CrossOrigin
    @RequestMapping(value="/serve",method = RequestMethod.GET)
    @ResponseBody
    public Boolean Serve(String usersaccountnumber,String mealsname, String paytime){
        return classAdminFunction.serve(usersaccountnumber,mealsname,paytime);
    }
    
    //管理员删除订单
    @CrossOrigin
    @RequestMapping(value="/deleteOrder",method = RequestMethod.GET)
    @ResponseBody
    public Boolean DeleteOrder(String usersaccountnumber,String mealsname, String paytime){
        return classAdminFunction.deleteOrder(usersaccountnumber,mealsname,paytime);
    }

}
