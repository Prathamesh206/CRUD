package in.sts.crud_application.connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	
	private final static String url="jdbc:mysql://localhost:3306/employee_database";
	private final static String userName="root";
	private final static String password="root";
	/*
	 * 
	 *  getConnection method use for return the connection
	 * 
	 */
	public static Connection getConnection() {
		Connection dbConnection = null;
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 dbConnection=DriverManager.getConnection(url,userName,password);
		
		}
		catch(Exception sqlException) {
			System.out.println("message"+sqlException);
			
		}
		return dbConnection;
	}

}
