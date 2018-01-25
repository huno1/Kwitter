<%@ page contentType="text/html;charset=utf-8" import="beans.*"%>
<div class="postsingle" id="${result.postID}">
	<div class="accountgroup">
		<div class="left">
	<div class="avatar"><img src="${result.avatar}"></div>
	<div class="accountname"><a href="">${result.userID}</a></div>
		</div>
		<div class="follow"><a href="" class="rounded">フォローする</a></div>
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
		<div class="avatar"><img src="${pageContext.request.contextPath}/web-resources/images/zjYn7dKv_400x400.jpg"></div>
		<div class="right">
			<textarea></textarea>
			<div class="submitwrap"><input type="submit" class="rounded" value="送信"></div>
		</div>
	</form>
</div>
<div class="readreply">
	<ul>
		<li>
			<div class="accountgroup">
				<div class="avatar"><img src="${pageContext.request.contextPath}/web-resources/images/zjYn7dKv_400x400.jpg"></div>
				<div class="accountname"><a href="">セブン</a></div>
				<div class="postdate">1月 22日</div>
			</div>
			<div class="contentgroup">
				<p class="postcontent">当たるといいな！</p>
			</div>
			<div class="menugroup">
				<a class="reply"><i class="far fa-comment"></i></i><span>29</span></a>
				<a class="reply"><i class="far fa-heart"></i><span>29</span></a>
			</div>
		</li>
		<li>
			<div class="accountgroup">
				<div class="avatar"><img src="${pageContext.request.contextPath}/web-resources/images/zjYn7dKv_400x400.jpg"></div>
				<div class="accountname"><a href="">セブン</a></div>
				<div class="postdate">1月 22日</div>
			</div>
			<div class="contentgroup">
				<p class="postcontent">当たるといいな！</p>
			</div>
			<div class="menugroup">
				<a class="reply"><i class="far fa-comment"></i></i><span>29</span></a>
				<a class="reply"><i class="far fa-heart"></i><span>29</span></a>
			</div>
		</li>
	</ul>
</div>
