package com.Eshopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Handled;

import com.Eshopping.service.HandledService;

public class HandledServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;  
	
	HandledService handledService=new HandledService();
	
    public HandledServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request,response);
	}

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");//防止中文乱码
		//搜索词（根据账号搜索） 
		String key=request.getParameter("key")==null?"":request.getParameter("key");
		String action=request.getParameter("action");//操作
		String id=request.getParameter("id");//用户ID
		String orNum=request.getParameter("orNum");
		String orName=request.getParameter("orName");
		String orAmount=request.getParameter("orAmount");
		String orCus=request.getParameter("orCus");
		String orUserno=request.getParameter("orUserno");
		String orAddress=request.getParameter("orAddress");
		String orPhone=request.getParameter("orPhone");

		
		//要查询第几页 
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		requestDispatcherHandled(request,response);	
		
    }
	 

	//跳转到handledOrder.jsp的公共方法
	public void requestDispatcherHandled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//搜索词（根据账号搜索）
		String key=request.getParameter("key")==null?"":request.getParameter("key");
		//要查询第几页
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		
		Handled handled=new Handled();
		handled.setKey(key);
		handled.setPage(Integer.parseInt(page));
		//搜索操作
		List<Handled> list=handledService.findHandledList(handled);
		System.out.println(list);
		//计算一共有多少页
		//1.一共有多少条数据
		int count=handledService.findHandledCount();
		//2.计算一共有多少页
		int total=0;//总页数
		if(count%handled.getPageSize()==0){
			total=count/handled.getPageSize();
		}else{
			total=count/handled.getPageSize()+1;
		}
		//request的作用范围，针对某一次请求
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//当前第几页
		request.setAttribute("total", total);//总页数
		request.getRequestDispatcher("handledOrder.jsp").forward(request, response);
	}





}
