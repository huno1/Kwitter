<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="beans.*" %>

<html>
<body>
<h1>パスワード変更</h1>
${result}
<% UsersBean ub = (UsersBean)session.getAttribute("loginUser"); %>
<% if(ub == null) { %>
	<p>ログインしていません。ログインしてくだし。</p>
	<a href="/kreta">START画面へ</a>
<% }else{ %>
<p>ログイン中のユーザー名：<%= ub.getUserName() %></p>
<form method="post" action="editpassword">
	<p>現在のパスワード：　　　　<input type="password" name="beforepass" maxlength="15"></p>
	<p>新しいパスワード：　　　　<input type="password" name="afterpass" maxlength="15"></p>
	<p>確認のためにもう一度：　　<input type="password" name="checkpass" maxlength="15"></p>
<input type="submit" value="送信">
<br><br>
	<% }%>
</body>
</html>
