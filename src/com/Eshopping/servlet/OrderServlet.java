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
		request.setCharacterEncoding("UTF8");//��ֹ��������
		//�����ʣ������˺������� 
		String key=request.getParameter("key")==null?"":request.getParameter("key");
		String action=request.getParameter("action");//����
		String id=request.getParameter("id");//�û�ID
		String orNum=request.getParameter("orNum");
		String orName=request.getParameter("orName");
		String orAmount=request.getParameter("orAmount");
		String orCus=request.getParameter("orCus");
		String orUserno=request.getParameter("orUserno");
		String orAddress=request.getParameter("orAddress");
		String orPhone=request.getParameter("orPhone");

		
		//Ҫ��ѯ�ڼ�ҳ 
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		if(action!=null&&action.equals("delete")){
			//ɾ������
			boolean re=orderService.deleteOrder(Integer.parseInt(id));
			if(re){
				System.out.println("ɾ���ɹ���");
				//��ת
				requestDispatcherOrder(request,response);
			}
		}else if(action!=null&&action.equals("handling")){
			//�����������
			Order order=orderService.findOrderById(Integer.parseInt(id));
			request.setAttribute("o", order);
			request.getRequestDispatcher("handlingOrder.jsp").forward(request, response);
		
		}else if(action!=null&&action.equals("copyOrder")){
			//����ͳ�Ʋ������Ѵ��������ݵĸ��ƣ�
			boolean re=orderService.copyOrder(Integer.parseInt(id));
			if(re){
				System.out.println("�ö����Ѿ�����ɹ���");
				requestDispatcherOrder(request,response);
			}
			
		}else if(action!=null&&action.equals("add")){
			//��������
			Order order=new Order();
			List<Order> list=orderService.findOrderList(order);
			request.setAttribute("list", list);
			request.getRequestDispatcher("addorder.jsp").forward(request, response);
			
		}else if(action!=null&&action.equals("toUpdate")){
			//��ת���޸�ҳ�����
			Order order=orderService.findOrderById(Integer.parseInt(id));
			System.out.println("order:"+order);
			request.setAttribute("o", order);
			request.getRequestDispatcher("updateorder.jsp").forward(request, response);
			
		}else if(action!=null&&action.equals("update")){
			//�޸Ĳ���
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
				System.out.println("�޸ĳɹ�");
				//�޸ĳɹ�
				requestDispatcherOrder(request,response);
			}
		}else{
			requestDispatcherOrder(request,response);
		}
	 }

	//��ת��orderlist.jsp�Ĺ�������
	public void requestDispatcherOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ʣ������˺�������
		String key=request.getParameter("key")==null?"":request.getParameter("key");
		//Ҫ��ѯ�ڼ�ҳ
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		
		Order order=new Order();
		order.setKey(key);
		order.setPage(Integer.parseInt(page));
		//��������
		List<Order> list=orderService.findOrderList(order);
		System.out.println(list);
		//����һ���ж���ҳ
		//1.һ���ж���������
		int count=orderService.findOrderCount();
		//2.����һ���ж���ҳ
		int total=0;//��ҳ��
		if(count%order.getPageSize()==0){
			total=count/order.getPageSize();
		}else{
			total=count/order.getPageSize()+1;
		}
		//request�����÷�Χ�����ĳһ������
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//��ǰ�ڼ�ҳ
		request.setAttribute("total", total);//��ҳ��
		request.getRequestDispatcher("orderlist.jsp").forward(request, response);
	}



}
