package com.Eshopping.service;

import java.util.List;

import jdk.internal.org.objectweb.asm.Handle;
import model.Handled;
import model.User;

import com.Eshopping.dao.HandledDao;
import com.Eshopping.dao.UserDao;

public class HandledService {

	 HandledDao handledDao=new HandledDao();//�ŵ�����������
	 UserDao userDao=new UserDao();
	 public User login(String userno,String password){
			//����ҵ���߼�
			//������ܵ�	 
		return userDao.login(userno, password);
	}

	public int findHandledCount() {
		// TODO Auto-generated method stub
		return handledDao.findHandledCount();
	}
    public Handled findHandledById(int parseInt) {
		
		return handledDao.findHandledById(parseInt);
	}
    public List<Handled> findHandledList(Handled handled) {
	    // TODO Auto-generated method stub
	    return handledDao.findHandledList(handled);
}
   
}
