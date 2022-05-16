package in.sts.CRUD_Application.servlet;

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

import in.sts.CRUD_Application.dao.EmployeeDao;
import in.sts.CRUD_Application.entity.Employee;

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

		String action=request.getParameter("action");
		if(action!=null && action.equals("edit")) {
			int id=Integer.parseInt(request.getParameter("id"));
			HttpSession  session=request.getSession();
			Employee employee=employeeDao.getEmployee(id);

			session.setAttribute("employee", employee);
			response.sendRedirect("updateuser.jsp");

		}else if(action!=null && action.equals("delete")) {
			int id=Integer.parseInt(request.getParameter("id"));
			boolean deleteResult=employeeDao.delete(id);
			if(deleteResult) {
				response.sendRedirect("employeeList.jsp");
			}else {
				response.sendRedirect("error.jsp");
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String checked=request.getParameter("check");
		if(checked==null) {
			response.sendRedirect("Please check the box");
		}else {

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
					out.println("<script type='text/javascript'>alert('User Registered Successfully')</script>");
					RequestDispatcher req=request.getRequestDispatcher("index.jsp");
					req.include(request, response);

					;
				}else {
					response.sendRedirect("error.jsp");
				}

			}else {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println("<script type='text/javascript'>alert('User Alerady Present')</script>");
				RequestDispatcher req=request.getRequestDispatcher("index.jsp");
				req.include(request, response);




			}
		}

	}
}
