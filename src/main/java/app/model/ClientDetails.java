package app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Table(name = "client_details")
public class ClientDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_client_details", unique = true)
	private int id;
	@NotEmpty
	@Size(max = 20)
	private String firstName;
	@NotEmpty
	@Size(max = 20)
	private String lastName;
	@NotEmpty
	private String city;
	@NotEmpty
	@Size(max = 30)
	private String address;
	@NotEmpty
	@Email
	private String email;
	@PositiveOrZero
	@Max(999999999)
	@Min(100000000)
	private int phoneNumber;
	
	public ClientDetails() {}
	
	public ClientDetails(String firstName, String lastName, String city, String address, String email,
			int phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
