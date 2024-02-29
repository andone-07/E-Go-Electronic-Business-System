package com.Eshopping.service;

import java.util.ArrayList;

import com.Eshopping.dao.EducationalDao;

public class EducationalService {

    EducationalDao dao = new EducationalDao();

    public ArrayList getList()
    {
        return dao.getList();
    }
    
}
