<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<header><h1>${title}</h1></header>
	<nav>
		<ul>
			<li><a href="home.bo">Home</a></li>
			<li class="dd">
				<a class="dd-1">Board</a>
					<div class="dd-content">
						<a href="boardList.bo?btype=0">게시판1</a>
						<a href="boardList.bo?btype=1">게시판2</a>
						<a href="boardList.bo?btype=2">게시판3</a>
					</div>
			</li>
		</ul>
	</nav>