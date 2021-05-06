package com.laptrinhjavaweb.convert;

import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;

@Component
public class UserConvert {
     public UserDTO toDto(UserEntity entity)
     {
    	 UserDTO result =new UserDTO();
    	
    	 result.setFullName(entity.getFullName());
    	 result.setUserName(entity.getUserName());
    	 result.setPassword(entity.getPassword());
    	 result.setStatus(entity.getStatus());
    	 result.setId(entity.getId());
    	 result.setRoleCode(entity.getRoles().get(0).getCode());
    	 result.setPassword_convert(entity.getPassword_convert());
    	 return result;
     }
     public UserEntity toEntity(UserDTO dto)
     {
       UserEntity result =new UserEntity();
       
  	 result.setFullName(dto.getFullName());
  	 result.setUserName(dto.getUserName());
  	 result.setPassword(dto.getPassword());
  	 result.setStatus(dto.getStatus());
  	 result.setPassword_convert(dto.getPassword_convert());
       return result;
     }
     public UserEntity toEntity(UserDTO dto,UserEntity result)
     {    
  	 result.setFullName(dto.getFullName());
  	 result.setUserName(dto.getUserName());
  	 result.setPassword(dto.getPassword());
  	 result.setStatus(dto.getStatus());
  	 result.setPassword_convert(dto.getPassword_convert());
       return result;
     }
}
