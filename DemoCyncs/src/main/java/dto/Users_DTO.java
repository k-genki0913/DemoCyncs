package dto;

import java.io.Serializable;
import java.sql.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Users_DTO implements Serializable{
	private String user_id;
	private String name;
	private String mail_address;
	private boolean admin;
	private boolean is_valid;
	private Date update_date;
	private String update_user;
	
	public Users_DTO() {}
	
	public Users_DTO(String user_id, String name, String mail_address, 
			boolean admin, boolean is_valid, Date update_date, String update_user) {
		this.user_id = user_id;
		this.name = name;
		this.mail_address = mail_address;
		this.admin = admin;
		this.is_valid = is_valid;
		this.update_date = update_date;
		this.update_user = update_user;
	}
	
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	
	public String getUser_id() {
		return this.user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMail_address() {
		return this.mail_address;
	}
	
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	
	public boolean getAdmin() {
		return this.admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public boolean getIs_valid() {
		return this.is_valid;
	}
	
	public void setIs_valid(boolean is_valid) {
		this.is_valid = is_valid;
	}
	
	public Date getUpdate_date() {
		return this.update_date;
	}
	
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	public String getUpdate_user() {
		return this.update_user;
	}
	
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}
	
}
