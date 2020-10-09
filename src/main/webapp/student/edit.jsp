<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link href="<c:url value="../styles/main.css" />" type="text/css"
	rel="stylesheet" />
<title>编辑学生</title>
</head>
<body>
	<div class="main">
		<h2 class="title">
			<span>编辑学生</span>
		</h2>
		<form
			action="${pageContext.request.contextPath }/stuServlet?cmd=updateStu"
			method="post">
			<!-- <form:form action="addSave" modelAttribute="entity"> -->
			<fieldset>
				<legend>学生</legend>
				<input type="text" style="display: none;" name="id"
				value="${student.id }" />
				<p>
					<label for="name">学生姓名：</label> <input type="text"
						value="${student.name }" name="name" />
					<!-- <form:input path="name" size="50" />
					<form:errors path="name" cssClass="error"></form:errors> -->
				</p>

				<p>
					<label for="name">登录名：&emsp;</label> <input type="text"
						value="${student.loginname }" name="loginname" />
					<!-- <form:input path="loginname" size="50" />
					<form:errors path="loginname" cssClass="error"></form:errors> -->
				</p>
				<p>
					<label for="password">密码：&emsp;&emsp;</label> <input
						type="password" value="${student.password }" name="password" />
					<!-- <form:password path="password" size="50" showPassword="true" />
					<form:errors path="password" cssClass="error"></form:errors> -->
				</p>

				<p>
					<c:if test="${student.sex == '男' }">
						<label for="sex">学生性别：</label>
						<input checked="checked"  type="radio" name="sex" value="男" />男 <input
							type="radio" name="sex" value="女" />女
					</c:if>
					<c:if test="${student.sex == '女' }">
						<label for="sex">学生性别：</label>
						<input type="radio" name="sex" value="男" />男 <input type="radio"
							checked="checked" name="sex" value="女" />女
					</c:if>
					<!-- <form:select path="sex">
						<form:option value="0">男</form:option>
						<form:option value="1">女</form:option>
					</form:select>
					<form:errors path="sex" cssClass="error"></form:errors> -->
				</p>
				<p>
					<label for="tel">学生电话：</label> <input value="${student.tel }"  type="tel"
						name="tel" size="11" />
					<!-- 	<form:input path="tel" size="11" />
					<form:errors path="tel" cssClass="error"></form:errors> -->
				</p>
				<p>
					<label for="address">居住地址：</label> <input value="${student.address }" 
						type="text" name="address" />
					<!-- <form:input path="address" size="50" />
					<form:errors path="address" cssClass="error"></form:errors> -->
				</p>
				<p>
					<label for="classid">班级:&emsp;&emsp;&emsp;</label> <select
						  name="classid">
						<c:forEach var="cla" items="${clalist }">
							<option value="${clalist.id }"></option>
						</c:forEach>
					</select>
					<%-- <form:select path="productType.id">
                     <form:option value="0">--请选择--</form:option>
                     <form:options items="${productTypes}"  itemLabel="name" itemValue="id"/>
                </form:select> --%>
					<%-- <form:select path="classid">
						<form:option value="0">--请选择班级--</form:option>
						<form:options items="${clist}" itemLabel="name" itemValue="id"></form:options>
					</form:select>
					<form:errors path="classid" cssClass="error"></form:errors> --%>
				</p>
				<p>
					<input type="submit" value="保存" class="btn out">
				</p>
			</fieldset>
			<!-- </form:form> -->
		</form>
		<p style="color: red">${message}</p>
		<!-- <form:errors path="*"></form:errors> -->
		<p>
			<a href="<c:url value="/stuServlet?cmd=getStuList" />"
				class="abtn out">返回列表</a>
		</p>
	</div>
</body>
</html>
