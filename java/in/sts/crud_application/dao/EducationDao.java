package in.sts.crud_application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;



public class EducationDao {
	final static Logger log=Logger.getLogger(EducationDao.class);
	final int EMP_ID=1;
	final int EDUCATION=2;
	/*
	 * 
	 *
	 * insertEducation method for storing the education of employee in the table
	 * 
	 * 
	 */

	public  boolean insert(int empid,List<String>educations,Connection connection) {

		PreparedStatement educationStatment=null;
		@SuppressWarnings("unused")
		int count=1;
		int rowInserted=0;
		try {

			String query="insert into education_data values(educationID,?,?)";
			educationStatment=connection.prepareStatement(query);
			educationStatment.setInt(EMP_ID,empid);

			for(String education:educations) {

				educationStatment.setString(EDUCATION, education);
				rowInserted =educationStatment.executeUpdate();
				count++;
			}
			if(rowInserted>0) {
				System.out.println("Succeessfully education inserted");
				return true;

			}

		}

		catch (SQLException sqlException) {

			if(connection!=null) {
				try {
					connection.rollback();
				} catch (SQLException sqlException1) {
					log.error("message" +sqlException1);

				}
				System.out.println("Data not inserted");
			}

		}finally {
			if(educationStatment!=null) {
				try {
					educationStatment.close();
				} catch (SQLException sqlException) {
					log.error("message"+ sqlException);
				}
			}
		}

		return false;

	}
	/*
	 * 
	 * getEducations method for fetch on the educationData from the database
	 */

	public List<String>   getEducation(Connection connection,int empId) {
		String education=null;                      

		List<String> educations=new ArrayList<String>();
		PreparedStatement  preparedStatement=null;
		String educationQuery="select * from education_data  where empid = ?";                                         
		try {
			preparedStatement =connection.prepareStatement(educationQuery);
			preparedStatement.setInt(EMP_ID, empId);
			ResultSet educationSet=preparedStatement.executeQuery();
			while(educationSet.next()) {
				int id=educationSet.getInt("empid");

				education=	educationSet.getString("education");

				educations.add(education);
			}

		} catch (SQLException sqlException) {

			log.error("message" +sqlException);
		}
		return educations;

	}
 	/*
	 * delete method for delete the education from the database and insert the new education in it.
	 * 
	 */
	public void  delete(Connection connection,int empid,List<String> newEducations) {

		try {

			String deleteQuery="delete from education_data where empid=? ";

			PreparedStatement deleteStatement=connection.prepareStatement(deleteQuery);
			deleteStatement.setInt(EMP_ID,empid);  
			int result=deleteStatement.executeUpdate();
			if(result>0) {
				insert(empid,newEducations,connection);
				
			}
			//				
		}


		catch (SQLException sqlExcpeption) {
			// TODO Auto-generated catch block
			log.error("message" +sqlExcpeption);
		}

	


	}





}
