package com.Eshopping.dao;

//import java.sql*可以导入所有sql包里的类型
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
 * 数据库操作公共类
 */
public class BaseDao {
	//属性
	private String url="jdbc:mysql://localhost:3306/eshopping";//数据库连接地址
	private String driver="com.mysql.jdbc.Driver";//数据库入口类
	private String stuName="root";//数据库账号
	private String stuPassword="";//数据库密码
	
	Connection conn;//数据库链接对象
	PreparedStatement ps;//命令执行对象
	ResultSet rs;//储存返回的结果
	
	/**
	 * 创建数据连接，获得连接对象
	 * @return 连接数据
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Connection getConn(){
		//加载驱动
		try {
			Class.forName(driver);//(剩下的10%：类名.方法名)
			return DriverManager.getConnection(url,stuName,stuPassword);
		} catch (Exception e) {  //(任何类型错误都能捕获)
//		} catch (ClassNotFoundException e) {//也可以多个catch捕获多个异常，进行不同的处理，放入不同的文件
			//异常信息写入到外部文件里Excrptionlog.txt
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Connection conn=new BaseDao().getConn();	
		System.out.println(conn);
	}
	/**
	 * 释放资源
	 */
	public void closeAll(){
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(ps!=null){
				ps.close();
				ps=null;
			}
			if(conn!=null){
				conn.close();
				conn=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 公共查询方法
	 */
	public ResultSet myQuery(String sql,Object[] obj){
		try {
			//1.获得数据库连接(每一次都要重新链接)
			this.conn = this.getConn();
			//2.获得ps对象
			this.ps = conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			//3.执行查询命令
			this.rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
