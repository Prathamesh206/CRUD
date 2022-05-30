<%@page import="com.sts.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Student Information</h1>
	<%
	 User user=(User)request.getAttribute("name");
	/* String email = (String) request.getAttribute("email"); */
	%>
	<%
/* 	String name = (String) request.getAttribute("name"); */
	%>
	<%
	/* String password = (String) request.getAttribute("password"); */
	%>

	<h1> 
		<%-- email is=<%=email%></h1> --%>
		Email is <%= user.getEmail() %>
	<h1>
		<%-- name is=<%=name%></h1> --%>
		name is<%= user.getName() %>
	<h1>
	<%-- 	password is=<%=password%></h1> --%>
	password is<%=user.getPassword() %>

</body>
</html>