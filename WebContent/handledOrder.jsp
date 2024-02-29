<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单销售统计</title>
<script type="text/javascript">

//上一页
function showPre(){
	var page="${page}"; //当前第几页
	if(page==1){
		alert("当前已经是第一页了！")
		return;
	}
	page=page-1;
	location.href="HandledServlet?page="+page;
}
//下一页
function showNext(){	
	var page="${page}";//当前第几页
	if(page=="${total}"){ //如果是最后一页
		alert("当前已经是最后一页了！");
	    return;
	}
	//page=page+1;
	page++;
	location.href="HandledServlet?page="+page;
}
</script>
</head>
<style>
	.content{
		border:1;
	
		margin:0 auto;
/*		background-color:#9ea5de;*/
		background: linear-gradient(#35b4df, #9c28b0);
/*	padding-top:10px;*/
		text-align:center
	}

</style>
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
				width: 800px;
				height: 350px;
				margin: auto;
				margin-top: 10px;
				background-color: #ffffff63;
				padding: 50px 50px 30px;
				box-shadow: 10px 10px 15px black;
				text-align: inherit;
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
				background-color: #f5f3f3;
				padding: 0.1px;
			}
			

    </style>
<body>
<center>
<div class="dialog-bz">
 欢迎${user.userno }来到易购（E-go）后台系统<br>
  <form action="HandledServlet" method="post">
      订单编号<input type="text" name="key" value="${key }"><input type="submit" value="搜索">   
  
  </form>
  
   <table border="1"
			   style="color:white;background: linear-gradient(#b2b2b2, grey)";>
			<tr style="font-weight:bold">
       <td>订单ID</td>
       <td>订单编号</td>
       <td>订单名称</td>
       <td>订单数量</td>
       <td>客户账号</td>
       <td>收货人</td>
       <td>收货地址</td>
       <td>收货人电话</td>
     </tr>
     <!-- JSTL标签+EL表达式 -->
     <c:forEach var="h" items="${list }">
       <tr>
         <td>${h.id }</td>
         <td>${h.orNum }</td>
         <td>${h.orName }</td>
         <td>${h.orAmount }</td>
         <td>${h.orUserno }</td>
         <td>${h.orCus }</td>
         <td>${h.orAddress }</td>
         <td>${h.orPhone }</td>
       
         
       </tr>
     </c:forEach>
     
   </table>
   <a  href="#" onclick="showPre()">上一页</a>&nbsp;&nbsp;&nbsp;
     	第${page }页&nbsp;&nbsp;&nbsp;一共${total }页&nbsp;&nbsp;&nbsp;
   <a  href="#" onclick="showNext()">下一页</a>
</div>
</center>
</body>
</html>