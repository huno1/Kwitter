<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<% 
	Boolean isfollowed = (Boolean)request.getAttribute("result");
	out.print(isfollowed); %>