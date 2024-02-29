<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单处理</title>
<script type="text/javascript">

function copyOrder(id){
	var result=confirm("确定要处理该订单？");//确定返回true，取消返回false
	if(result){
		location.href="OrderServlet?id="+id+"&action=copyOrder";//copyOrder把数据复制到另一个表
	}
}
function deleteOrder(id){
	var result=confirm("确定要处理该订单？");//确定返回true，取消返回false
	if(result){
		location.href="OrderServlet?id="+id+"&action=delete";
	}
}

</script>
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
<body>
<center>
<form action="OrderServlet?action=update" method="post">
	<input type="hidden" name="id" value="${g.id }" >
	<div class="dialog-bz">
	<p style=text-align:center;color:grey;font-family:cursive;font-size:xx-large;>
	请确认相关信息
	</p>
		<div>
		收   货   人    &nbsp;&nbsp;&nbsp;:<input type="text" name="orCus" value="${o.orCus }">
		</div>	
		<div>
		收 货 地 址:<input type="text" name="orAddress" value="${o.orAddress }">
		</div>	
		<div>
		收货人电话:<input type="text" name="orPhone" value="${o.orPhone }">
		</div>		
		<div>
		   <a href="#" onclick="copyOrder(${o.id })&deleteOrder(${o.id })">确认处理</a>
		</div>
	</div>	
   </form>
   <script type="text/javascript">
   </script>
</center>
</body>
</html>