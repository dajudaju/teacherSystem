<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="../styles/main.css"/>" type="text/css"
	rel="stylesheet" />
<title>成绩查询</title>
</head>
<body>
	<div class="main">
		<h2 class="title">
			<span>成绩</span>
		</h2>
		<form action="<c:url value="/cou/deletes?pageNO=${pageNO}"/>"
			method="post">
			<table border="1" width="100%" class="tab">
				<tr>
					<th>课程名称</th>
					<th>代课老师</th>
					<th>平时成绩</th>
					<th>期末成绩</th>
					<th>综合成绩</th>
				</tr>
				<c:forEach var="g" items="${glist}" varStatus="status">
					<tr>
						<td>${g.cname}</td>
						<td>${g.tname }</td>
						<td>${g.pgrade}</td>
						<td>${g.kgrade}</td>
						<td>${g.zgrade}</td>

					</tr>
				</c:forEach>
			</table>
			<script type="text/javascript"
				src="<c:url value="/scripts/jquery-1.10.2.min.js"/>"></script>
		</form>
	</div>
</body>
</html>
