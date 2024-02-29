<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
 <link href="demo.css" rel="stylesheet" type="text/css" />
 <script src="scripts/boot.js" type="text/javascript"></script>
 <link href="scripts/miniui/themes/bootstrap/skin.css" rel="stylesheet" type="text/css" />
 <link href="scripts/miniui/themes/default/large-mode.css" rel="stylesheet"
        type="text/css" />

    
    
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
    
<title>用户列表</title>
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

<script type="text/javascript" >
//javascript:弱类型
function deleteUser(id){//不用指定数据类型
//删除用户	
	var result=window.confirm("确定要删除？")//window.可以不写
	if(result){
		location.href="UserServlet?id="+id+"&action=delete";		
	}


}
//上一页
function showPre(){
	var page="${page}";
	if(page==1){
		alert("当前已经是第一页了！");
		return;
	}
	page=page-1;
	location.href="UserServlet?page="+page;
	
}
//下一页
function showNext(){
	var page="${page}";
	if(page=="${total}"){
		alert("当前已经是最后一页了！");
		return;
	}
	page++;
	location.href="UserServlet?page="+page;
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

<center
>
<body>
	<div class="dialog-bz">

		欢迎${user.userno}来到易购(E-go)管理员管理界面<br>
		<form action="UserServlet" method="post">
		账号<input type="text" name="key" value="${key }"><input type="submit" value="搜索">
		</form>
		<a onclick="add()"class="mini-button">新增</a>
		<table border="1"
			   style="color:white;background: linear-gradient(#b2b2b2, grey)";>
			<tr style="font-weight:bold">
			<tr>
				<td>用户ID</td>
				<td>账号</td>
				<td>邮箱</td>
				<td>电话</td>
				<td>地址</td>
				<td>操作</td>
				
			</tr>
			
			<!--JSTL标签+EL表达式-->
			<c:forEach var="u" items="${list }">
				<tr>
					<td>${u.id }</td>
					<td>${u.userno }</td>
					<td>${u.email }</td>
					<td>${u.phone }</td>
					<td>${u.address }</td>
					<td><a  href ="UserServlet?id=${u.id}&action=toUpdate">修改</a> <a   onclick="deleteUser(${u.id})">删除</a></td>
					
				</tr>
			</c:forEach>	
		</table>
		<a href="#" onclick="showPre()">上一页</a>&nbsp;&nbsp;&nbsp;
		第${page}页&nbsp;&nbsp;&nbsp;共${total}页&nbsp;&nbsp;&nbsp;
		<a href="#" onclick="showNext()">下一页</a>
	
               <table width="600px" cellpadding="2" class="chara1"align="center">
              </table>
	</div >
		<script type="text/javascript">
		mini.parse();

        var grid = mini.get("datagrid1");
        grid.load();
        grid.sortBy("createtime", "desc");
        
		function add() {
            
            mini.open({
    //            url: bootPATH + "../demo/CommonLibs/EmployeeWindow.html",
    			url: "../UserServlet?action=add",
                title: "新增管理员", width: 600, height: 400,
               
            });
        }
		
        
		</script>
	
</body>
</center>
</html>