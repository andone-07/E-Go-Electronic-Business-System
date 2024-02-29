	package com.Eshopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import com.Eshopping.service.UserService;

/**
 * 接收用户相关的业务请求
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService=new UserService();
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");//中文库
		
		
		String action=request.getParameter("action");//操作
		String id=request.getParameter("id");//用户Id
		String userno=request.getParameter("userno");//用户名
		String password=request.getParameter("password");//用户密码
		String phone=request.getParameter("phone");//用户电话
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		
		if(action!=null&&action.equals("delete")){
			//删除操作
			boolean re=userService.deleteUser(Integer.parseInt(id));
			if(re){
				System.out.println("删除成功！");
				//跳转
				requestDispatcherUser(request, response);
			}else{
				System.out.println("删除失败！");
			}
		}else if(action!=null&&action.equals("add")){
			//新增操作
			User user=new User();
			List<User> list=userService.findUserList(user);
			request.setAttribute("list", list);
			request.getRequestDispatcher("useradd.jsp").forward(request,response);
			
		}else if(action!=null&&action.equals("toUpdate")){
			//跳转到修改页面操作
			User user=userService.findUserById(Integer.parseInt(id));
			request.setAttribute("u",user);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			
			
		}else if(action!=null&&action.equals("update")){
			//修改操作
			User u=new User();
			if(id!=null&&!id.equals("")){//修改才会赋值
				u.setId(Integer.parseInt(id));
			}
			u.setUserno(userno);
			u.setPassword(password);
			u.setEmail(email);
			u.setAddress(address);
			u.setPhone(phone);
			
			boolean re=userService.updateUser(u);
			if(re){
				System.out.println("修改成功");
				//跳转
				requestDispatcherUser(request, response);
			}
		}else{
			//搜索操作
			requestDispatcherUser(request, response);
		}
		
		

	}
	//跳转到userlist.jsp的公共方法
	public void requestDispatcherUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		String key=request.getParameter("key")==null?"":request.getParameter("key");//搜索词(根据账号搜索)
		
		User user=new User();
		user.setKey(key);
		user.setPage(Integer.parseInt(page));
		//搜索操作
		List<User> list=userService.findUserList(user);
		//计算一共有多少页
		//1.一共有多少条数据
		int count=userService.findUserCount();
		//2.计算一共有多少页
		int total=0;//总页数
		if(count%user.getPageSize()==0){	
			total=count/user.getPageSize();
		}else{
			total=count/user.getPageSize()+1;
		}
		//request数据作用范围：针对某一次请求
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//当前第几页
		request.setAttribute("total", total);//总页数
		request.getRequestDispatcher("userlist.jsp").forward(request,response);
	}
}
