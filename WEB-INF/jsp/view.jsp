<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="java.util.ArrayList, tera.Product" %>

<html>
<body>
<h1>商品一覧</h1>
<table>
	<tr><th>商品番号</th><th>商品名</th><th>価格</th></tr>
	<% 
	ArrayList<Product> result = (ArrayList)request.getAttribute("result");
	for(Product p : result){ %>
		<tr><td><%=p.getPid() %></td><td><%=p.getName() %></td><td><%=p.getPrice() %></td></tr>
	<% } %>
</table>
</body>
</html>