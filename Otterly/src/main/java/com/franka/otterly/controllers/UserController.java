package com.franka.otterly.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.franka.otterly.models.User;
import com.franka.otterly.services.UserService;
import com.franka.otterly.validations.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService uSer;
	@Autowired
	private UserValidator uVal;
	
	@GetMapping("")
	public String index(HttpSession session) {
		//If user in session send them to home page
		if(session.getAttribute("user_id") != null)
			return "redirect:/home";
		return "index.jsp";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, @RequestParam("email") String email, @RequestParam("pword") String pword, RedirectAttributes ratt ) {
		//Authenticates user
		if(!uSer.validateUser(email, pword)) {
			ratt.addFlashAttribute("loginError", "Invalid Credentials!");
			return "redirect:/";
		}
		//Places user into session
		User user  = uSer.getByEmail(email);
		session.setAttribute("user_id", user.getId());
		return "redirect:/home";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("user") User user) {
		return "register.jsp";
	}
	
	@PostMapping("")
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		//Validates user input
		uVal.validateUser(user, result);
		if(result.hasErrors()) {
			return "register.jsp";
		}
		User newuser = this.uSer.createUser(user);
		session.setAttribute("user_id", newuser.getId());
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		Long userId = (Long)session.getAttribute("user_id");
		//Verify there is a user in session
		if(userId == null)
			return "redirect:/";
		
		User user = uSer.getById(userId);
		model.addAttribute("user", user);
		return "home.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
