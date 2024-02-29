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
 * �����û���ص�ҵ������
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
		request.setCharacterEncoding("UTF8");//���Ŀ�
		
		
		String action=request.getParameter("action");//����
		String id=request.getParameter("id");//�û�Id
		String userno=request.getParameter("userno");//�û���
		String password=request.getParameter("password");//�û�����
		String phone=request.getParameter("phone");//�û��绰
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		
		if(action!=null&&action.equals("delete")){
			//ɾ������
			boolean re=userService.deleteUser(Integer.parseInt(id));
			if(re){
				System.out.println("ɾ���ɹ���");
				//��ת
				requestDispatcherUser(request, response);
			}else{
				System.out.println("ɾ��ʧ�ܣ�");
			}
		}else if(action!=null&&action.equals("add")){
			//��������
			User user=new User();
			List<User> list=userService.findUserList(user);
			request.setAttribute("list", list);
			request.getRequestDispatcher("useradd.jsp").forward(request,response);
			
		}else if(action!=null&&action.equals("toUpdate")){
			//��ת���޸�ҳ�����
			User user=userService.findUserById(Integer.parseInt(id));
			request.setAttribute("u",user);
			request.getRequestDispatcher("update.jsp").forward(request, response);
			
			
		}else if(action!=null&&action.equals("update")){
			//�޸Ĳ���
			User u=new User();
			if(id!=null&&!id.equals("")){//�޸ĲŻḳֵ
				u.setId(Integer.parseInt(id));
			}
			u.setUserno(userno);
			u.setPassword(password);
			u.setEmail(email);
			u.setAddress(address);
			u.setPhone(phone);
			
			boolean re=userService.updateUser(u);
			if(re){
				System.out.println("�޸ĳɹ�");
				//��ת
				requestDispatcherUser(request, response);
			}
		}else{
			//��������
			requestDispatcherUser(request, response);
		}
		
		

	}
	//��ת��userlist.jsp�Ĺ�������
	public void requestDispatcherUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		String key=request.getParameter("key")==null?"":request.getParameter("key");//������(�����˺�����)
		
		User user=new User();
		user.setKey(key);
		user.setPage(Integer.parseInt(page));
		//��������
		List<User> list=userService.findUserList(user);
		//����һ���ж���ҳ
		//1.һ���ж���������
		int count=userService.findUserCount();
		//2.����һ���ж���ҳ
		int total=0;//��ҳ��
		if(count%user.getPageSize()==0){	
			total=count/user.getPageSize();
		}else{
			total=count/user.getPageSize()+1;
		}
		//request�������÷�Χ�����ĳһ������
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//��ǰ�ڼ�ҳ
		request.setAttribute("total", total);//��ҳ��
		request.getRequestDispatcher("userlist.jsp").forward(request,response);
	}
}
