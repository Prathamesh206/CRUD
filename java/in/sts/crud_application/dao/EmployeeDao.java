package in.sts.crud_application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.sts.crud_application.connection.DBConnection;
import in.sts.crud_application.entity.Employee;




public class EmployeeDao {
	final static Logger log=Logger.getLogger(EmployeeDao.class);

	EducationDao educationDao=new EducationDao();
	Employee employee=new Employee();
	

	final int FIRSTNAME=1;
	final int LASTNAME=2;
	final int CITY =3;
	final int JOB=4;
	final int EXISTING_FIRSTNAME=5;
	final int EXISTING_LASTNAME=6;
	final int ID=1;
	final int UPDATE_CITY=1;
	final int UPDATE_JOB=2;
	final int UPDATE_FIRST_NAME=3;
	final int UPDATE_LAST_NAME=4;
	final int UPDATE_ID=5;
	

	
	/*
	 * 
	 * insert method for insert the employee in the dataBase
	 * 
	 * 
	 */
	public boolean insert(Employee employee, List<String> educations) {
		Connection connection=DBConnection.getConnection();
		
		//   for Insert data into employee_data & eduaction_data if the firstName and lastName not exist in database

		String insertquery="INSERT INTO employee_data(firstname,lastname,city,job) SELECT * FROM (SELECT ? AS firstname, ? AS"        //insert query for insert the employee in the database if the employee is not present in the database
				+ " lastname,? AS city,? AS job) AS temp WHERE NOT EXISTS (SELECT * FROM  employee_data WHERE firstname = ? "
				+ "and lastname=?)  ";

		try {
			
			connection.setAutoCommit(false);
			
			PreparedStatement insertStatment=connection.prepareStatement(insertquery);
			insertStatment.setString(FIRSTNAME, employee.getFirstName());
			insertStatment.setString(LASTNAME,employee.getLastName() );
			insertStatment.setString(CITY, employee.getCity());
			insertStatment.setString(JOB, employee.getJob());
			insertStatment.setString(EXISTING_FIRSTNAME, employee.getFirstName());
			insertStatment.setString(EXISTING_LASTNAME, employee.getLastName());
			int result=insertStatment.executeUpdate();
			Employee employee1=getEmployee(employee.getFirstName(), employee.getLastName());
			if(result>0) {
				System.out.println("Successfully Employee inserted");
				educationDao.insert(employee1.getId(), educations,connection);
				return true;

			}
			connection.commit();

		}	catch (SQLException sqlException) {
			try {

				if(connection!=null) {

					connection.rollback();
				}
			}
			catch (SQLException sqlException1) {
				log.error("message"+sqlException1);

			}
			log.error("message" + sqlException);
			System.out.println("Data not inserted");
		}finally {
			if(connection!=null) {
				try {
					connection.close();

				} catch (SQLException sqlException) {
					log.error("message" +sqlException);
				}
			}
		}
		return false;

	}

	/*
	 * 
	 * 
	 * 
	 *getEmployee method for fetch the single employee from the database;
	 * 
	 * 
	 */
	public  Employee getEmployee(String FirstName,String LastName) {
		Connection connection=DBConnection.getConnection();
		//		ArrayList<Employee> dataBaseEmployeeList=new ArrayList<Employee>();
		Employee employee = null;
		int id = 0;
		String job = null;
		String city = null;
		String firstName = null;
		String lastName = null;


		PreparedStatement preparedStatement=null;

		String selectQuery="select * from employee_data where firstname=? and lastname=?";       //select query for fetch the record from the database
		try {
			preparedStatement=connection.prepareStatement(selectQuery);
			preparedStatement.setString(FIRSTNAME,FirstName); 
			preparedStatement.setString(LASTNAME, LastName);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next()) {  
				job=result.getString("job");
				city=result.getString("city");
				id=result.getInt("id");
				firstName=result.getString("firstname");
				lastName=result.getString("lastname"); 
				employee=new Employee(id,firstName,lastName,city,job);
			} 
             
			return employee;

		} catch (SQLException sqlException) {
           log.error("message"+ sqlException);

		}finally {
			try {
				connection.close();
			} catch (SQLException sqlException) {
				// TODO Auto-generated catch block
				log.error("message"+sqlException);
			}
		}
		return null; 


	}
	/*
	 * 
	 * getEmployeeInfo method for fetch all the employee from the database
	 * 
	 * 
	 * 
	 */

	public ArrayList<Employee> getEmployeeInfo() {
		Connection connection=DBConnection.getConnection();
		ArrayList<Employee> employees=new ArrayList<Employee>();

		String sql="select * from employee_data";
		try {
			PreparedStatement selectStament=connection.prepareStatement(sql);
			ResultSet employeeList=selectStament.executeQuery();
			while(employeeList.next()) {
				
				List<String> educations= educationDao.getEducation(connection, employeeList.getInt("id"));
				
				Employee employee2=new Employee(employeeList.getInt("id"), employeeList.getString("firstname"),employeeList.getString("lastname"),employeeList.getString("city"),educations,employeeList.getString("job"));
				System.out.println("dao"+employee2);
				employees.add(employee2);

			}
		}catch(SQLException sqlException) {

			log.error("message"+ sqlException);
		}finally {
	
        try {
			connection.close();
		} catch (SQLException sqlException) {
		        log.error("message"+ sqlException);
	
		}
		}
		return employees;

	}
	
	/*
	 * delete method use for delete employee from the dataBase
	 * 
	 * 
	 */
	
	public boolean delete(int id)  {
		Connection connection=DBConnection.getConnection();
		String sql="delete from employee_data where id=?";
		try {
		PreparedStatement deleteStatement=connection.prepareStatement(sql);
		deleteStatement.setInt(ID, id);
		int deleteResult=deleteStatement.executeUpdate();
		if(deleteResult>0) {
			return true;
		}
		
		}catch(Exception exception) {
			log.error("meassage"+exception);
		}finally {
		try {
			connection.close();
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
			log.error("message"+ sqlException);
		}
		}
		return false;
		
	}
	

	/*
	 * 
	 * update method for update the employee data in database
	 */

	public boolean update(int id,Employee employee,List<String> educations)  {
		Connection connection=DBConnection.getConnection();

		PreparedStatement preparedStatement=null;
		String updateQuery="update employee_data set city=?,job=? ,firstname=?,lastname=?  where id=?";
		try {
			preparedStatement=connection.prepareStatement(updateQuery);
			preparedStatement.setString(UPDATE_CITY,employee.getCity());
			preparedStatement.setString(UPDATE_JOB, employee.getJob());
			preparedStatement.setString(UPDATE_FIRST_NAME,employee.getFirstName());
			preparedStatement.setString(UPDATE_LAST_NAME, employee.getLastName());
			preparedStatement.setInt(UPDATE_ID,id);
			educationDao.delete(connection, id, educations);
			int updateRow=preparedStatement.executeUpdate();
			
			if(updateRow>0) {
				return true;
			}
		} 

		catch (SQLException sqlException) {

			log.error("message" +sqlException);
		}finally {
		try {
			connection.close();
		} catch (SQLException sqlException) {
          log.error("message"+sqlException);
	
		}
		}
		return false;


	}
	/*
	 * 
	 * getEmployee method for fetch the single employee from the database
	 * 
	 */
	public Employee getEmployee(int id) {
		Connection connection=DBConnection.getConnection();
		Employee employee=null;
		String sql="select * from employee_data where id=?";
		try {
		PreparedStatement selectStatement=connection.prepareStatement(sql);
		selectStatement.setInt(ID, id);
		ResultSet employeeResult=selectStatement.executeQuery();
		while(employeeResult.next()) {
		List<String> educationList=    educationDao.getEducation(connection, id);
		 employee=new Employee(employeeResult.getInt("id"), employeeResult.getString("firstname"),employeeResult.getString("lastname"),employeeResult.getString("city"),educationList,employeeResult.getString("job"));
		}
		
		}catch(Exception exception) {
			log.error("message"+exception);
		}finally {
		try {
			connection.close();
		} catch (SQLException sqlException) {
			// TODO Auto-generated catch block
		 log.error("message"+sqlException);
		}
		}
		return employee;
		
	}
	
	
}	





