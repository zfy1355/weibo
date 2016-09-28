<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
<div id="postform">
<form method="POST" action="/post">
${username}, 有啥感想?
<br>
<table>
<tr><td><textarea cols="70" rows="3" name="content"  id="content"></textarea></td><input type="hidden"  id="username" name="username" value="${username}"/></td></tr>
<tr><td align="right"><input type="button" name="doit" value="Update" onclick="doPost()"></td></tr>
</table>
</form>
<div id="homeinfobox">
${fansCount} 粉丝<br>
${followingCount} 关注<br>
</div>
</div>
<div class="post">
<%-- <c:forEach items="${posts} }"  var="post">
<a class="username" href="profile.php?u=test">${post.username }</a> ${post.content }<br>
<i>${post.time } 分钟前 通过 web发布</i>
</c:forEach> --%>
</div>

<%@include file="common/footer.jsp" %>
<<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
	function doPost(){
		$.ajax({
			url:  '/post',
			type: 'post',
			dataType: 'json',
			data: {'content': $("#content").val(),'username':$("#username").val()},
			success:function(data){
				$(".post").append("<br><a class='username' href='profile?username='>"+$("#username").val()+"</a>"+$("#content").val()+"<br>	<i>11 分钟前 通过 web发布</i>")
			}
		})
}
</script>