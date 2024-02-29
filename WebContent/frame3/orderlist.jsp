<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />

<title>E-go（易购）订单管理</title>
<script type="text/javascript">
//javascript:弱类型
//删除订单
function deleteOrder(id){
	var result=confirm("确定要删除该订单？");//确定返回true，取消返回false
	if(result){
		location.href="OrderServlet?id="+id+"&action=delete";		
	}
}
function handlingOrder(id){
	var result=confirm("确定要进入订单处理环节吗？")
	if(result){
		location.href="OrderServlet?id="+id+"&action=handling"
	}
}
//上一页
function showPre(){
	var page="${page}"; //当前第几页
	if(page==1){
		alert("当前已经是第一页了！")
		return;
	}
	page=page-1;
	location.href="OrderServlet?page="+page;
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
	location.href="OrderServlet?page="+page;
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
				width: 700px;
				height: 300px;
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
  <form action="OrderServlet" method="post">
      订单编号<input type="text" name="key" value="${key }"><input type="submit" value="搜索">   
  
  </form>
  <a  href="updateorder.jsp" target="_blank" class="mini-button">新增</a>
  <a  href="HandledServlet" target="_blank" class="mini-button">订单销售统计</a>
  
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
     <c:forEach var="o" items="${list }">
       <tr>
         <td>${o.id }</td>
         <td>${o.orNum }</td>
         <td>${o.orName }</td>
         <td>${o.orAmount }</td>
         <td>${o.orUserno }</td>
         <td>${o.orCus }</td>
         <td>${o.orAddress }</td>
         <td>${o.orPhone }</td>
         <td><a  href="OrderServlet?id=${o.id }&action=toUpdate">修改</a> <a  href="#" onclick="deleteOrder(${o.id })">删除</a> <a href="#" onclick="handlingOrder(${o.id })">订单处理</a></td>
         
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