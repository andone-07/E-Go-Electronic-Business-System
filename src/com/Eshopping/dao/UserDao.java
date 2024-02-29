package com.Eshopping.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

/*
 * �û��־ò�
 */
public class UserDao extends BaseDao{
	/** 
	 * @param userno �˺�
	 * @param password ����
	 * @return ��ǰ��¼�û�����
	 */
	public User login(String userno, String password) {
		//��ѯ���ݿ⣬�Ա��˺������Ƿ�һ�£�һ�·���true�����򷵻�false
		//JDBC---Java���ݿ����Ӽ���
		//SQlע�빥��
		String sql="SELECT * FROM user_info WHERE userno=? AND password=?";
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
	/**
	 * ��ѯ�û��б�
	 * @return �û�����
	 */
	public List<User> findUserList(User u) {
//		String sql="select * from user_info where userno like '%key%';		
		String sql="select * from user_info u left join dept_info d on u.dept_id=d.id ";
		if(u.getKey()!=null&&!u.getKey().equals("")){//���������
			sql+="where u.userno like '%"+u.getKey()+"%'";
		}
		
		int page=u.getPage();//Ҫ��ѯ��ҳ
		int pageSize=u.getPageSize();//ÿҳ������
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
			List<User> list= new ArrayList<User>();
			System.out.println("�����������������������������������������������������û���Ϣ����������������������������������������������������");
			System.out.println("Id\t�Ա�\t�˺�\t����\t\t�绰\t\t��ַ\t��������");
			while(rs.next()){//���rs�������е�һ������ƥ�䣬����true
				User user = new User();
				int id = rs.getInt("id");//���������õ������ݿ�����
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
	 * �޸�����
	 * @param u
	 * @return
	 */
	public boolean updateUser(User u) {
		String sql="update user_info set userno='"+u.getUserno()+"', password='"+u.getPassword()+"',email='"+u.getEmail()+"',phone='"+u.getPhone()+"',address='"+u.getAddress()+"' where id= '"+u.getId()+"'";
		if(u.getId()==0){//���������
			sql="insert into user_info(userno,password,email,phone,address) values('"+u.getUserno()+"','"+u.getPassword()+"','"+u.getEmail()+"','"+u.getPhone()+"','"+u.getAddress()+"')";
		}
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
	/**
	 * ɾ���û�
	 * @param id �û�ID
	 * @return	true �ɹ���false ʧ�ܣ�
	 */
	public boolean deleteUser(int id) {
		String sql="delete from user_info where id="+id+"";//Ҳ���ԡ�where id="+id
		try {
			//1.������ݿ�����(ÿһ�ζ�Ҫ��������,)
			System.out.println("ji");
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
	public boolean addUser(User u) {
		String sql="insert into user_info(userno, password, sex, email, phone, address) values('"+u.getUserno()+"', '"+u.getPassword()+"', '"+u.getSex()+"', '"+u.getEmail()+"', '"+u.getPhone()+"', '"+u.getAddress()+"')";
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
	public User findUserById(int parseInt) {

		String sql="SELECT * FROM user_info WHERE id=?";
		try {
			Object[] obj=new Object[]{parseInt};
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
	public int findUserCount() {
		String sql="select count(*) as count from user_info";//Ҳ���ԡ�where id="+id
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


}
