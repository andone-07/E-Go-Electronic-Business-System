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
		requestDispatcherHandled(request,response);	
		
    }
	 

	//��ת��handledOrder.jsp�Ĺ�������
	public void requestDispatcherHandled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�����ʣ������˺�������
		String key=request.getParameter("key")==null?"":request.getParameter("key");
		//Ҫ��ѯ�ڼ�ҳ
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		
		Handled handled=new Handled();
		handled.setKey(key);
		handled.setPage(Integer.parseInt(page));
		//��������
		List<Handled> list=handledService.findHandledList(handled);
		System.out.println(list);
		//����һ���ж���ҳ
		//1.һ���ж���������
		int count=handledService.findHandledCount();
		//2.����һ���ж���ҳ
		int total=0;//��ҳ��
		if(count%handled.getPageSize()==0){
			total=count/handled.getPageSize();
		}else{
			total=count/handled.getPageSize()+1;
		}
		//request�����÷�Χ�����ĳһ������
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//��ǰ�ڼ�ҳ
		request.setAttribute("total", total);//��ҳ��
		request.getRequestDispatcher("handledOrder.jsp").forward(request, response);
	}





}
