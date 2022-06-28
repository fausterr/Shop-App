package app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import app.constraint.UserChangePass;
import app.constraint.UserPass;

//@Component
public class UserDTO {
	
	private String login;
	@UserChangePass
	@NotEmpty
	private String oldPassword;
	@NotEmpty
	@Size(max = 5)
	@UserPass
	private String newPassword;
	
	private String firstName;
	
	private String lastName;
	
	public UserDTO() {}
	
	public UserDTO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public UserDTO(String login, String newPassword, String oldPassword) {
		this.login = login;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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
}
