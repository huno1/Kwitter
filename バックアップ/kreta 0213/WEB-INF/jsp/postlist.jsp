<%@ page contentType="text/html;charset=utf-8" import="beans.*" %>
				<% 
					PostBean pb = (PostBean)request.getAttribute("result");
					String mediapath = pb.getPostMedia();
					if(pb.getPostID()!=null) { %>
						<li id="${result.postID}" onclick="javascript:getPost(${result.postID});">
							<div class="accountgroup">
								<div class="avatar"><img src="${pageContext.request.contextPath}/web-resources/${result.avatar}"></div>
								<div class="accountname"><a href="${pageContext.request.contextPath}/?u=" style="pointer-events:none">${result.userName}</a></div>
								<div class="postdate">${result.postDate}</div>
							</div>
							<div class="contentgroup">
								<p class="postcontent">${result.postContent}</p>
								<div class="postimage">
								<%
									if(mediapath.indexOf(".jpg") > 1 || mediapath.indexOf(".png") > 1 || mediapath.indexOf(".gif") > 1){ %>
								<img src="${pageContext.request.contextPath}/web-resources/${result.postMedia}">
									<% }else if(mediapath.indexOf(".mp4") > 1){ %>
								<video src="${result.postMedia}">
									<% }				
								%></div>
							</div>
							<div class="menugroup">
								<a class="reply"><i class="far fa-comment"></i></i><span>${result.replyCount}</span></a>
								<a class="reply"><i class="far fa-heart"></i><span>${result.likeCount}</span></a>
							</div>
						</li>
				<% } %>