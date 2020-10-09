<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="../styles/main.css"/>"  type="text/css" rel="stylesheet" />
<title>班级管理</title>
<script type="text/javascript" src="./jquery/jQuery-3.5.1-min.js"></script>
<script type="text/javascript">
	$(function() {
		/* 全选按钮  */
		$("#chbAll").change(function() {
			var check = $(this).prop("checked");
			$(".check").prop("checked", check);
		});
	});
</script>
</head>
<body>
    <div class="main">
        <h2 class="title"><span>学生管理</span></h2>
        <form
			action="${pageContext.request.contextPath }/claServlet?cmd=deleteAllCla"
			method="post">
       <%--  <form action="<c:url value="/cla/deletes?pageNO=${pageNO}"/>" method="post"> --%>
        <table border="1" width="100%" class="tab">
            <tr>
                <th><input type="checkbox" id="chbAll"></th>
                <th>班级编号</th>
                <th>班级名称</th>
                <th>班级类别</th>
                <th>操作</th>
            </tr>
            <c:forEach var="entity" items="${clalist}" varStatus="status">
                <tr>
                    <th><input class="check" type="checkbox" name="id" value="${entity.id}"></th>
                    <td>${entity.id}</td>
                    <td>${entity.name}</td>
                    <td>${entity.type}</td>
                    <td>
                    <a href="<c:url value="claServlet?cmd=deleteCla&id=${entity.id}"/>" class="abtn">删除</a>
                    <a href="${pageContext.request.contextPath }/claServlet?cmd=findClaById&id=${entity.id}" class="abtn">编辑</a>
                    <a href="${pageContext.request.contextPath }/claServlet?cmd=findStuById&id=${entity.id}" class="abtn">本班学生</a>
                    <%-- <a href="search1/${entity.id}" class="abtn">本班学生1${entity.id}</a> --%>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div id="pager"></div>
        <p>
            <a href="${pageContext.request.contextPath }/class/add.jsp" class="abtn out">添加</a>
            <input type="submit"  value="批量删除" class="btn out"/>
        </p>
        <p style="color: red">${message}</p>
        <!--分页 -->
        <script type="text/javascript" src="<c:url value="/styles/bootstrap/js/jquery-1.10.2.js"/>" ></script>
        <link href="<c:url value="/scripts/pagination.css"/>"  type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="<c:url value="/scripts/jquery.pagination.js"/>" ></script>
        <script type="text/javascript">
           //初始化分页组件
           var count=${count};
           var size=${size};
           var pageNO=${pageNO};
           $("#pager").pagination(count, {
              items_per_page:size,
               current_page:pageNO-1,
               next_text:"下一页",
               prev_text:"上一页",
               num_edge_entries:2,
               load_first_page:false,
              callback:handlePaginationClick
            });
           
           //回调方法
           function handlePaginationClick(new_page_index, pagination_container){
               location.href="<c:url value="/cla/"/>list?pageNO="+(new_page_index+1);
           }
           
        </script>
    </form>
    </div>
</body>
</html>
