package com.rocketboys.hd.Database.InterfaceFactory;

public interface Interface_Handle {
    public String select(String table, String column, String where);
    public void insert(String table, String values);
    public boolean delete(String table, String where);
    public void update(String table, String column, String vlaue, String where);
    public boolean deletedir(String table,String where);
}
