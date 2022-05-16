<%@page import="in.sts.CRUD_Application.dao.EmployeeDao"%>
<%@page import="in.sts.CRUD_Application.entity.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	<style>
	
body{

background-color: rgb(135,206,250);


}
	
	
	
	</style>
</head>
<body>
	<%
	EmployeeDao employeeDao=new EmployeeDao();
	ArrayList<Employee> employeeList = employeeDao.getEmployeeInfo();
	int count=1;
	%>
 <h1 align="center">Employee Details</h1>
	<div class="container">
		<div class="row">
			

			<table class="table table-striped table-dark">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">FirstName</th>
						<th scope="col">LastName</th>
						<th scope="col">City</th>
						<th scope="col">job</th>
						<th scope="col">Educations</th>
						<th scope="colspan-2" name="action">Action</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Employee employee : employeeList) {
					%>
		
			
					<tr>
						<th scope="row"><%= count %></th>
						<td><%= employee.getFirstName() %></td>
						<td><%= employee.getLastName() %></td>
						<td><%= employee.getCity() %></td>
                        <td><%= employee.getJob() %></td>
                        <td><%= String.join(",", employee.getEducations()) %> </td>
                        <td><a href="registration?action=edit&id=<%=employee.getId() %>">edit</a> &nbsp;
                        <a href="registration?action=delete&id=<%=employee.getId() %>">delete</a>
                        </td>
                        
						
					</tr>
					<%
				count++;	}
					%>


				</tbody>
			</table>
		</div>
	</div>

                         <h1 align="center"> <a href="index.jsp">Home</a></h1>

</body>
</html>