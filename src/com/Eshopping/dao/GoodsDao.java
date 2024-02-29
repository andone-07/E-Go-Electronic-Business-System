package com.Eshopping.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Eshopping.service.CategoryService;

import model.Category;
import model.Goods;
/*
 * 商品持久层 
 */
import model.User;

public class GoodsDao extends BaseDao{
/*
 * 查找商品
 */
	public List<Goods> findGoodsList(Goods g) {

//		String sql="select * from user_info where userno like '%key%';		
		String sql="select * from goods_info g left join category_info c on g.category_id=c.id ";
		if(g.getKey()!=null&&!g.getKey().equals("")){//如果是新增
			sql+="where g.gsname like '%"+g.getKey()+"%'";
		}
		
		int page=g.getPage();//要查询几页
		int pageSize=g.getPageSize();//每页多少条
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
			List<Goods> list= new ArrayList<Goods>();
			while(rs.next()){//如果rs集合中有第一条数据匹配，返回true
				Goods goods = new Goods();
				int id = rs.getInt("id");//引号里引用的是数据库列名
				String gsname=rs.getString("gsname");
				String gsprice=rs.getString("gsprice");
				String gsamount=rs.getString("gsamount");
				String category=rs.getString("category");
	
				goods.setId(id);
				goods.setGsname(gsname);
				goods.setGsprice(gsprice);
				goods.setGsamount(gsamount);
				goods.setCategory(category);
				
				list.add(goods);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	/**
	 * 修改商品信息
	 * @param u
	 * @return
	 */
	public boolean updateGoods(Goods g) {
		String sql="update goods_info set gsname='"+g.getGsname()+"',gsprice='"+g.getGsprice()+"',gsamount='"+g.getGsamount()+"',category_id='"+g.getCategory_id()+"' where id= '"+g.getId()+"'";
		if(g.getId()==0){//如果是新增
			sql="insert into goods_info(gsname,gsprice,gsamount,category_id) values('"+g.getGsname()+"','"+g.getGsprice()+"','"+g.getGsamount()+"','"+g.getCategory_id()+"')";
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
	public boolean deleteGoods(int id) {
		String sql="delete from goods_info where id="+id+"";//也可以“where id="+id
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
	public boolean addGoods(Goods g) {
		String sql="insert into goods_info(gsname,gsprice,gsamount) values('"+g.getGsname()+"','"+g.getGsprice()+"','"+g.getGsamount()+"')";
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
	public Goods findGoodsById(int parseInt) {

		String sql="SELECT * FROM goods_info g  LEFT JOIN category_info c on g.category_id=c.id WHERE g.id=?";
		try {
			Object[] obj=new Object[]{parseInt};
			this.rs=this.myQuery(sql,obj);//整合代码
			//4.取结果
			if(rs.next()){//如果rs集合中有第一条数据匹配，返回true
				Goods goods = new Goods();
				int id = rs.getInt("id");//引号里引用的是数据库列名
				String gsname=rs.getString("gsname");
				String gsprice=rs.getString("gsprice");
				String gsamount=rs.getString("gsamount");
				String category=rs.getString("category");
				String category_id=rs.getString("category_id");
	
				goods.setId(id);
				goods.setGsname(gsname);
				goods.setGsprice(gsprice);
				goods.setGsamount(gsamount);
				goods.setCategory(category);
				goods.setCategory_id(category_id);
				
				return goods;
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
	public int findGoodsCount() {
		String sql="select count(*) as count from goods_info";//也可以“where id="+id
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
	public List<Category> findCategoryList(Category c) {
//		String sql="select * from category_info where id like '%key%';		
				String sql="select * from category_info ";
				
				
				System.out.println(sql);
				try {
					
					//1.获得数据库连接(每一次都要重新链接)
					this.conn = this.getConn();
					//2.获得ps对象
					this.ps = conn.prepareStatement(sql);
					//3.执行查询命令
					this.rs = ps.executeQuery();
					//4.取结果
					List<Category> list2= new ArrayList<Category>();
					while(rs.next()){//如果rs集合中有第一条数据匹配，返回true
						Category category = new Category();
						int id = rs.getInt("id");//引号里引用的是数据库列名
						String category_=rs.getString("category");
			
						category.setId(id);
						category.setCategory(category_);
						
						list2.add(category);
					}
					return list2;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
	}
	
	

}
