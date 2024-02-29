<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>商品更新</title>
<style>

			body {
				background: url(6.png) no-repeat center center;
				background-size: cover;
				/* 让背景图基于容器大小伸缩 */
				background-attachment: fixed;
				/* 当内容高度大于图片高度时，背景图像的位置相对于viewport固定 此条属性必须设置否则可能无效*/
				background-color: #CCCCCC;
				/* 设置背景颜色，背景图加载过程中会显示背景色 */
			}
				div.dialog-bz {
		
				width: 350px;
				height: 250px;
				margin: auto;
				margin-top: 10px;
				background-color: #ffffff63;
				padding: 50px 50px 30px;
				box-shadow: 10px 10px 15px black;
				text-align: center;
				left:80px;
			}

			.music {
				float: right;
				padding-bottom: 600px;
			}

			.dialog-bz-btn {
				text-decoration: none;
				font-size: larger;
				color: #1f4e5f;
			}

			h2 {
				font-family: Courier New;
			}

			#start {
				width: 40px;
				height: 40px;
			}

			a:hover {
				background-color: #f4f7f7;
				padding: 10px;
			}
			
			
		
    </style>
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
<center>
<body>
   <form action="GoodsServlet?action=update" method="post">
	<input type="hidden" name="id" value="${g.id }" >
	<div class="dialog-bz">
	<p style=text-align:center;color:grey;font-family:cursive;font-size:xx-large;>
	请填写相关信息
	</p>
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
			
				
				<c:forEach var="c" items="${list2 }">
				
					<option value="${c.id }">${c.category }</option>
			    </c:forEach>
				
			</datalist>
		</div>
		<div>
		   <input type="submit"  value="提交">
		</div>
	</div>	
   </form>

</body>
</center>
</html>