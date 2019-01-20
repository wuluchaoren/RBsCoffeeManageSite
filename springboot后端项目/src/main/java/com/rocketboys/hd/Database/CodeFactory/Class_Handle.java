package com.rocketboys.hd.Database.CodeFactory;

import com.rocketboys.hd.Database.InterfaceFactory.Interface_Handle;
import com.rocketboys.hd.Database.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/*
 * 对数据库的操作
 * 增、删、查、改
 */

public class Class_Handle implements Interface_Handle {
    private Connection connection;
    private String a;

    public Class_Handle(JDBC j){
        connection=j.getConnection();
    }

    public  String select(String table,String column,String where){
        try {
            //mysql查询语句
            String sql = "SELECT " + column + " FROM " + table + " " + where ;
            PreparedStatement prst = connection.prepareStatement(sql);
            //结果集
            ResultSet rs = prst.executeQuery();
            a=new Class_Json().resultSetToJson(rs);
            rs.close();
            prst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public void insert(String table,String values){
        try {
            String sql = "INSERT INTO " + table + " VALUES(" + values + ")";
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean delete(String table, String where){
        try {
            String sql = "UPDATE " + table + " SET isdeleted = '1'" + where;
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.execute();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return select(table,"*",where + " and isdeleted='0'").equals("[]");
    }

    public boolean deletedir(String table,String where){
        try {
            String sql = "DELETE FROM " + table + where;
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
        return select(table,"*",where ).equals("[]");
    }
    
    public void update(String table, String column, String value, String where){
        try{
            String sql = "UPDATE " + table + " SET "+column + "='" + value + "'" + where;
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void selfupdate(String table, String column,String value, String where){
        try{
            String sql = "UPDATE " + table + " SET "+ column + "= " + value + where;
            PreparedStatement prst = connection.prepareStatement(sql);
            prst.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
