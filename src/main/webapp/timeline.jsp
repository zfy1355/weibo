<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<h2>热点</h2>
<i>最新注册用户(redis中的sort用法)</i><br>
<c:forEach var="user" items="${users }">
	<div><a class="username" href="/profile?username=${user }">${user }</a> </div>
</c:forEach>


<br><i>最新的50条微博!</i><br>
<div class="post">

<a class="username" href="profile.php?u=test">test</a>
world<br>
<i>22 分钟前 通过 web发布</i>
</div>

<div class="post">
<a class="username" href="profile.php?u=test">${username}</a>
hello<br>
<i>22 分钟前 通过 web发布</i>
</div>

<%@include file="common/footer.jsp"%>
