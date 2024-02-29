package com.Eshopping.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Handled;
import model.User;

public class HandledDao extends BaseDao {

	private ResultSet rs;
	public User login(String userno, String password) {
		//查询数据库，对比账号密码是否一致，一致返回true，否则返回false
		//JDBC---Java数据库连接技术
		//SQl注入攻击
		String sql="SELECT * FROM userno_info WHERE userno=? AND password=?"; //后台管理员登录 注意修改BaseDao数据库
		try {
			Object[] obj=new Object[]{userno,password};
			this.rs=this.myQuery(sql,obj);//整合代码
			//4.取结果
			if(rs.next()){//如果rs集合中有第一条数据匹配，返回true
				User user = new User();
				int id = rs.getInt("id");//引号里引用的是数据库列名
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
			//释放数据
			this.closeAll();
		}
		return null;
	}

	public List<Handled> findHandledList(Handled h) {

	    String sql="select * from handled_info";	 //根据订单账号搜索	
		//String sql="select * from order_info u left join dept_info d on u.dept_id=d.id ";
		if(h.getKey()!=null&&!h.getKey().equals("")){
			sql+=" where handled_info.orNum like '%"+h.getKey()+"%'";
		}
		
		int page=h.getPage();//要查询几页
		int pageSize=h.getPageSize();//每页多少条
		int rownum=(page-1)*pageSize;
		
		sql+=" limit "+rownum+","+pageSize+"";
		System.out.println(sql);
		try {
			//1.获得数据库连接(每一次都要重新链接)
			this.conn = this.getConn();
			//2.获得ps对象
			this.ps = conn.prepareStatement(sql);
			//3.执行查询命令
			this.rs = ps.executeQuery();
			//4.取结果
			List<Handled> list= new ArrayList<Handled>();
			
			while(rs.next()){//如果rs集合中有第一条数据匹配，返回true
				Handled handled = new Handled();
				
				int id = rs.getInt("id");//引号里引用的是数据库列名
				int orNum=rs.getInt("orNum");
				
				String orName=rs.getString("orName");
				String orAmount=rs.getString("orAmount");
				String orUserno=rs.getString("orUserno");
				String orCus=rs.getString("orCus");
				String orAddress=rs.getString("orAddress");
				String orPhone=rs.getString("orPhone");
	
				handled.setId(id);
				handled.setOrNum(orNum);
				handled.setOrName(orName);	
				handled.setOrAmount(orAmount);
				handled.setOrUserno(orUserno);
				handled.setOrCus(orCus);
				handled.setOrAddress(orAddress);
				handled.setOrPhone(orPhone);
				
				list.add(handled);
			}
			System.out.println(list);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}

	public int findHandledCount() {

		String sql="select count(*) as count from handled_info";//也可以“where id="+id
		try {
			//1.获得数据库连接(每一次都要重新链接,)
			this.conn = this.getConn();
			//2.获得ps对象
			this.ps = conn.prepareStatement(sql);
			this.rs = ps.executeQuery();
			//3.执行更新命令（修改、新增、删除）
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

	public Handled findHandledById(int parseInt) {

		String sql="SELECT * FROM handled_info WHERE id=?";
		try {
			Object[] obj=new Object[]{parseInt};
			this.rs=this.myQuery(sql, obj);
			//4、取结果；
			if(rs.next()){//如果rs集合中有第一条数据，返回true
				Handled handled=new Handled();
				
				int id = rs.getInt("id");
				int orNum=rs.getInt("orNum");//数据库列名
				String orName=rs.getString("orName");//数据库列名
				String orAmount=rs.getString("orAmount");
				String orCus=rs.getString("orCus");
				String orUserno=rs.getString("orUserno");
				String orAddress=rs.getString("orAddress");
				String orPhone=rs.getString("orPhone");
				
				handled.setId(id);
				handled.setOrNum(orNum);
				handled.setOrName(orName);
				handled.setOrAmount(orAmount);
				handled.setOrCus(orCus);
				handled.setOrUserno(orUserno);
				handled.setOrAddress(orAddress);
				handled.setOrPhone(orPhone);
			
				
				return handled;	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源
			this.closeAll();
		}
		return null;

	
	}

	


}
