<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
</div>
<div id="welcomebox">
<div id="registerbox">
<h2>注册!</h2>
<b>想试试Retwis? 请注册账号!</b>
<form method="POST" action="/registerUser">
<table>
<tr>
  <td>用户名</td><td><input type="text" name="username"  value="${username}"></td>
</tr>
<tr>
  <td>密码</td><td><input type="password" name="password"  value="${password }"></td>
</tr>
<tr>
  <td>密码(again)</td><td><input type="password" name="password2"  value="${password2 }"></td>
</tr>
<tr>
<td colspan="2" align="right"><span style="color:red">${requestScope.errMsg }</span><input type="submit" name="doit"  value="注册"></td></tr>
</table>
</form>
<h2>已经注册了? 请直接登陆</h2>
<form method="POST" action="login">
<table><tr>
  <td>用户名</td><td><input type="text" name="user"></td>
  </tr><tr>
  <td>密码:</td><td><input type="password" name="passwd"></td>
  </tr><tr>
  <td colspan="2" align="right"><span style="color:red">${requestScope.errMsg1 }</span><input type="submit" name="doit" value="Login"></td>
</tr></table>
</form>
</div>
介绍! Retwis  是一个简单的<a href="http://twitter.com">Twitter</a>克隆, 也是<a href="http://code.google.com/p/redis/">Redis</a> key-value 数据库的一个使用安全. 关键点:
<ul>
<li>Redis 是一种key-value 数据库, 而且是本项目中 <b>唯一</b>使用的数据库, 没有用mysql等.</li>
<li>应用程序可以通过一致性哈希轻易的部署多台服务器</li>
<li>本程序使用Jedis做的小程序，不完善之处敬请谅解！</a>
</ul>
</div>
<%@include file="common/footer.jsp" %>
