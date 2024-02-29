package com.Eshopping.service;

import java.sql.SQLException;
import java.util.List;

import model.Goods;
import model.Category;

import com.Eshopping.dao.GoodsDao;


/*
 * 用户业务层
 */
public class GoodsService {
	
	GoodsDao goodsDao=new GoodsDao();

	/** 
	 * @param userno 账号
	 * @param password 密码
	 * @return 当前用户登录对象
	 */
//	public User login(String userno, String password) {
//		//处理业务能力
//	
//		return goodsDao.login(userno,password);
//	}
	/**
	 * 查询用户列表
	 * @return 用户集合
	 */
	public  List<Goods> findGoodsList(Goods g) {
		return goodsDao.findGoodsList(g);
	}
	/**
	 * 修改用户
	 * @return true成功；false失败
	 */
	public boolean updateGoods(Goods g) {
		return goodsDao.updateGoods(g);
	}
	/**
	 * 删除用户
	 * @param id 用户ID
	 * @return	true 成功；false 失败；
	 */
	public boolean deleteGoods(int id) {		
		return goodsDao.deleteGoods(id);
	}
	/**
	 * 新增用户
	 * @param u
	 * @return
	 */
	public boolean addGoods(Goods g) {
		return goodsDao.addGoods(g);
	}
	/**
	 * 根据id查询用户
	 * @param u
	 * @return
	 */
	public Goods findGoodsById(int parseInt) {
		return goodsDao.findGoodsById(parseInt);
		
	}
	public int findGoodsCount() {
		return goodsDao.findGoodsCount();
	}
	public List<Category> findCategoryList(Category c) {
		return goodsDao.findCategoryList(c);
	}

}
