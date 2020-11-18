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

import com.franka.otterly.models.Post;
import com.franka.otterly.models.User;
import com.franka.otterly.services.PostService;
import com.franka.otterly.services.UserService;

@Controller
public class PostController {
	@Autowired
	private PostService pSer;
	@Autowired
	private UserService uSer;
	
	@GetMapping("/create")
	public String postForm(@ModelAttribute("post") Post post, Model model, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		//Verify there is a user in session
		if(userId == null)
			return "redirect:/";
		User user = uSer.getById(userId);
		model.addAttribute("user", user);
		return "createpost.jsp";
	}
	
	@PostMapping("/create")
	public String createPost(@Valid @ModelAttribute("post") Post post, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "createpost.jsp";
		}
		this.pSer.createPost(post);
		return "redirect:/";
	}
}
