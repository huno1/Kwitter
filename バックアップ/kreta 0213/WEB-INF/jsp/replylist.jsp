<%@ page contentType="text/html;charset=utf-8" import="beans.*, java.util.ArrayList"%>

	<ul>
		<% ArrayList<ReplyBean> ary = (ArrayList<ReplyBean>)request.getAttribute("result"); %>
		<% for(ReplyBean rb : ary){ %>
		<li id="<%=rb.getReplyID() %>">
			<div class="accountgroup">
				<div class="avatar"><img src="${pageContext.request.contextPath}/web-resources/images/default.png"></div>
				<div class="accountname"><a href="${pageContext.request.contextPath}/?u=<%=rb.getLoginID() %>"><%=rb.getUserName() %></a></div>
				<div class="postdate"><%=rb.getReplyDate() %></div>
			</div>
			<div class="contentgroup">
				<p class="postcontent"><%=rb.getReplyContent() %></p>
			</div>
			<!--
			<div class="menugroup">
				<a class="reply"><i class="far fa-comment"></i></i><span>29</span></a>
				<a class="reply"><i class="far fa-heart"></i><span>29</span></a>
			</div>
			-->
		</li>
		<% } %>
	</ul>