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
 * 接收用户相关的业务请求
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
		request.setCharacterEncoding("UTF8");//中文库
		
		
		String action=request.getParameter("action");//操作
		String id=request.getParameter("id");//商品Id
		String gsname=request.getParameter("gsname");//商品名称
		String gsprice=request.getParameter("gsprice");//商品价格
		String gsamount=request.getParameter("gsamount");//商品数量
		String category_id=request.getParameter("category_id");
		String category=request.getParameter("category");
		System.out.println(gsamount);
		System.out.println(category);
		
		if(action!=null&&action.equals("delete")){
			//删除操作
			boolean re=goodsService.deleteGoods(Integer.parseInt(id));
			if(re){
				System.out.println("删除成功！");
				//跳转
				requestDispatcherGoods(request, response);
			}else{
				System.out.println("删除失败！");
			}
		}else if(action!=null&&action.equals("add")){
			Category category2=new Category();
			//新增操作
			List<Category> list2=goodsService.findCategoryList(category2);
			request.setAttribute("list2", list2);
			request.getRequestDispatcher("gsadd.jsp").forward(request,response);
			
		}else if(action!=null&&action.equals("toUpdate")){
			//跳转到修改页面操作
			Category category2=new Category();
			Goods goods=goodsService.findGoodsById(Integer.parseInt(id));
			List<Category> list2=goodsService.findCategoryList(category2);
			request.setAttribute("list2", list2);
			request.setAttribute("g",goods);
			request.getRequestDispatcher("gsupdate.jsp").forward(request, response);
			
			
		}else if(action!=null&&action.equals("update")){
			//修改操作
			Goods g=new Goods();
			if(id!=null&&!id.equals("")){//修改才会赋值
				g.setId(Integer.parseInt(id));
			}
			g.setGsname(gsname);
			g.setGsprice(gsprice);
			g.setGsamount(gsamount);
			g.setCategory(category);
			g.setCategory_id(category_id);
			
			boolean re=goodsService.updateGoods(g);
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
		
		Goods goods=new Goods();
		Category category2=new Category();
		goods.setKey(key);
		goods.setPage(Integer.parseInt(page));
		//搜索操作
		List<Goods> list=goodsService.findGoodsList(goods);
		List<Category> list2=goodsService.findCategoryList(category2);
		//计算一共有多少页
		//1.一共有多少条数据
		int count=goodsService.findGoodsCount();
		//2.计算一共有多少页
		int total=0;//总页数
		if(count%goods.getPageSize()==0){	
			total=count/goods.getPageSize();
		}else{
			total=count/goods.getPageSize()+1;
		}
		//request数据作用范围：针对某一次请求
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		request.setAttribute("key", key);
		request.setAttribute("page", page);//当前第几页
		request.setAttribute("total", total);//总页数
		
		request.getRequestDispatcher("goodslist.jsp").forward(request,response);
	}
}
