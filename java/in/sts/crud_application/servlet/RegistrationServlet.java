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
import javax.servlet.http.HttpSession;
import org.apache.log4j.BasicConfigurator;

import in.sts.crud_application.dao.EmployeeDao;
import in.sts.crud_application.entity.Employee;






/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeDao employeeDao=new EmployeeDao();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BasicConfigurator.configure();
		HttpSession session=request.getSession();
		List<Employee> employeeList=employeeDao.getEmployeeInfo();
		if(employeeList.size()==0) {
			RequestDispatcher req=request.getRequestDispatcher("WEB-INF/somethingWentWrong.jsp");
			req.forward(request, response);
			
		}else {
			session.setAttribute("employeeList", employeeList);
			RequestDispatcher req=request.getRequestDispatcher("WEB-INF/employeeList.jsp");
			req.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String city=request.getParameter("city");
		String job=request.getParameter("job");
		List<String>educations=Arrays.asList(request.getParameterValues("education"));
		//getEmployee method check if the user alerday present or not
		Employee employee=	 employeeDao.getEmployee(firstName,lastName);     
		if(employee==null) {
			employee=new Employee(firstName, lastName, city, job);
			boolean result=(boolean) employeeDao.insert(employee, educations);
			if(result) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script type='text/javascript'>alert('User Registered Successfully"+response.SC_CREATED+"')</script>");
				RequestDispatcher req=request.getRequestDispatcher("WEB-INF/index.jsp");
				req.include(request, response);

				;
			}else {
				response.sendRedirect("error.jsp");
			}

		}else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>alert('User Alerady Present"+response.SC_BAD_REQUEST+"')</script>");
			RequestDispatcher req=request.getRequestDispatcher("WEB-INF/index.jsp");
			req.include(request, response);




		}
	}

}

