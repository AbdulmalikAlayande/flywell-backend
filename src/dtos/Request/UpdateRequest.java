package dtos.Request;

import org.jetbrains.annotations.Nullable;

public class UpdateRequest {
	@Nullable private String firstName;
	@Nullable private String lastName;
	@Nullable private String Email;
	@Nullable private String phoneNumber;
	@Nullable private String password;
	private String userName;
	
	@Nullable public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(@Nullable String firstName) {
		this.firstName = firstName;
	}
	
	@Nullable public String getLastName() {
		return lastName;
	}
	
	public void setLastName(@Nullable String lastName) {
		this.lastName = lastName;
	}
	
	@Nullable public String getEmail() {
		return Email;
	}
	
	public void setEmail(@Nullable String email) {
		Email = email;
	}
	
	@Nullable public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(@Nullable String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Nullable public String getPassword() {
		return password;
	}
	
	public void setPassword(@Nullable String password) {
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
