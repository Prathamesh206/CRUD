package in.sts.crud_application.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import in.sts.crud_application.dao.EmployeeDao;
import in.sts.crud_application.entity.Employee;


/**
 * Servlet implementation class updateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	EmployeeDao  employeeDao=new EmployeeDao();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id=Integer.parseInt(request.getParameter("id"));
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String city=request.getParameter("city");
		String job=request.getParameter("job");
		List<String>educations=Arrays.asList(request.getParameterValues("education"));
		Employee employee=new Employee(firstName, lastName, city, job);
		boolean result = employeeDao.update(id, employee,educations );
		if(result) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>alert('User Updated Successfully"+response.SC_ACCEPTED+"' )</script>");
			response.sendRedirect("viewServlet");
		}else {
			RequestDispatcher req=request.getRequestDispatcher("WEB-INF/error.jsp");
			req.forward(request, response);
		}
	}

}
