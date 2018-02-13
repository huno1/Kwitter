<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="beans.*" %>

<html>
<body>
<h1>プロフィール編集</h1>
${result}
<% UsersBean ub = (UsersBean)session.getAttribute("loginUser"); %>
<% if(ub == null) { %>
	<p>ログインしていません。ログインしてくだし。</p>
	<a href="/kreta">START画面へ</a>
<% }else{ %>
<p>ログイン中のユーザー名：<%= ub.getUserName() %></p>
<form method="post" action="editprofile">
	<p>新しいユーザー名：　　<input type="text" name="username" maxlength="20"></p>
	<p>新しい紹介文：　　　　<textarea name="introduction" cols="30" rows="5" maxlength="160"></textarea></p>
	<p>新しいアイコン画像：　<input type="iconpath" name="iconpath" maxlength="500"></p>
	<p>新しいヘッダー画像：　<input type="headerimagepath" name="headerimagepath" maxlength="500"></p>
<input type="submit" value="送信">
<br><br>
	<% }%>
</body>
</html>
