<%@ page contentType="text/html;charset=utf-8" import="beans.*" %>
				<% 
					PostBean pb = (PostBean)request.getAttribute("result");
					if(pb.getPostID()!=null) { %>
						<li id="${result.postID}" onclick="javascript:getPost(${result.postID});">
							<div class="accountgroup">
								<div class="avatar"><img src="${result.avatar}"></div>
								<div class="accountname"><a href="/user?_uid${result.userID}">${result.userName}</a></div>
								<div class="postdate">${result.postDate}</div>
							</div>
							<div class="contentgroup">
								<p class="postcontent">${result.postContent}</p>
								<div class="postimage">
								<%
									String mediapath = pb.getPostMedia();
									if(mediapath.indexOf(".jpg") > 1){ %>
								<img src="${result.postMedia}">
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