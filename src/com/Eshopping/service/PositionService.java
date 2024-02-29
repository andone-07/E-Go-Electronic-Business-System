package com.Eshopping.service;

import java.util.ArrayList;

import com.Eshopping.dao.PositionDao;

public class PositionService {

    PositionDao dao = new PositionDao();

    public ArrayList getList()
    {
        return dao.getList();
    }
 
    public ArrayList getPositionsByDepartmenId(String departmentId)
    {
        return dao.getPositionsByDepartmenId(departmentId);
    }
    
}
