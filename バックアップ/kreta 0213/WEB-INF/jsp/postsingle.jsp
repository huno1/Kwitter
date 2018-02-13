<%@ page contentType="text/html;charset=utf-8" import="beans.*"%>
<div class="postsingle" pid="${result.postID}">
	<div class="accountgroup">
		<div class="left">
	<div class="avatar"><img src="${result.avatar}"></div>
	<div class="accountname"><a href="${pageContext.request.contextPath}/?u=${result.loginID}">${result.userName}</a></div>
		</div>
		<div class="follow"><a href="javascript: return false;" class="rounded"></a></div>
		<script>
			getFollowCheck(".postsingle .follow", _u);
		</script>
	</div>
	<div class="contentgroup">
		<p class="postcontent">${result.postContent}</p>
		<div class="postimage">
			<%
				PostBean pb = (PostBean)request.getAttribute("result");
				String mediapath = pb.getPostMedia();
				if(mediapath.indexOf(".jpg") > 1){ %>
			<img src="${result.postMedia}">
				<% }else if(mediapath.indexOf(".mp4") > 1){ %>
			<video src="${result.postMedia}">
				<% }				
			%>
		</div>
		<p class="postdate">${result.postDate}</p>
	</div>
	<div class="menugroup">
		<a class="reply"><i class="far fa-comment"></i></i><span>${result.replyCount}</span></a>
		<a class="reply"><i class="far fa-heart"></i><span>${result.likeCount}</span></a>
	</div>
</div>
<div class="writereply">
	<form>
		<div class="avatar"><img src="${pageContext.request.contextPath}/web-resources/images/default.png"></div>
		<div class="right">
			<textarea></textarea>
			<div class="submitwrap"><input type="submit" class="rounded" value="送信"></div>
		</div>
	</form>
</div>
<div class="readreply">
</div>
<script>
getReply();
</script>