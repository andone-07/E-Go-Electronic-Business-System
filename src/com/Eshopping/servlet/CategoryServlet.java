package com.Eshopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.User;

import com.Eshopping.service.GoodsService;
import com.Eshopping.service.CategoryService;

public class CategoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	CategoryService categoryService=new CategoryService();
       
    public CategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");//���Ŀ�
		
		String id=request.getParameter("id");//��ƷId
		String action=request.getParameter("action");//����
		String category=request.getParameter("category");
		String info=request.getParameter("info");
		System.out.println(category);
		System.out.println(info);
		
		if(action!=null&&action.equals("delete")){
			//ɾ������
			boolean re=categoryService.deleteCategory(Integer.parseInt(id));
			if(re){
				requestDispatcherGoods(request, response);
			}else{
				System.out.println("ɾ��ʧ�ܣ�");
			}
		}else if(action!=null&&action.equals("add")){
			//��������
			Category category_=new Category();
			List<Category> list=categoryService.findCategoryList(category_);
			request.setAttribute("list", list);
			request.getRequestDispatcher("addcty.jsp").forward(request,response);
			
		}else if(action!=null&&action.equals("toUpdate")){
			//��ת���޸�ҳ�����
			Category category_=categoryService.findCategoryById(Integer.parseInt(id));
			request.setAttribute("c",category_);
			request.getRequestDispatcher("ctyupdate.jsp").forward(request, response);
			
			
		}else if(action!=null&&action.equals("update")){
			//�޸Ĳ���
			Category c=new Category();
			if(id!=null&&!id.equals("")){//�޸ĲŻḳֵ
				c.setId(Integer.parseInt(id));
			}
			c.setCategory(category);
			c.setInfo(info);
			
			boolean re=categoryService.updateCategory(c);
			if(re){
				System.out.println("�޸ĳɹ�");
				//��ת
				requestDispatcherGoods(request, response);
			}
		}else{
			//��������
			requestDispatcherGoods(request, response);
		}
		
		

	}
	//��ת��goodslist.jsp�Ĺ�������
	public void requestDispatcherGoods(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		String key=request.getParameter("key")==null?"":request.getParameter("key");//������(�����˺�����)
		
		Category category=new Category();
		category.setKey(key);
		category.setPage(Integer.parseInt(page));
		//��������
		List<Category> list=categoryService.findCategoryList(category);
		//����һ���ж���ҳ
		//1.һ���ж���������
		int count=categoryService.findCategoryCount();
		//2.����һ���ж���ҳ
		int total=0;//��ҳ��
		if(count%category.getPageSize()==0){	
			total=count/category.getPageSize();
		}else{
			total=count/category.getPageSize()+1;
		}
		//request�������÷�Χ�����ĳһ������
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//��ǰ�ڼ�ҳ
		request.setAttribute("total", total);//��ҳ��
		request.getRequestDispatcher("categorylist.jsp").forward(request,response);
	}


}
