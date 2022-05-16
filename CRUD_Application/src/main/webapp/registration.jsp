<%@page import="java.util.ArrayList"%>
<%@page import="in.sts.CRUD_Application.support.selectList"%>
<html>
<head>


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
<script
	src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="multiselect/jquery.multiselect.js"></script>
<link rel="stylesheet" href="multiselect/jquery.multiselect.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin=>
<style>
body {
	background-image: url("images/download.jpg");
}

.form-label, .form-check-label {
	color: #fff;
}

h1 {
	color: #fff;
}

.mt-100{margin-top: 100px}body{background: #00B4DB;background: -webkit-linear-gradient(to right, #0083B0, #00B4DB);background: linear-gradient(to right, #0083B0, #00B4DB);color: #514B64;min-height: 100vh}
</style>


</head>
<body>
	<h1 style="text-align: center;">Signup Form</h1>
	<div class="container">
		<div class="row">
			<form class="row g-3" action="registration" method="post">
				<div class="col-12">
					<label for="firstName" class="form-label">FirstName</label> <input
						type="text" name="firstName" class="form-control" 
						id="inputAddress2">
				</div>
				<div class="col-12">
					<label for="firstName" class="form-label">LastName</label> <input
						type="text" name="lastName" class="form-control"
						id="inputAddress2">
				</div>
				<div class="col-md-6">
					<label for="inputEmail4" class="form-label">city</label> <input
						type="text" name="city" class="form-control" id="inputEmail4">
				</div>
				<div class="col-md-6">
					<label for="inputPassword4" class="form-label">job</label> <input
						type="text" name="job" class="form-control" id="inputPassword4">
				</div>
				<div class="row d-flex justify-content-left mb-5 mt-5">
					<div class="col-md-6">
					<lable for="education" class="form-label">Educations</lable>
						<select id="choices-multiple-remove-button" name="education"
						 multiple>
						 <%selectList educationList=new selectList();
							ArrayList<String> getList=   educationList.educationList();
							for(String education:getList){
							%>
						 
							<option value=<%=education %> ><%=education %></option>
							<<!-- option value="MBA">MBA</option>
							<option value="BSC-IT">BSC-IT</option>
							<option value="MSC-IT">MSC-IT</option>
							<option value="BSC">BSC</option>
							<option value="BMM">BMM</option>
							<option value="BAF">BAF</option>
							<option value="MCOM">MCOM</option>
							<option value="BE">BE</option>
							 -->
							 <%} %>
						</select>
					</div>
				</div>


				<div class="col-12">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" id="gridCheck"
							name="check"> <label class="form-check-label"
							for="gridCheck"> Check me out </label>
					</div>
				</div>
				<div class="col-12">
					<button type="submit" class="btn btn-primary checked-class">Sign
						in</button>
				</div>

			</form>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
	    
	     var multipleCancelButton = new Choices('#choices-multiple-remove-button', {
	        removeItemButton: true,
	        maxItemCount:5,
	        searchResultLimit:5,
	        renderChoiceLimit:5
	      });  
	 });
	</script>

</body>
</html>
