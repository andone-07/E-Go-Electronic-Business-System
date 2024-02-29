package com.Eshopping.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;



public class CategoryDao extends BaseDao{
	
	public List<Category> findCategoryList(Category c) {
//		String sql="select * from category_info where userno like '%key%';		
				String sql="select * from category_info";
				if(c.getKey()!=null&&!c.getKey().equals("")){//���������
					sql+="where c.category like '%"+c.getKey()+"%'";
				}
				
				int page=c.getPage();//Ҫ��ѯ��ҳ
				int pageSize=c.getPageSize();//ÿҳ������
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
					List<Category> list= new ArrayList<Category>();
					while(rs.next()){//���rs�������е�һ������ƥ�䣬����true
						Category category_= new Category();
						int id = rs.getInt("id");//���������õ������ݿ�����
						String category=rs.getString("category");
						String info=rs.getString("info");
			
						category_.setId(id);
						category_.setCategory(category);
						category_.setCategory(category);
						
						list.add(category_);
					}
					return list;
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				return null;
	}
	
	public boolean deleteCategory(int id) {

		String sql="delete from category_info where id="+id+"";//Ҳ���ԡ�where id="+id
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

	public Category findCategoryById(int parseInt) {


		String sql="SELECT * FROM category_info WHERE id=?";
		try {
			Object[] obj=new Object[]{parseInt};
			this.rs=this.myQuery(sql,obj);//���ϴ���
			//4.ȡ���
			if(rs.next()){//���rs�������е�һ������ƥ�䣬����true
				Category category = new Category();
				int id = rs.getInt("id");//���������õ������ݿ�����
				String category_=rs.getString("category");
				String info=rs.getString("info");
	
				category.setId(id);
				category.setCategory(category_);
				category.setInfo(info);
				
				return category;
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

	public int findCategoryCount() {

		String sql="select count(*) as count from category_info";//Ҳ���ԡ�where id="+id
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

	public boolean updateCategory(Category c) {

		String sql="update category_info set category='"+c.getCategory()+"',info='"+c.getInfo()+"'";
		if(c.getId()==0){//���������
			sql="insert into category_info(category,info) values('"+c.getCategory()+"','"+c.getInfo()+"')";
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

	

}
