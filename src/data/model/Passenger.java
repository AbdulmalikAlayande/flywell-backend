package data.model;

public class Passenger {
	private String id;
	private String firstName;
	private String lastName;
	private String fullName;
	private String userName;
	private String Email;
	private String phoneNumber;
	private String password;
	
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
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName() {
		this.fullName = getFirstName() + " " + getLastName();
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return Passenger.class.getSimpleName()+"{" + "firstName ==>'" + firstName + '\'' +
				            ", lastName ==> " + lastName +
				            ", fullName ==> " + fullName +
					        ", Username ==> "+ userName+
				            ", Email ==> " + Email +
					        ", Password ==> "+ password+
				            ", id ==> " + id +
				            ", phoneNumber ==> " + phoneNumber + 
				            '}';
	}
}
