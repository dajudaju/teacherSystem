<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改密码</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="<c:url value="../styles/main.css" />" type="text/css"
	rel="stylesheet" />


</head>

<body>
	<div class="main">
		<h2 class="title">
			<span>修改密码</span>
		</h2>
		<form
			action="${pageContext.request.contextPath }/userServlet?cmd=changePwd"
			method="post">
			<fieldset>
				<legend>用户</legend>
				<p>
					<label for="name">原密码：&emsp;</label> <input type="password"
						name="password" />
				</p>
				<p>
					<label for="name">新密码：&emsp;</label> <input type="password"
						name="newPassword" />
				</p>
				<!-- <p>
					<label for="password">确认密码：</label> <input type="password"
						name="password" />
				</p> -->

				<p>
					<input type="submit" value="保存" class="btn out">
				</p>
			</fieldset>
			<p style="color: red">${message}</p>
		</form>
	</div>
</body>
</html>
