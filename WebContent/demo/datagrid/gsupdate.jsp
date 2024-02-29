<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
</head>

<style>
	/*标签选择器*/
	div{
		height:30px;
	}
	/*类选择器*/
	.content{
		border:3px solid #2196f3;
		width:300px;
		height:150px;
		margin:0 auto;
/*		background-color:#9ea5de;*/
		background: linear-gradient(#35b4df, #9c28b0);
/*	padding-top:10px;*/
		text-align:center
	}
	/*元素选择器*/
	p{
	text-align:center;
	color:blue;
	}
</style>
	
<body>
   <form action="GoodsServlet?action=update" method="post">
	<input type="hidden" name="id" value="${g.id }" >
	<div class="content">
		<div>
		商品名称:<input type="text" name="gsname" value="${g.gsname }">
		</div>
		<div>
		商品价格:<input type="text" name="gsprice" value="${g.gsprice}">
		</div>
		<div>
		商品数量:<input type="text" name="gsamount" value="${g.gsamount}">
		</div>
		<div>
		商品分类:<input type="text" list="category_id" name="category_id" value="${g.category_id}">
			<datalist id="category_id">
				<option value="1">食品类</option>
				<option value="2">生活类</option>
				<option value="3">娱乐类</option>
				<option value="4">饰品类</option>
				<option value="5">外设类</option>
				<a href="gsupdate.jsp" target="_blank">新增</a>
				
			</datalist>
		</div>
		<div>
		   <input type="submit" value="提交">
		</div>
	</div>	
   </form>
   <p>
   	薛梓洵是个大帅逼	
   </p>
</body>
</html>