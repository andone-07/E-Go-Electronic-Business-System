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
 * 接受用户登陆
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//类的构建方法
    public LoginServlet() {
        super();
       
    }
    //接受get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	//接受post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");//中文编码
		String userno=request.getParameter("userno");
		String password=request.getParameter("password");
		
		String key="";//搜索关键词
		
		UserService userService=new UserService();
		//true:成功；false:失败
		User user=userService.login(userno,password);
		if(user!=null){
			System.out.println("登录成功！");
			User u=new User();
			u.setKey(key);
			List<User> list=userService.findUserList(u);
			//计算一共有多少页
			//1.一共共有多少条数
			int count=userService.findUserCount();
			//2.计算一共有多少页
			int total=0;//总页数
			if(count%u.getPageSize()==0){	
				total=count/u.getPageSize();
			}else{
				total=count/u.getPageSize()+1;
			}
			
			//将用户列表数据放入到request对象中（放行李）
			request.setAttribute("list", list);
//			request.setAttribute("user", user);
			//Session对象作用域：针对同一个浏览器，同1个浏览器，不同窗口之间共享数据
			request.getSession().setAttribute("user", user);
			request.setAttribute("page",1);
			request.setAttribute("total",total);
			//进入首页(转发)
			request.getRequestDispatcher("userlist.jsp").forward(request,response);
			//重定向
			//response.sendRedirect("http://www.baidu.com");
		}else{
			System.out.println("登录失败！");
		}
	}

}

