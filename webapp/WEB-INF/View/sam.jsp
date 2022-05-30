<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>my name is khan</h1>
<%   
     ArrayList<String> Employees=(ArrayList<String>)request.getAttribute("name");
     for(String Employee:Employees) {   %>
     <h1>FIRST NAME IS <%=Employee %></h1>
    	 
  <%   }%> 



</body>
</html>