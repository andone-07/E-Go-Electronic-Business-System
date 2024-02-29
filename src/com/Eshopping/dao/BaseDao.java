package com.Eshopping.dao;

//import java.sql*���Ե�������sql���������
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*
 * ���ݿ����������
 */
public class BaseDao {
	//����
	private String url="jdbc:mysql://localhost:3306/eshopping";//���ݿ����ӵ�ַ
	private String driver="com.mysql.jdbc.Driver";//���ݿ������
	private String stuName="root";//���ݿ��˺�
	private String stuPassword="";//���ݿ�����
	
	Connection conn;//���ݿ����Ӷ���
	PreparedStatement ps;//����ִ�ж���
	ResultSet rs;//���淵�صĽ��
	
	/**
	 * �����������ӣ�������Ӷ���
	 * @return ��������
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Connection getConn(){
		//��������
		try {
			Class.forName(driver);//(ʣ�µ�10%������.������)
			return DriverManager.getConnection(url,stuName,stuPassword);
		} catch (Exception e) {  //(�κ����ʹ����ܲ���)
//		} catch (ClassNotFoundException e) {//Ҳ���Զ��catch�������쳣�����в�ͬ�Ĵ������벻ͬ���ļ�
			//�쳣��Ϣд�뵽�ⲿ�ļ���Excrptionlog.txt
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Connection conn=new BaseDao().getConn();	
		System.out.println(conn);
	}
	/**
	 * �ͷ���Դ
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
	 * ������ѯ����
	 */
	public ResultSet myQuery(String sql,Object[] obj){
		try {
			//1.������ݿ�����(ÿһ�ζ�Ҫ��������)
			this.conn = this.getConn();
			//2.���ps����
			this.ps = conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++){
				ps.setObject(i+1, obj[i]);
			}
			//3.ִ�в�ѯ����
			this.rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
