<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<h2>热点</h2>
<i>最新注册用户(redis中的sort用法)</i><br>
<c:forEach var="user" items="${users }">
	<div><a class="username" href="/profile?username=${user }">${user }</a> </div>
</c:forEach>


<br><i>最新的50条微博!</i><br>

<div class="post">
<c:forEach items="${posts}" var="p">
<c:set var="time"  value="${p.time }"></c:set>
 <a class="username" href="profile?username=${p.username }">${p.username }</a> ${p.content }<br/>
<i>${p.time }  通过 web发布</i><br/>
</c:forEach> 
</div>

<%@include file="common/footer.jsp"%>
