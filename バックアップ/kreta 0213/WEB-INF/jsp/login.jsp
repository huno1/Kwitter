<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="beans.*" %>

<html>
<body>
<h1>login</h1>
${result}
<% UsersBean ub = (UsersBean)session.getAttribute("loginUser"); %>
     <% if(ub == null || ub.getLoginID().equals("null")) { %>
	<form method="post" action="login">
	  	ログインID<input type="text" name="loginid"><br>
		パスワード<input type="password" name="pass"><br>
	<input type="submit" value="送信">
</form>

<% }else{ %>
	<p><%= ub.getUserName() %></p>
	<a href="/kreta">スタート画面へ</a>
	
<% } %>
</body>
</html>
