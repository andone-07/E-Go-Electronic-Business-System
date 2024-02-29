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
		request.setCharacterEncoding("UTF8");//中文库
		
		String id=request.getParameter("id");//商品Id
		String action=request.getParameter("action");//操作
		String category=request.getParameter("category");
		String info=request.getParameter("info");
		System.out.println(category);
		System.out.println(info);
		
		if(action!=null&&action.equals("delete")){
			//删除操作
			boolean re=categoryService.deleteCategory(Integer.parseInt(id));
			if(re){
				requestDispatcherGoods(request, response);
			}else{
				System.out.println("删除失败！");
			}
		}else if(action!=null&&action.equals("add")){
			//新增操作
			Category category_=new Category();
			List<Category> list=categoryService.findCategoryList(category_);
			request.setAttribute("list", list);
			request.getRequestDispatcher("addcty.jsp").forward(request,response);
			
		}else if(action!=null&&action.equals("toUpdate")){
			//跳转到修改页面操作
			Category category_=categoryService.findCategoryById(Integer.parseInt(id));
			request.setAttribute("c",category_);
			request.getRequestDispatcher("ctyupdate.jsp").forward(request, response);
			
			
		}else if(action!=null&&action.equals("update")){
			//修改操作
			Category c=new Category();
			if(id!=null&&!id.equals("")){//修改才会赋值
				c.setId(Integer.parseInt(id));
			}
			c.setCategory(category);
			c.setInfo(info);
			
			boolean re=categoryService.updateCategory(c);
			if(re){
				System.out.println("修改成功");
				//跳转
				requestDispatcherGoods(request, response);
			}
		}else{
			//搜索操作
			requestDispatcherGoods(request, response);
		}
		
		

	}
	//跳转到goodslist.jsp的公共方法
	public void requestDispatcherGoods(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String page=request.getParameter("page")==null?"1":request.getParameter("page");
		String key=request.getParameter("key")==null?"":request.getParameter("key");//搜索词(根据账号搜索)
		
		Category category=new Category();
		category.setKey(key);
		category.setPage(Integer.parseInt(page));
		//搜索操作
		List<Category> list=categoryService.findCategoryList(category);
		//计算一共有多少页
		//1.一共有多少条数据
		int count=categoryService.findCategoryCount();
		//2.计算一共有多少页
		int total=0;//总页数
		if(count%category.getPageSize()==0){	
			total=count/category.getPageSize();
		}else{
			total=count/category.getPageSize()+1;
		}
		//request数据作用范围：针对某一次请求
		request.setAttribute("list", list);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//当前第几页
		request.setAttribute("total", total);//总页数
		request.getRequestDispatcher("categorylist.jsp").forward(request,response);
	}


}
