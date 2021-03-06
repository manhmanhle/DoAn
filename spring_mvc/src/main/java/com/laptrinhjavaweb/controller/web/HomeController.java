package com.laptrinhjavaweb.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.DonViDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IDonViService;
import com.laptrinhjavaweb.service.IUserService;

@Controller(value = "HomeControllerOfWeb")
public class HomeController {
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IDonViService iDonViService;
   @RequestMapping(value = "/trang-chu", method = RequestMethod.GET)
   public ModelAndView homePage() {
      ModelAndView mav = new ModelAndView("web/home");	
      UserDTO dto =new UserDTO(); dto.setListResult(iUserService.find_All());
      mav.addObject("list", dto);
      return mav;
   }
   @RequestMapping(value = "/trang-chu/test", method = RequestMethod.GET)
   public ModelAndView homeTest(HttpServletRequest request) {
	   ModelAndView mav = new ModelAndView("web/report");	
	    //  List<DonViDTO> list =iDonViService.findAll();
	      return mav;
   }
   @RequestMapping(value = "/trang-chu/config", method = RequestMethod.GET)
   public ModelAndView Config(HttpServletRequest request) {
      ModelAndView mav = new ModelAndView("web/Config");	
      String fileName=request.getParameter("message");
      
      return mav;
   }
   @RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
   public ModelAndView Login() {
      ModelAndView mav = new ModelAndView("login");
      return mav;
   }
   @RequestMapping(value = "/thoat", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ModelAndView("redirect:/trang-chu");
	}
	
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		return new ModelAndView("redirect:/dang-nhap?accessDenied");
	}
  
}