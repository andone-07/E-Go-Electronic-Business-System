<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="UTF8"%>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
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
		width:250px;
		height:185px;
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
   <form action="UserServlet?action=update" method="post">
	<input type="hidden" name="id" value="${u.id }" >
	<div class="dialog-bz">
		<div>
		用户名:<input type="text" name="userno" value="${u.userno}">
		</div>
		<div>
		密码:&nbsp;&nbsp;&nbsp;<input type="password" name="password" value="${u.password}">
		</div>
		<div>
		邮箱:&nbsp;&nbsp;&nbsp;<input type="text" name="email" value="${u.email}">
		</div>
		<div>
		电话:&nbsp;&nbsp;&nbsp;<input type="text" name="phone" value="${u.phone}">
		</div>
		<div>
		地址:&nbsp;&nbsp;&nbsp;<input type="text" name="address" value="${u.address}">
		</div>
		<div>
		   <input type="submit" onclick="CloseWindow('refresh')" value="提交">
		</div>
	</div>	
   </form>
  
  
</body>
</html>