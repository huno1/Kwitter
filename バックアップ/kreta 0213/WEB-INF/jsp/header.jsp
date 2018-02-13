<%@ page contentType="text/html;charset=utf-8" import="beans.*" %>
<!doctype html>
<html lang="" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
	<script src="${pageContext.request.contextPath}/web-resources/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/web-resources/js/jquery.magnific-popup.min.js"></script>
	<script src="${pageContext.request.contextPath}/web-resources/js/chat.js"></script>
	<script src="${pageContext.request.contextPath}/web-resources/js/main.js"></script>
	<link href="${pageContext.request.contextPath}/web-resources/js/magnific-popup.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/web-resources/fontawesome-free-5.0.4/web-fonts-with-css/css/fontawesome-all.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/web-resources/style.css" />
  </head>
  <body>
	<header>
		<div class="inner9 clearfix">
			<ul class="head_l clearfix">
				<li><a href="${pageContext.request.contextPath}"><i class="fas fa-home"></i><span>ホーム</span></a></li>
				<li><a href=""><i class="far fa-bell"></i><span>通知</span></a></li>
			</ul>
			<div class="head_r clearfix">
				<form class="searchform" method="get" action=".">
					<input type="text" name="s">
				</form>
				<% //UsersBean ub = (UsersBean)session.getAttribute("loginUser"); %>
				<div class="avatar"><a href=""><img src="${pageContext.request.contextPath}/web-resources/images/default.png"></a></div>
				<div><a href="javascript:openwrite();" class="rounded">サケブ</a></div>
			</div>
		</div>
	</header>
	