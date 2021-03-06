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
<title>编辑班级</title>
</head>
<body>
	<div class="main">
		<h2 class="title">
			<span>编辑班级</span>
		</h2>
		<form
			action="${pageContext.request.contextPath }/claServlet?cmd=updateCla"
			method="post">
			<!--  <form:form action="addSave" modelAttribute="entity"> -->
			<input type="text" style="display: none;" name="id"
				value="${cla.id }" />
			<fieldset>
				<legend>学生</legend>
				<p>
					<label for="name">班级名称：</label> <input type="text" name="name"
						value="${cla.name }" />
					<!--   <form:input path="name" size="50"/>
                <form:errors path="name" cssClass="error"></form:errors> -->
				</p>
				<p>
					<label for="type">班级类别：</label>
					<c:if test="${cla == null || cla.type == '普通班' }">
						<select name="type">
							<option value="普通班">普通班</option>
							<option value="实验班">实验班</option>
						</select>
					</c:if>
					<c:if test="${cla.type == '实验班' }">
						<select name="type">
							<option value="实验班">实验班</option>
							<option value="普通班">普通班</option>
						</select>
					</c:if>
					<!-- <form:select path="type">
                     <form:option value="0">普通版</form:option>
                     <form:option value="1">实验班</form:option>
                </form:select>
                <form:errors path="type" cssClass="error"></form:errors> -->
				</p>

				<p>
					<input type="submit" value="保存" class="btn out">
				</p>
			</fieldset>
			<!--  </form:form> -->
		</form>
		<p style="color: red">${message}</p>
		<!--  <form:errors path="*"></form:errors> -->
		<p>
			<a href="<c:url value="/claServlet?cmd=getClaList" />"
				class="abtn out">返回列表</a>
		</p>
	</div>
</body>
</html>