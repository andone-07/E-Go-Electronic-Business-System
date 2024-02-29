package com.Eshopping.service;

import java.sql.SQLException;
import java.util.List;

import model.Goods;
import model.Category;

import com.Eshopping.dao.GoodsDao;


/*
 * �û�ҵ���
 */
public class GoodsService {
	
	GoodsDao goodsDao=new GoodsDao();

	/** 
	 * @param userno �˺�
	 * @param password ����
	 * @return ��ǰ�û���¼����
	 */
//	public User login(String userno, String password) {
//		//����ҵ������
//	
//		return goodsDao.login(userno,password);
//	}
	/**
	 * ��ѯ�û��б�
	 * @return �û�����
	 */
	public  List<Goods> findGoodsList(Goods g) {
		return goodsDao.findGoodsList(g);
	}
	/**
	 * �޸��û�
	 * @return true�ɹ���falseʧ��
	 */
	public boolean updateGoods(Goods g) {
		return goodsDao.updateGoods(g);
	}
	/**
	 * ɾ���û�
	 * @param id �û�ID
	 * @return	true �ɹ���false ʧ�ܣ�
	 */
	public boolean deleteGoods(int id) {		
		return goodsDao.deleteGoods(id);
	}
	/**
	 * �����û�
	 * @param u
	 * @return
	 */
	public boolean addGoods(Goods g) {
		return goodsDao.addGoods(g);
	}
	/**
	 * ����id��ѯ�û�
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
