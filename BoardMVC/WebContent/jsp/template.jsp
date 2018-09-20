<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/${content}.css?ver=3">
<link rel="stylesheet" type="text/css" href="css/common.css?ver=5">
</head>
<body>
	<jsp:include page="top.jsp"/>
	<div class="container">
	<table>
			<tr>
				<th class="num">번호</th>
				<th class="tit">제목</th>
				<th class="time">등록일시</th>
			</tr>
			<c:forEach items="${data}" var="data">
				<tr>
					<td>${data.bid}</td>
					<td>${data.btitle}</td>
					<td>${data.bregdate}</td>
				</tr>
			</c:forEach>	
		</table>
		<div class="button">
			<button>글쓰기</button>
		</div>
	</div>	
	<jsp:include page="bottom.jsp"/>
</body>
