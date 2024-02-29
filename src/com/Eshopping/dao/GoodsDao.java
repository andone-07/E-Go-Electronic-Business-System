package com.Eshopping.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Eshopping.service.CategoryService;

import model.Category;
import model.Goods;
/*
 * ��Ʒ�־ò� 
 */
import model.User;

public class GoodsDao extends BaseDao{
/*
 * ������Ʒ
 */
	public List<Goods> findGoodsList(Goods g) {

//		String sql="select * from user_info where userno like '%key%';		
		String sql="select * from goods_info g left join category_info c on g.category_id=c.id ";
		if(g.getKey()!=null&&!g.getKey().equals("")){//���������
			sql+="where g.gsname like '%"+g.getKey()+"%'";
		}
		
		int page=g.getPage();//Ҫ��ѯ��ҳ
		int pageSize=g.getPageSize();//ÿҳ������
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
			List<Goods> list= new ArrayList<Goods>();
			while(rs.next()){//���rs�������е�һ������ƥ�䣬����true
				Goods goods = new Goods();
				int id = rs.getInt("id");//���������õ������ݿ�����
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
	 * �޸���Ʒ��Ϣ
	 * @param u
	 * @return
	 */
	public boolean updateGoods(Goods g) {
		String sql="update goods_info set gsname='"+g.getGsname()+"',gsprice='"+g.getGsprice()+"',gsamount='"+g.getGsamount()+"',category_id='"+g.getCategory_id()+"' where id= '"+g.getId()+"'";
		if(g.getId()==0){//���������
			sql="insert into goods_info(gsname,gsprice,gsamount,category_id) values('"+g.getGsname()+"','"+g.getGsprice()+"','"+g.getGsamount()+"','"+g.getCategory_id()+"')";
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
	public boolean deleteGoods(int id) {
		String sql="delete from goods_info where id="+id+"";//Ҳ���ԡ�where id="+id
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
	public boolean addGoods(Goods g) {
		String sql="insert into goods_info(gsname,gsprice,gsamount) values('"+g.getGsname()+"','"+g.getGsprice()+"','"+g.getGsamount()+"')";
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
	public Goods findGoodsById(int parseInt) {

		String sql="SELECT * FROM goods_info g  LEFT JOIN category_info c on g.category_id=c.id WHERE g.id=?";
		try {
			Object[] obj=new Object[]{parseInt};
			this.rs=this.myQuery(sql,obj);//���ϴ���
			//4.ȡ���
			if(rs.next()){//���rs�������е�һ������ƥ�䣬����true
				Goods goods = new Goods();
				int id = rs.getInt("id");//���������õ������ݿ�����
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
			//�ͷ�����
			this.closeAll();
		}
		return null;

	}
	public int findGoodsCount() {
		String sql="select count(*) as count from goods_info";//Ҳ���ԡ�where id="+id
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
	public List<Category> findCategoryList(Category c) {
//		String sql="select * from category_info where id like '%key%';		
				String sql="select * from category_info ";
				
				
				System.out.println(sql);
				try {
					
					//1.������ݿ�����(ÿһ�ζ�Ҫ��������)
					this.conn = this.getConn();
					//2.���ps����
					this.ps = conn.prepareStatement(sql);
					//3.ִ�в�ѯ����
					this.rs = ps.executeQuery();
					//4.ȡ���
					List<Category> list2= new ArrayList<Category>();
					while(rs.next()){//���rs�������е�һ������ƥ�䣬����true
						Category category = new Category();
						int id = rs.getInt("id");//���������õ������ݿ�����
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
