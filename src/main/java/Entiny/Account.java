package Entiny;

import java.io.InputStream;
import java.sql.Time;
import java.sql.Timestamp;

public class Account {
	
	//field
	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private int status;
	private int role;
	private Timestamp created_at;
	private Timestamp updated_at;
	
	
	
	

	//getter setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	//constructer
	public Account(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	
	

}
