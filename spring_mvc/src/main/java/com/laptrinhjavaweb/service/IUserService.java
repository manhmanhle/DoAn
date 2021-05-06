package com.laptrinhjavaweb.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.UserDTO;

import net.sf.jasperreports.engine.JRException;

public interface IUserService {
	List<UserDTO> findAll(Pageable pageable);
	List<UserDTO> find_All();
	Map<Integer,String> findAll();
	int getTotalItem();
	UserDTO findById(long id);
	UserDTO save(UserDTO user);
	void Delete(long [] ids);
	String exportReport(JSONObject object) throws FileNotFoundException, JRException, IOException, SQLException, ClassNotFoundException;
}
