package com.Eshopping.service;

import java.sql.SQLException;
import java.util.List;

import model.User;

import com.Eshopping.dao.UserDao;

/*
 * �û�ҵ���
 */
public class UserService {
	
	UserDao userDao=new UserDao();

	/** 
	 * @param userno �˺�
	 * @param password ����
	 * @return ��ǰ�û���¼����
	 */
	public User login(String userno, String password) {
		//����ҵ������
	
		return userDao.login(userno,password);
	}
	/**
	 * ��ѯ�û��б�
	 * @return �û�����
	 */
	public  List<User> findUserList(User user) {
		return userDao.findUserList(user);
	}
	/**
	 * �޸��û�
	 * @return true�ɹ���falseʧ��
	 */
	public boolean updateUser(User u) {
		return userDao.updateUser(u);
	}
	/**
	 * ɾ���û�
	 * @param id �û�ID
	 * @return	true �ɹ���false ʧ�ܣ�
	 */
	public boolean deleteUser(int id) {		
		return userDao.deleteUser(id);
	}
	/**
	 * �����û�
	 * @param u
	 * @return
	 */
	public boolean addUser(User u) {
		return userDao.addUser(u);
	}
	/**
	 * ����id��ѯ�û�
	 * @param u
	 * @return
	 */
	public User findUserById(int parseInt) {
		return userDao.findUserById(parseInt);
		
	}
	public int findUserCount() {
		return userDao.findUserCount();
	}

}
