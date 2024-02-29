package com.Eshopping.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;



public class CategoryDao extends BaseDao{
	
	public List<Category> findCategoryList(Category c) {
//		String sql="select * from category_info where userno like '%key%';		
				String sql="select * from category_info";
				if(c.getKey()!=null&&!c.getKey().equals("")){//如果是新增
					sql+="where c.category like '%"+c.getKey()+"%'";
				}
				
				int page=c.getPage();//要查询几页
				int pageSize=c.getPageSize();//每页多少条
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
					List<Category> list= new ArrayList<Category>();
					while(rs.next()){//如果rs集合中有第一条数据匹配，返回true
						Category category_= new Category();
						int id = rs.getInt("id");//引号里引用的是数据库列名
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

		String sql="delete from category_info where id="+id+"";//也可以“where id="+id
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

	public Category findCategoryById(int parseInt) {


		String sql="SELECT * FROM category_info WHERE id=?";
		try {
			Object[] obj=new Object[]{parseInt};
			this.rs=this.myQuery(sql,obj);//整合代码
			//4.取结果
			if(rs.next()){//如果rs集合中有第一条数据匹配，返回true
				Category category = new Category();
				int id = rs.getInt("id");//引号里引用的是数据库列名
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
			//释放数据
			this.closeAll();
		}
		return null;

	
	}

	public int findCategoryCount() {

		String sql="select count(*) as count from category_info";//也可以“where id="+id
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

	public boolean updateCategory(Category c) {

		String sql="update category_info set category='"+c.getCategory()+"',info='"+c.getInfo()+"'";
		if(c.getId()==0){//如果是新增
			sql="insert into category_info(category,info) values('"+c.getCategory()+"','"+c.getInfo()+"')";
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

	

}
