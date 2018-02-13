<%@ page contentType="text/html;charset=utf-8" import="beans.*" %>
<% 
	UsersBean lub = (UsersBean)request.getAttribute("result"); %>
	<div class="profilearea">
		<div class="avatar"><img src="${pageContext.request.contextPath}/web-resources/images/default.png"></div>
		<div class="accountname">
			<a href="${pageContext.request.contextPath}/?u=<%=lub.getLoginID() %>"><%=lub.getUserName() %></a>
		</div>
		<p class="accountinfo">
		loginID: <%=lub.getLoginID() %>
		password: <%=lub.getPassword() %>
		userNmae: <%=lub.getUserName() %>
		登録日: <%=lub.getRegistrationDate() %>
		紹介: <%=lub.getIntroduction() %>
		icon: <%=lub.getIconPath() %>
		header: <%=lub.getHeaderImagePath() %>
		
		PostCount: <%=lub.getPostCount() %>
		FollowCount: <%=lub.getFollowCount() %>
		FollowerCount:  <%=lub.getFollowerCount() %>
		
		</p>
	</div>