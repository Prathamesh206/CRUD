<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%   LocalDateTime time=(LocalDateTime) request.getAttribute("time");
      
%>

<h1> The time is know is<%= time %> </h1>
</body>
</html>