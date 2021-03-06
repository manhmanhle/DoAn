package com.laptrinhjavaweb.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.convert.UserConvert;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserConvert userConvert;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired 
	private PasswordEncoder crypt;
	@Override
	public List<UserDTO> findAll(Pageable pageable) {
		List<UserDTO> models =new ArrayList<UserDTO>();
		List<UserEntity> entities = userRepository.findAll(pageable).getContent();
		for (UserEntity userEntity : entities) {
			UserDTO model =new UserDTO();
			model=userConvert.toDto(userEntity);
			models.add(model);
				
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		// TODO Auto-generated method stub
		return (int) userRepository.count();
	}

	@Override
	public UserDTO findById(long id) {
		// TODO Auto-generated method stub
		UserEntity user =userRepository.findOne(id);
		return userConvert.toDto(user);
	}

	@Override
	@Transactional
	public UserDTO save(UserDTO user) {
		RoleEntity role = roleRepository.findOneByCode(user.getRoleCode());
		UserEntity userEntity =new UserEntity();
		if(user.getId() != null)
		{
			UserEntity old = userRepository.findOne(user.getId());
			List<RoleEntity> list =new ArrayList<>();
			list.add(role);
			old.setRoles(list);
			user.setPassword(crypt.encode(user.getPassword_convert()));
			userEntity =userConvert.toEntity(user, old);
		}
		else
		{
			user.setPassword(crypt.encode(user.getPassword_convert()));
			userEntity =userConvert.toEntity(user);
			List<RoleEntity> list =new ArrayList<>();
			list.add(role);
			userEntity.setRoles(list);
		}
		return userConvert.toDto(userRepository.save(userEntity));
	}

	@Override
	public Map<Integer,String> findAll() {
		Map<Integer,String> result =new HashMap<>();
		List<UserEntity> entities =userRepository.findAll();
		    String check="";
			for (UserEntity Entity : entities) {	
				     if(Entity.getStatus() ==1)
				     {
				    	 check="Ho???t ?????ng";
				     }
				     else {
				    	 check="T???m d???ng";
				     }
					 result.put(Entity.getStatus(), check);			
			}
						
		return result;
	}

	@Override
	public void Delete(long [] ids) {
		for (long id : ids) {
			userRepository.delete(id);
		}
		
	}
	@Override
	public String exportReport(JSONObject object) throws JRException, IOException, SQLException, ClassNotFoundException {
        String pathPdf = "C:\\file\\";
        String pathJP = "C:\\file\\jasper\\";
        String b64="";
        String tenfile =(String) object.get("tenfile");
        String type =(String) object.get("type");
        String userName=(String) object.get("userName");
        //get file local
        InputStream reportStream = new FileInputStream(pathJP+tenfile+".jrxml");
        //set fonts
		  String defaultPDFFont = "Times New Roman";
		  JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font","true"); 
		  JRProperties.setProperty("net.sf.jasperreports.default.font.name", defaultPDFFont);
		//gener report
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        Map<String,Object> parameters = new HashMap<>();  
        parameters.put("createby",userName);
        Connection connect = null;
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/springmvcbasicfree","root","123456");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connect);
        if (type.equalsIgnoreCase("xlsx")) {
        	File xlsFile = new File(pathPdf+tenfile+".xlsx");
            JRXlsxExporter Xlsxexporter = new JRXlsxExporter();
            Xlsxexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            Xlsxexporter.setParameter(JRExporterParameter.OUTPUT_FILE, xlsFile);
            Xlsxexporter.exportReport();
            byte[] inFileBytes = Files.readAllBytes(Paths.get(pathPdf+tenfile+".xlsx")); 
            b64 = java.util.Base64.getEncoder().encodeToString(inFileBytes);
        }
        if (type.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPdf+tenfile+".pdf");
            byte[] inFileBytes = Files.readAllBytes(Paths.get(pathPdf+tenfile+".pdf")); 
            b64 = java.util.Base64.getEncoder().encodeToString(inFileBytes);
        }
        if (type.equalsIgnoreCase("docx")) {
        	File xlsFile = new File(pathPdf+tenfile+".docx");
        	JRDocxExporter export = new JRDocxExporter();
        	export.setExporterInput(new SimpleExporterInput(jasperPrint));
        	export.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsFile));

        	SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
        	//config.setFlexibleRowHeight(true); //Set desired configuration

        	export.setConfiguration(config);            
        	export.exportReport();
        	 byte[] inFileBytes = Files.readAllBytes(Paths.get(pathPdf+tenfile+".docx")); 
             b64 = java.util.Base64.getEncoder().encodeToString(inFileBytes);
        }
        return b64;
    }

	@Override
	public List<UserDTO> find_All() {
		List<UserDTO> models =new ArrayList<UserDTO>();
		List<UserEntity> entities = userRepository.findAll();
		for (UserEntity userEntity : entities) {
			UserDTO model =new UserDTO();
			model=userConvert.toDto(userEntity);
			models.add(model);
				
		}
		return models;
	}

}
