package com.Eshopping.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import javax.servlet.http.*;
import com.Eshopping.service.*;
import com.Eshopping.util.*;


public class FormService extends BaseService {
	
	public FormService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super(request, response);
	}

	public void SaveData() throws Exception
	{ 	
	    //获取提交的数�?
	    String submitJSON = request.getParameter("submitData");
	    HashMap data = (HashMap)JSON.decode(submitJSON);

	    //进行数据处理
	    String UserName = data.get("UserName") != null ? data.get("UserName").toString() : "";
	    String Pwd =  data.get("Pwd") != null ? data.get("Pwd").toString() : "";
	    //......

	    //返回处理结果
	    String json = JSON.encode(data);
	    response.getWriter().write(json);    
	}
	public void LoadData() throws Exception
	{
		String path = request.getSession().getServletContext().getRealPath("/");    
		String file = path+"/demo/data/form.txt";
	    String s = FileHelper.read(file);
	    HashMap data = (HashMap)JSON.decode(s);
	    
	    //返回处理结果
	    String json = JSON.encode(data);
	    response.getWriter().write(json);    
	}
	
}
