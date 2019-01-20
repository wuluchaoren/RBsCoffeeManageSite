package com.rocketboys.hd.Database.InterfaceFactory;

import org.json.JSONException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Interface_Json {
    public String resultSetToJson(ResultSet rs) throws SQLException,JSONException;
}
