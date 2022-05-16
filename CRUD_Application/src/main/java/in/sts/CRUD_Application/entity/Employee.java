package in.sts.CRUD_Application.entity;


import java.util.List;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private String city;
	private List<String> educations ;
	private String job;
	
	public Employee(int id, String firstName, String lastName, String city, String job) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.job = job;
	}



	

	public Employee() {
	
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



	

	public List<String> getEducations() {
		return educations;
	}



	public void setEducations(List<String> educations) {
		this.educations = educations;
	}



	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}








	public Employee(int id, String firstName, String lastName, String city, List<String> educations, String job) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.educations = educations;
		this.job = job;
	}



	public Employee(String firstName, String lastName, String city, String job) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.job = job;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city
				+ ", educations=" + educations + ", job=" + job + "]";
	}



	public Employee(String firstName, String lastName, String city, List<String> educations, String job) {
	
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.educations = educations;
		this.job = job;
	}



	

}


