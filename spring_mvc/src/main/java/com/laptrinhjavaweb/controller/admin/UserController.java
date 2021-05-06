package com.laptrinhjavaweb.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.MessageUtil;

@Controller(value = "userControllerOfAdmin")
public class UserController {
	@Autowired
	private IUserService IUserService;
	
	@Autowired
	private IRoleService roleService;
    @Autowired 
    private MessageUtil messageUtil;
	@RequestMapping(value = "/quan-tri/nguoi-dung", method = RequestMethod.GET)
	public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit,
			HttpServletRequest request) {

		UserDTO model = new UserDTO();
		model.setPage(page);
		model.setLimit(limit);
		ModelAndView mav = new ModelAndView("admin/list_user");
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		Pageable pageable = new PageRequest(page - 1, limit);
		model.setListResult(IUserService.findAll(pageable));
		model.setTotalItem(IUserService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
		mav.addObject("model", model);
		return mav;
	}

	@RequestMapping(value = "/quan-tri/nguoi-dung/chinh-sua", method = RequestMethod.GET)
	public ModelAndView editList(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("admin/edit_user");
		UserDTO userDto = new UserDTO();
		if (id != null) {
			userDto = IUserService.findById(id);
		}
		if (request.getParameter("message") != null) {
			Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
			mav.addObject("message", message.get("message"));
			mav.addObject("alert", message.get("alert"));
		}
		mav.addObject("role", roleService.findAll());
		mav.addObject("st", IUserService.findAll());
		// userDto.setPassword(crypt.encode(userDto.getPassword()));
		mav.addObject("model", userDto);
		return mav;
	}
	
}
