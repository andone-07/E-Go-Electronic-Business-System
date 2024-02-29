package com.Eshopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.Goods;

import com.Eshopping.service.GoodsService;
import com.Eshopping.service.CategoryService;

/**
 * �����û���ص�ҵ������
 */
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsService goodsService=new GoodsService();
       
    public GoodsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");//���Ŀ�
		
		
		String action=request.getParameter("action");//����
		String id=request.getParameter("id");//��ƷId
		String gsname=request.getParameter("gsname");//��Ʒ����
		String gsprice=request.getParameter("gsprice");//��Ʒ�۸�
		String gsamount=request.getParameter("gsamount");//��Ʒ����
		String category_id=request.getParameter("category_id");
		String category=request.getParameter("category");
		System.out.println(gsamount);
		System.out.println(category);
		
		if(action!=null&&action.equals("delete")){
			//ɾ������
			boolean re=goodsService.deleteGoods(Integer.parseInt(id));
			if(re){
				System.out.println("ɾ���ɹ���");
				//��ת
				requestDispatcherGoods(request, response);
			}else{
				System.out.println("ɾ��ʧ�ܣ�");
			}
		}else if(action!=null&&action.equals("add")){
			Category category2=new Category();
			//��������
			List<Category> list2=goodsService.findCategoryList(category2);
			request.setAttribute("list2", list2);
			request.getRequestDispatcher("gsadd.jsp").forward(request,response);
			
		}else if(action!=null&&action.equals("toUpdate")){
			//��ת���޸�ҳ�����
			Category category2=new Category();
			Goods goods=goodsService.findGoodsById(Integer.parseInt(id));
			List<Category> list2=goodsService.findCategoryList(category2);
			request.setAttribute("list2", list2);
			request.setAttribute("g",goods);
			request.getRequestDispatcher("gsupdate.jsp").forward(request, response);
			
			
		}else if(action!=null&&action.equals("update")){
			//�޸Ĳ���
			Goods g=new Goods();
			if(id!=null&&!id.equals("")){//�޸ĲŻḳֵ
				g.setId(Integer.parseInt(id));
			}
			g.setGsname(gsname);
			g.setGsprice(gsprice);
			g.setGsamount(gsamount);
			g.setCategory(category);
			g.setCategory_id(category_id);
			
			boolean re=goodsService.updateGoods(g);
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
		
		Goods goods=new Goods();
		Category category2=new Category();
		goods.setKey(key);
		goods.setPage(Integer.parseInt(page));
		//��������
		List<Goods> list=goodsService.findGoodsList(goods);
		List<Category> list2=goodsService.findCategoryList(category2);
		//����һ���ж���ҳ
		//1.һ���ж���������
		int count=goodsService.findGoodsCount();
		//2.����һ���ж���ҳ
		int total=0;//��ҳ��
		if(count%goods.getPageSize()==0){	
			total=count/goods.getPageSize();
		}else{
			total=count/goods.getPageSize()+1;
		}
		//request�������÷�Χ�����ĳһ������
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//��ǰ�ڼ�ҳ
		request.setAttribute("total", total);//��ҳ��
		
		request.getRequestDispatcher("goodslist.jsp").forward(request,response);
	}
}
