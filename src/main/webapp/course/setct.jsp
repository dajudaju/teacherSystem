<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<link href="<c:url value="../styles/main.css" />" type="text/css"
	rel="stylesheet" />
<title>排课</title>
<base href="<c:url value="/" />" />
</head>
<body>
	<div class="main">
		<h2 class="title">
			<span>编排课程</span>
		</h2>
		<form
			action="${pageContext.request.contextPath }/couServlet?cmd=paiKe"
			method="post">
			<!-- <form:form action="cou/editSavect" modelAttribute="entity"> -->
			<input type="text" style="display: none;" name="couid" value="${couid }" />
			<fieldset>
				<legend>课程</legend>
				<p>
					<label for="name">课程名称：</label> <input readonly="readonly" type="text" name="name"
						value="${couname }" />
					<!-- <form:input path="name" size="50"/>
                <form:errors path="name" cssClass="error"></form:errors> -->
				</p>
				<p>
					<label for="type">课程类别：</label> <input readonly="readonly" type="text"
						value="${coutype }" />
					<!-- <form:select path="type">
                     <form:option value="0">必修课</form:option>
                     <form:option value="1">选修课</form:option>
                </form:select>
                <form:errors path="type" cssClass="error"></form:errors> -->
				</p>
				<p>
					<label for="title">上课教师：</label> 
					<select name="tid">
						<c:forEach var="t" items="${tealist }">
							<option value="${t.id }">${t.name }</option>
						</c:forEach>
					</select>

					<%--  <form:select path="teacher.id">
                     <form:option value="0">--请选择--</form:option>
                     <form:options items="${tlist}"  itemLabel="name" itemValue="id"/>
                </form:select>
                <form:errors path="teacher.id" cssClass="error"></form:errors> --%>
				</p>

				<p>
					<label for="classes">选择班级：</label>
					<select name="claid">
						<c:forEach var="c" items="${clalist }">
							<option value="${c.id }">${c.name }</option>
						</c:forEach>
					</select>
					<%--  <form:checkboxes path="classes" items="${clist}"  itemLabel="name"  itemValue="id" delimiter="-" />
                <form:errors path="classes" cssClass="error"></form:errors> --%>
				</p>
				<!--    <form:hidden path="id" /> -->
				<input type="submit" value="保存" class="btn out">
			</fieldset>
			<!--  </form:form> -->
		</form>
		<p style="color: red">${message}</p>
		<!-- <form:errors path="*"></form:errors> -->
		<p>
			<a href="${pageContext.request.contextPath }/couServlet?cmd=getCouList" class="abtn out">返回列表</a>
		</p>
	</div>
</body>
</html>