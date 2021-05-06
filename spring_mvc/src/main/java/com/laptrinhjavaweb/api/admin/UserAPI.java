package com.laptrinhjavaweb.api.admin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;

import net.sf.jasperreports.engine.JRException;

@RestController(value = "userAPIOfAdmin")
public class UserAPI {
	@Autowired
	private IUserService iUserService;
	@PostMapping("/api/user1")
	public String show(@RequestBody String  test) throws FileNotFoundException, JRException, IOException, SQLException, ClassNotFoundException {
		Object obj=JSONValue.parse(test);     
		JSONObject jsonObject = (JSONObject) obj; 
		return iUserService.exportReport(jsonObject);
		
	}
	@PostMapping("/api/user")
	public UserDTO createUser(@RequestBody UserDTO userDTO) {
		return iUserService.save(userDTO);
	}
	
	@PutMapping("/api/user")
	public UserDTO updateUser(@RequestBody UserDTO userDTO) {
		
		return iUserService.save(userDTO);
	}
	
	@DeleteMapping("/api/user")
	public void deleteUser(@RequestBody long[] ids) {
	     iUserService.Delete(ids);
	}
}