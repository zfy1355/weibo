<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<h2 class="username">${username}</h2>
<a href="follow.php?uid=1&f=1" class="button">关注ta</a>

<div class="post">
<a class="username" href="profile.php?u=test">test</a> 
world<br>
<i>11 分钟前 通过 web发布</i>
</div>

<div class="post">
<a class="username" href="profile.php?u=test">test</a>
hello<br>
<i>22 分钟前 通过 web发布</i>
</div>
<%@include file="common/footer.jsp"%>