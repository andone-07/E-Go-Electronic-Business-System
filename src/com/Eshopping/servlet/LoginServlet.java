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
 * �����û���½
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//��Ĺ�������
    public LoginServlet() {
        super();
       
    }
    //����get����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	//����post����
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");//���ı���
		String userno=request.getParameter("userno");
		String password=request.getParameter("password");
		
		String key="";//�����ؼ���
		
		UserService userService=new UserService();
		//true:�ɹ���false:ʧ��
		User user=userService.login(userno,password);
		if(user!=null){
			System.out.println("��¼�ɹ���");
			User u=new User();
			u.setKey(key);
			List<User> list=userService.findUserList(u);
			//����һ���ж���ҳ
			//1.һ�����ж�������
			int count=userService.findUserCount();
			//2.����һ���ж���ҳ
			int total=0;//��ҳ��
			if(count%u.getPageSize()==0){	
				total=count/u.getPageSize();
			}else{
				total=count/u.getPageSize()+1;
			}
			
			//���û��б����ݷ��뵽request�����У������
			request.setAttribute("list", list);
//			request.setAttribute("user", user);
			//Session�������������ͬһ���������ͬ1�����������ͬ����֮�乲������
			request.getSession().setAttribute("user", user);
			request.setAttribute("page",1);
			request.setAttribute("total",total);
			//������ҳ(ת��)
			request.getRequestDispatcher("userlist.jsp").forward(request,response);
			//�ض���
			//response.sendRedirect("http://www.baidu.com");
		}else{
			System.out.println("��¼ʧ�ܣ�");
		}
	}

}

