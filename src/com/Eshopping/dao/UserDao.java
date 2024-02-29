package com.Eshopping.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

/*
 * 用户持久层
 */
public class UserDao extends BaseDao{
	/** 
	 * @param userno 账号
	 * @param password 密码
	 * @return 当前登录用户对象
	 */
	public User login(String userno, String password) {
		//查询数据库，对比账号密码是否一致，一致返回true，否则返回false
		//JDBC---Java数据库连接技术
		//SQl注入攻击
		String sql="SELECT * FROM user_info WHERE userno=? AND password=?";
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
	/**
	 * 查询用户列表
	 * @return 用户集合
	 */
	public List<User> findUserList(User u) {
//		String sql="select * from user_info where userno like '%key%';		
		String sql="select * from user_info u left join dept_info d on u.dept_id=d.id ";
		if(u.getKey()!=null&&!u.getKey().equals("")){//如果是新增
			sql+="where u.userno like '%"+u.getKey()+"%'";
		}
		
		int page=u.getPage();//要查询几页
		int pageSize=u.getPageSize();//每页多少条
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
			List<User> list= new ArrayList<User>();
			System.out.println("··························用户信息··························");
			System.out.println("Id\t性别\t账号\t邮箱\t\t电话\t\t地址\t部们名称");
			while(rs.next()){//如果rs集合中有第一条数据匹配，返回true
				User user = new User();
				int id = rs.getInt("id");//引号里引用的是数据库列名
				String sex_=rs.getString("sex");
				String userno_=rs.getString("userno");
				String password_=rs.getString("password");
				String email=rs.getString("email");
				String phone=rs.getString("phone");
				String address=rs.getString("address");
				String dept_name=rs.getString("dept_name");
	
				user.setId(id);
				user.setSex(sex_);
				user.setUserno(userno_);	
				user.setPassword(password_);
				user.setEmail(email);
				user.setPhone(phone);
				user.setAddress(address);
				user.setDept_name(dept_name);
				
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改密码
	 * @param u
	 * @return
	 */
	public boolean updateUser(User u) {
		String sql="update user_info set userno='"+u.getUserno()+"', password='"+u.getPassword()+"',email='"+u.getEmail()+"',phone='"+u.getPhone()+"',address='"+u.getAddress()+"' where id= '"+u.getId()+"'";
		if(u.getId()==0){//如果是新增
			sql="insert into user_info(userno,password,email,phone,address) values('"+u.getUserno()+"','"+u.getPassword()+"','"+u.getEmail()+"','"+u.getPhone()+"','"+u.getAddress()+"')";
		}
		try {
			//1.获得数据库连接(每一次都要重新链接,)
			this.conn = this.getConn();
			//2.获得ps对象
			this.ps = conn.prepareStatement(sql);
			//3.执行更新命令（修改、新增、删除）
			int result = ps.executeUpdate();//返回受影响的行数
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 删除用户
	 * @param id 用户ID
	 * @return	true 成功；false 失败；
	 */
	public boolean deleteUser(int id) {
		String sql="delete from user_info where id="+id+"";//也可以“where id="+id
		try {
			//1.获得数据库连接(每一次都要重新链接,)
			System.out.println("ji");
			this.conn = this.getConn();
			//2.获得ps对象
			this.ps = conn.prepareStatement(sql);
			//3.执行更新命令（修改、新增、删除）
			int result = ps.executeUpdate();//返回受影响的行数
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean addUser(User u) {
		String sql="insert into user_info(userno, password, sex, email, phone, address) values('"+u.getUserno()+"', '"+u.getPassword()+"', '"+u.getSex()+"', '"+u.getEmail()+"', '"+u.getPhone()+"', '"+u.getAddress()+"')";
		try {
			//1.获得数据库连接(每一次都要重新链接,)
			this.conn = this.getConn();
			//2.获得ps对象
			this.ps = conn.prepareStatement(sql);
			//3.执行更新命令（修改、新增、删除）
			int result = ps.executeUpdate();//返回受影响的行数
			if(result>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public User findUserById(int parseInt) {

		String sql="SELECT * FROM user_info WHERE id=?";
		try {
			Object[] obj=new Object[]{parseInt};
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
	public int findUserCount() {
		String sql="select count(*) as count from user_info";//也可以“where id="+id
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


}
