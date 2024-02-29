package com.Eshopping.service;

import java.sql.SQLException;
import java.util.List;

import model.User;

import com.Eshopping.dao.UserDao;

/*
 * 用户业务层
 */
public class UserService {
	
	UserDao userDao=new UserDao();

	/** 
	 * @param userno 账号
	 * @param password 密码
	 * @return 当前用户登录对象
	 */
	public User login(String userno, String password) {
		//处理业务能力
	
		return userDao.login(userno,password);
	}
	/**
	 * 查询用户列表
	 * @return 用户集合
	 */
	public  List<User> findUserList(User user) {
		return userDao.findUserList(user);
	}
	/**
	 * 修改用户
	 * @return true成功；false失败
	 */
	public boolean updateUser(User u) {
		return userDao.updateUser(u);
	}
	/**
	 * 删除用户
	 * @param id 用户ID
	 * @return	true 成功；false 失败；
	 */
	public boolean deleteUser(int id) {		
		return userDao.deleteUser(id);
	}
	/**
	 * 新增用户
	 * @param u
	 * @return
	 */
	public boolean addUser(User u) {
		return userDao.addUser(u);
	}
	/**
	 * 根据id查询用户
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
