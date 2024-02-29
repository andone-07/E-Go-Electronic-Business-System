package com.Eshopping.view;

import java.util.List;
import java.util.Scanner;

import model.User;

import com.Eshopping.service.UserService;


public class Menu {
	Scanner sc =new Scanner(System.in);
	UserService userservice=new UserService();
	/*
	 * 用户登录
	 */
	public void loginView(){
		System.out.println("·········欢迎进入电商后台登陆系统·········");
		System.out.println("··············1.登录··············");
		System.out.println("··············2.退出··············");
		System.out.println("请选择：");
		int result=sc.nextInt();//获取控制台用户输入的整数（对象名.方法名）
		int num=3;
		if(result==1){
			//用户登录
			System.out.println("请输入账号：");
			String userno=sc.next();//获取所输入的字符串（账号）
			System.out.println("请输入密码：");
			String password=sc.next();//获取所输入的字符串（密码）
			
			UserService userService=new UserService();
			//true:成功；false:失败；
			User user=userService.login(userno,password);
			if(user!=null){
				System.out.println("登录成功！");
				//进入首页
				indexView(user);
			}else{
				while(num>0){
					System.out.println("账号或密码错误，请重试！你还有"+num+"次机会");
					num--;
					System.out.println("请输入账号：");
					String userno_=sc.next();//获取所输入的字符串（账号）
					System.out.println("请输入密码：");
					String password_=sc.next();//获取所输入的字符串（密码）
					
					UserService userService_=new UserService();
					//true:成功；false:失败；
					User user_=userService.login(userno_,password_);
					if(user_!=null){
						System.out.println("登录成功！");
						//进入首页
						indexView(user_);
						break;
					}else if(num==0){
						System.out.println("系统已退出");
						break;
					}
				}
				
			}
			

		}else if(result==2){
			//用户退出
			System.out.println("退出成功，祝您生活愉快");
		}else{
			System.out.println("选择无效，请重选");
			loginView();
		}
		
	}
	/*
	 * 首页
	 */
	public void indexView(User user){
		System.out.println("··········欢迎"+user.getUserno()+"来到电商后台系统············");
		System.out.println("············1.用户管理···············");
		System.out.println("················1.1、用户查看·········");
		System.out.println("················1.2、用户修改·········");
		System.out.println("················1.3、用户删除·········");
		System.out.println("················1.4、用户新增·········");
		System.out.println("············2.商品管理················");
		System.out.println("············3.订单管理················");
		System.out.println("············4.退出··················");
		System.out.println("请选择要进行的操作：");
		
		String result=sc.next();
		if(result.equals("1.1")){
			//用户查看
			//List Map
			System.out.println("是否要关键词检索信息（Y/N）？");
			String YorN=sc.next();
			String key="";
			if(YorN.equals("Y")||YorN.equals("y")){
				System.out.println("请输入关键词：");
				key=sc.next();
			}
			User u2=new User();
			u2.setKey(key);
			List<User> list=userservice.findUserList(u2);
			for(User u:list){
				System.out.println(u.getId()+"\t"+u.getSex()+"\t"+u.getUserno()+"\t"+u.getEmail()+"\t"+u.getPhone()+"\t"+u.getAddress()+"\t"+u.getDept_name());
			}
		
		}else if(result.equals("1.2")){
			//用户修改（修改密码）
			System.out.println("请输入要修改密码的账号ID：");
			int id=sc.nextInt();
			System.out.println("请输入要修改密码");
			String password=sc.next();
			
			User u=new User();
			u.setId(id);
			u.setPassword(password);
			
			boolean re=userservice.updateUser(u);
			if(re==true){
				System.out.println("修改成功！");
			}else{
				System.out.println("修改失败！");
			}
			
		}else if(result.equals("1.3")){
			//用户删除
			System.out.println("请输入要删除的用户ID：");
			int id=sc.nextInt();
			boolean re=userservice.deleteUser(id);
			if(re){
				System.out.println("删除成功!");
			}else{
				System.out.println("删除失败!");
			}
		}else if(result.equals("1.4")){
			//用户新增(账号、密码、性别、邮箱、电话、地址、部门)
			System.out.println("请输入新增用户账号：");
			String userno=sc.next();
			System.out.println("请输入新增用户密码：");//可以写一个用户密码验证
			String password=sc.next();
			System.out.println("请输入新增用户性别：");
			String sex=sc.next();
			System.out.println("请输入新增用户邮箱：");
			String email=sc.next();
			System.out.println("请输入新增用户电话：");
			String phone=sc.next();
			System.out.println("请输入新增用户地址：");
			String address=sc.next();
			System.out.println("请输入新增用户部门：");
			String dept_name=sc.next();
			
			User u=new User();
			u.setUserno(userno);
			u.setPassword(password);
			u.setSex(sex);
			u.setEmail(email);
			u.setPhone(phone);
			u.setAddress(address);
			u.setDept_name(dept_name);
			
			boolean re=userservice.addUser(u);
			if(re==true){
				System.out.println("新增用户成功！");
			}else{
				System.out.println("新增用户失败！");
			}
			
		}else if(result.equals("4")){
			//用户退出			
		}else{
			System.out.println("选择无效");
		}
		
		System.out.println("是否进行其他操作（Y/N）：");
		String reLogin=sc.next();
		if(reLogin.equals("Y")||reLogin.equals("y")){
			
			indexView(user);	
		}else{
			System.out.println("退出成功，祝您生活愉快！");
		}
	}
	
	public static void main(String[] args) {
		//创建对象
		Menu menu=new Menu();
		//对象名.方法名(90%以上的情况)
		menu.loginView();
//		new Menu().loginView();
		
		
	}
}
