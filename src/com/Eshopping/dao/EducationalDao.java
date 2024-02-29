package com.Eshopping.dao;
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.Eshopping.util.Convert;
import com.Eshopping.util.JSON;
import com.Eshopping.util.SqlHelper;
import com.Eshopping.util.StringUtil;


public class EducationalDao{
	
    public ArrayList getList()
    {
        String sql = "select * from t_educational";
        return SqlHelper.query(sql, null);
    }
}
