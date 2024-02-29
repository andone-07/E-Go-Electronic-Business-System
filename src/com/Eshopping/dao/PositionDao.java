package com.Eshopping.dao;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.Eshopping.util.Convert;
import com.Eshopping.util.JSON;
import com.Eshopping.util.SqlHelper;
import com.Eshopping.util.StringUtil;


public class PositionDao{
	
    public ArrayList getList()
    {
        String sql = "select * from t_position";
        return SqlHelper.query(sql, null);      
    }

    public ArrayList getPositionsByDepartmenId(String departmentId)
    {
        String sql = "select * from t_position where dept_id = '" + departmentId + "'";
        return SqlHelper.query(sql, null); 
    }
    
}
