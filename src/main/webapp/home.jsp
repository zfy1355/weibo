<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp" %>
<div id="postform">
<form method="POST" action="/post">
${user.username}, 有啥感想?
<br>
<table>
<tr><td><textarea cols="70" rows="3" name="content"  id="content"></textarea></td><input type="hidden"  id="username" name="username" value="${username}"/></td></tr>
<tr><td align="right"><input type="button" name="doit" value="Update" onclick="doPost()"></td></tr>
</table>
</form>
<div id="homeinfobox">
${user.followerC} 粉丝<br>
${user.followingC} 关注<br>
</div>
</div>

<c:forEach items="${user.posts}" var="p">
<div class="post">
<c:set var="time"  value="${p.time }"></c:set>
 <a class="username" href="profile?username=${p.username }">${p.username }</a> ${p.content }<br/>
<i>${p.time }  通过 web发布</i><br/>
</div>
</c:forEach> 


<%@include file="common/footer.jsp" %>
<script type="text/javascript" src="js/jquery-2.0.3.min.js"></script>
<script type="text/javascript">
	function doPost(){
		$.ajax({
			url:  '/post',
			type: 'post',
			dataType:"json",
			data: {'content': $("#content").val(),'username':$("#username").val()},
			success:function(data){
			//	var htmlobj = jQuery.parseJSON(data); 
				console.log(data);
				$(".post").first().before("<div class='post'><a class='username' href='profile?username='>"+data.username+"</a>"+data.content+"<br>	<i>"+data.time+" 通过 web发布</i><br/></div>")
			}
			,error:function(data){
				alert(data)
			}
		})
}
</script>