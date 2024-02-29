package com.Eshopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Order;
import com.Eshopping.service.OrderService;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	
	OrderService orderService=new OrderService();
	
    public OrderServlet() {
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
		if(action!=null&&action.equals("delete")){
			//删除操作
			boolean re=orderService.deleteOrder(Integer.parseInt(id));
			if(re){
				System.out.println("删除成功！");
				//跳转
				requestDispatcherOrder(request,response);
			}
		}else if(action!=null&&action.equals("handling")){
			//订单处理操作
			Order order=orderService.findOrderById(Integer.parseInt(id));
			request.setAttribute("o", order);
			request.getRequestDispatcher("handlingOrder.jsp").forward(request, response);
		
		}else if(action!=null&&action.equals("copyOrder")){
			//订单统计操作（已处理订单数据的复制）
			boolean re=orderService.copyOrder(Integer.parseInt(id));
			if(re){
				System.out.println("该订单已经处理成功！");
				requestDispatcherOrder(request,response);
			}
			
		}else if(action!=null&&action.equals("add")){
			//新增操作
			Order order=new Order();
			List<Order> list=orderService.findOrderList(order);
			request.setAttribute("list", list);
			request.getRequestDispatcher("addorder.jsp").forward(request, response);
			
		}else if(action!=null&&action.equals("toUpdate")){
			//跳转到修改页面操作
			Order order=orderService.findOrderById(Integer.parseInt(id));
			System.out.println("order:"+order);
			request.setAttribute("o", order);
			request.getRequestDispatcher("updateorder.jsp").forward(request, response);
			
		}else if(action!=null&&action.equals("update")){
			//修改操作
			Order o=new Order();
			if(id!=null&&!id.equals("")){
				o.setId(Integer.parseInt(id));
			}
			o.setOrNum(Integer.parseInt(orNum));
			o.setOrName(orName);
			o.setOrAmount(orAmount);	
			o.setOrCus(orCus);
			o.setOrUserno(orUserno);
			o.setOrAddress(orAddress);
			o.setOrPhone(orPhone);
			
			boolean re=orderService.updateOrder(o);
			if(re){
				System.out.println("修改成功");
				//修改成功
				requestDispatcherOrder(request,response);
			}
		}else{
			requestDispatcherOrder(request,response);
		}
	 }

	//跳转到orderlist.jsp的公共方法
	public void requestDispatcherOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//搜索词（根据账号搜索）
		String key=request.getParameter("key")==null?"":request.getParameter("key");
		//要查询第几页
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		
		Order order=new Order();
		order.setKey(key);
		order.setPage(Integer.parseInt(page));
		//搜索操作
		List<Order> list=orderService.findOrderList(order);
		System.out.println(list);
		//计算一共有多少页
		//1.一共有多少条数据
		int count=orderService.findOrderCount();
		//2.计算一共有多少页
		int total=0;//总页数
		if(count%order.getPageSize()==0){
			total=count/order.getPageSize();
		}else{
			total=count/order.getPageSize()+1;
		}
		//request的作用范围，针对某一次请求
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//当前第几页
		request.setAttribute("total", total);//总页数
		request.getRequestDispatcher("orderlist.jsp").forward(request, response);
	}



}
