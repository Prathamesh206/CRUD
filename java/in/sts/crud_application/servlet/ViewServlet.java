package in.sts.crud_application.servlet;

import java.io.IOException;
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
 * Servlet implementation class ViewServlet
 */
@WebServlet("/viewServlet")
public class ViewServlet extends HttpServlet {
	EmployeeDao employeeDao=new EmployeeDao();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Employee> employeeList=employeeDao.getEmployeeInfo();
		if(employeeList.size()==0) {
			RequestDispatcher req=request.getRequestDispatcher("WEB-INF/somethingWentWrong.jsp");
			req.forward(request, response);

		}else {
			request.setAttribute("employeeList", employeeList);
			RequestDispatcher req=request.getRequestDispatcher("WEB-INF/employeeList.jsp");
			req.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
