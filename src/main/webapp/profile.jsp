<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<h2 class="username">${username}</h2>
<input type="hidden"  id="uid" value="${user.id}"/>
<input type="hidden"  id="isFollowing"  value="${isFollowing==true?0:1}"/>
<c:if test="${isFollowing==true}"><a href="#" class="button" onclick="f(0)"id="follow">取消关注</a></c:if>
<c:if test="${isFollowing==false}"><a href="#" class="button"  onclick="f(1)" id="follow">关注ta</a></c:if>

<div class="post">
<a class="username" href="profile?username=test">test</a> 
world<br>
<i>11 分钟前 通过 web发布</i>
</div>

<div class="post">
<a class="username" href="profile?username=test">test</a>
hello<br>
<i>22 分钟前 通过 web发布</i>
</div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript">
	function f(){
		$.ajax({
			url:'follow',
			data:{'uid':$("#uid").val(),'f':$("#isFollowing").val()},
			type:"post",
			success:function(data){
				if($("#isFollowing").val()==0){
					$("#follow").empty().append("关注ta");
					$("#isFollowing").val(1)
				}
				else{
					$("#follow").empty().append("取消关注")
					$("#isFollowing").val(0)
				}
			},
			error:function(data){
				
			}
		})
	}
	
</script>