package com.Eshopping.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.User;

public class OrderDao extends BaseDao{
	private ResultSet rs;
	public User login(String userno, String password) {
		//��ѯ���ݿ⣬�Ա��˺������Ƿ�һ�£�һ�·���true�����򷵻�false
		//JDBC---Java���ݿ����Ӽ���
		//SQlע�빥��
		String sql="SELECT * FROM userno_info WHERE userno=? AND password=?"; //��̨����Ա��¼ ע���޸�BaseDao���ݿ�
		try {
			Object[] obj=new Object[]{userno,password};
			this.rs=this.myQuery(sql,obj);//���ϴ���
			//4.ȡ���
			if(rs.next()){//���rs�������е�һ������ƥ�䣬����true
				User user = new User();
				int id = rs.getInt("id");//���������õ������ݿ�����
				String sex_=rs.getString("sex");
				String userno_=rs.getString("userno");
				String password_=rs.getString("password");;
				String email=rs.getString("email");;
				String phone=rs.getString("phone");;
				String address=rs.getString("address");;
	
				user.setId(id);
				user.setSex(sex_);
				user.setUserno(userno_);	
				user.setPassword(password_);
				user.setEmail(email);
				user.setPhone(phone);
				user.setAddress(address);
				
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ͷ�����
			this.closeAll();
		}
		return null;
	}

	public List<Order> findOrderList(Order o) {

	    String sql="select * from order_info";	 //���ݶ����˺�����	
		//String sql="select * from order_info u left join dept_info d on u.dept_id=d.id ";
		if(o.getKey()!=null&&!o.getKey().equals("")){
			sql+=" where order_info.orNum like '%"+o.getKey()+"%'";
		}
		
		int page=o.getPage();//Ҫ��ѯ��ҳ
		int pageSize=o.getPageSize();//ÿҳ������
		int rownum=(page-1)*pageSize;
		
		sql+=" limit "+rownum+","+pageSize+"";
		System.out.println(sql);
		try {
			//1.������ݿ�����(ÿһ�ζ�Ҫ��������)
			this.conn = this.getConn();
			//2.���ps����
			this.ps = conn.prepareStatement(sql);
			//3.ִ�в�ѯ����
			this.rs = ps.executeQuery();
			//4.ȡ���
			List<Order> list= new ArrayList<Order>();
			
			while(rs.next()){//���rs�������е�һ������ƥ�䣬����true
				Order order = new Order();
				
				int id = rs.getInt("id");//���������õ������ݿ�����
				int orNum=rs.getInt("orNum");
				
				String orName=rs.getString("orName");
				String orAmount=rs.getString("orAmount");
				String orUserno=rs.getString("orUserno");
				String orCus=rs.getString("orCus");
				String orAddress=rs.getString("orAddress");
				String orPhone=rs.getString("orPhone");
	
				order.setId(id);
				order.setOrNum(orNum);
				order.setOrName(orName);	
				order.setOrAmount(orAmount);
				order.setOrUserno(orUserno);
				order.setOrCus(orCus);
				order.setOrAddress(orAddress);
				order.setOrPhone(orPhone);
				
				list.add(order);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public int findOrderCount() {

		String sql="select count(*) as count from order_info";//Ҳ���ԡ�where id="+id
		try {
			//1.������ݿ�����(ÿһ�ζ�Ҫ��������,)
			this.conn = this.getConn();
			//2.���ps����
			this.ps = conn.prepareStatement(sql);
			this.rs = ps.executeQuery();
			//3.ִ�и�������޸ġ�������ɾ����
			if(rs.next()){
				int count = rs.getInt("count");
				return count;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeAll();
		}
		
		return 0;
	}

	public Order findOrderById(int parseInt) {

		String sql="SELECT * FROM order_info WHERE id=?";
		try {
			Object[] obj=new Object[]{parseInt};
			this.rs=this.myQuery(sql, obj);
			//4��ȡ�����
			if(rs.next()){//���rs�������е�һ�����ݣ�����true
				Order order=new Order();
				
				int id = rs.getInt("id");
				int orNum=rs.getInt("orNum");//���ݿ�����
				String orName=rs.getString("orName");//���ݿ�����
				String orAmount=rs.getString("orAmount");
				String orCus=rs.getString("orCus");
				String orUserno=rs.getString("orUserno");
				String orAddress=rs.getString("orAddress");
				String orPhone=rs.getString("orPhone");
				
				order.setId(id);
				order.setOrNum(orNum);
				order.setOrName(orName);
				order.setOrAmount(orAmount);
				order.setOrCus(orCus);
				order.setOrUserno(orUserno);
				order.setOrAddress(orAddress);
				order.setOrPhone(orPhone);
			
				
				return order;	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ͷ���Դ
			this.closeAll();
		}
		return null;

	
	}

	public boolean deleteOrder(int id) {
		
		String sql="delete from order_info where id="+id;
		try {
			//1��������ݿ����ӣ�ÿһ�ζ�Ҫ�������ӣ�
			this.conn=this.getConn();
			//2�����ps����
			this.ps=conn.prepareStatement(sql);
			//3��ִ�в�ѯ����޸ġ�������ɾ����
			int result=ps.executeUpdate();//�������ݿ���Ӱ�������
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	
	}
	public boolean addOrder(Order o) {
		
		String sql="insert into order_info(orNum, orName, orAmount, orCus , orUserno, orAddress, orPhone) values('"+o.getOrNum()+"', '"+o.getOrName()+"', '"+o.getOrAmount()+"', '"+o.getOrCus()+"', '"+o.getOrUserno()+"', '"+o.getOrAddress()+"', '"+o.getOrPhone()+"')";
		try {
			//1.������ݿ�����(ÿһ�ζ�Ҫ��������,)
			this.conn = this.getConn();
			//2.���ps����
			this.ps = conn.prepareStatement(sql);
			//3.ִ�и�������޸ġ�������ɾ����
			int result = ps.executeUpdate();//������Ӱ�������
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateOrder(Order o) {
		String sql="update order_info set orNum='"+o.getOrNum()+"', orName='"+o.getOrName()+"',orAmount='"+o.getOrAmount()+"',orCus='"+o.getOrCus()+"',orUserno='"+o.getOrUserno()+"',orAddress='"+o.getOrAddress()+"',orPhone='"+o.getOrPhone()+"' where id="+o.getId()+"";
		if(o.getId()==0){//���������
			sql="insert into order_info(orNum,orName,orAmount,orCus,orUserno,orAddress,orPhone) values('"+o.getOrNum()+"','"+o.getOrName()+"','"+o.getOrAmount()+"','"+o.getOrCus()+"','"+o.getOrUserno()+"','"+o.getOrAddress()+"','"+o.getOrPhone()+"')";		
		}
		try {
			//1��������ݿ����ӣ�ÿһ�ζ�Ҫ�������ӣ�
			this.conn=this.getConn();
			//2�����ps����
			this.ps=conn.prepareStatement(sql);
			//3��ִ�в�ѯ����޸ġ�������ɾ����
			int result=ps.executeUpdate();
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return false;
	}

	public boolean copyOrder(int id) {
		
		String sql="insert into handled_info select * from order_info where id="+id;
		try {
			//1.������ݿ�����(ÿһ�ζ�Ҫ��������,)
			this.conn = this.getConn();
			//2.���ps����
			this.ps = conn.prepareStatement(sql);
			//3.ִ�и�������޸ġ�������ɾ����
			int result = ps.executeUpdate();//������Ӱ�������
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	
	}
	
}
