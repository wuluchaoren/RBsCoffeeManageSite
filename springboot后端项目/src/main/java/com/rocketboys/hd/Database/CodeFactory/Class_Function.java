package com.rocketboys.hd.Database.CodeFactory;

import com.rocketboys.hd.Database.InterfaceFactory.Interface_Function;

public class Class_Function implements Interface_Function {
    private Class_Handle classHandle;
    public Class_Function(Class_Handle h){
        classHandle =h;
    }

    public boolean login(String table, String usersaccountnumber ,String password){
        return !classHandle.select(table,"*","where usersaccountnumber='"+usersaccountnumber+"' and userspassword='"+password+"'").equals("[]");
    }

    public boolean register(String usersaccountnumber,String password,String name,String email){
        classHandle.insert("user","'"+usersaccountnumber+"','"+password+"','"+name+"','"+email+"'");
        return !classHandle.select("user","*","where usersaccountnumber='"+usersaccountnumber+"'").equals("[]");
    }

}
