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

	public List<Handled> findHandledList(Handled h) {

	    String sql="select * from handled_info";	 //���ݶ����˺�����	
		//String sql="select * from order_info u left join dept_info d on u.dept_id=d.id ";
		if(h.getKey()!=null&&!h.getKey().equals("")){
			sql+=" where handled_info.orNum like '%"+h.getKey()+"%'";
		}
		
		int page=h.getPage();//Ҫ��ѯ��ҳ
		int pageSize=h.getPageSize();//ÿҳ������
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
			List<Handled> list= new ArrayList<Handled>();
			
			while(rs.next()){//���rs�������е�һ������ƥ�䣬����true
				Handled handled = new Handled();
				
				int id = rs.getInt("id");//���������õ������ݿ�����
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

		String sql="select count(*) as count from handled_info";//Ҳ���ԡ�where id="+id
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

	public Handled findHandledById(int parseInt) {

		String sql="SELECT * FROM handled_info WHERE id=?";
		try {
			Object[] obj=new Object[]{parseInt};
			this.rs=this.myQuery(sql, obj);
			//4��ȡ�����
			if(rs.next()){//���rs�������е�һ�����ݣ�����true
				Handled handled=new Handled();
				
				int id = rs.getInt("id");
				int orNum=rs.getInt("orNum");//���ݿ�����
				String orName=rs.getString("orName");//���ݿ�����
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
			//�ͷ���Դ
			this.closeAll();
		}
		return null;

	
	}

	


}
