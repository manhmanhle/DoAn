package com.laptrinhjavaweb.dto;

public class UserDTO extends AbstractDTO<UserDTO> {
	
	private String userName;
	private String fullName;
	private String password;
	private String password_convert;
	public String getPassword_convert() {
		return password_convert;
	}
	public void setPassword_convert(String password_convert) {
		this.password_convert = password_convert;
	}
	private int status;
	private String roleCode;
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	 
}
