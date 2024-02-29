package com.Eshopping.service;

import java.util.List;
import com.Eshopping.dao.OrderDao;
import com.Eshopping.dao.UserDao;
import model.Order;
import model.User;

public class OrderService {
	 OrderDao orderDao=new OrderDao();//放到方法的外面
	 UserDao userDao=new UserDao();
	 public User login(String userno,String password){
			//处理业务逻辑
			//密码加密等	 
		return userDao.login(userno, password);
	}
	public List<Order> findOrderList(Order order) {
		// TODO Auto-generated method stub
		return orderDao.findOrderList(order);
	}

	public int findOrderCount() {
		// TODO Auto-generated method stub
		return orderDao.findOrderCount();
	}
    public Order findOrderById(int parseInt) {
		
		return orderDao.findOrderById(parseInt);
	}

	public  boolean deleteOrder(int id) {
		// TODO Auto-generated method stub
		return orderDao.deleteOrder(id);
	}
	public boolean addOrder(Order o) {
		return orderDao.addOrder(o);
	}

	public boolean updateOrder(Order o) {
		// TODO Auto-generated method stub
		return orderDao.updateOrder(o);
	}
	public boolean copyOrder(int id) {
		// TODO Auto-generated method stub
		return orderDao.copyOrder(id);
	}
	

}
