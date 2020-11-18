package com.franka.otterly.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.franka.otterly.models.Post;
import com.franka.otterly.models.User;
import com.franka.otterly.services.PostService;
import com.franka.otterly.services.UserService;
import com.franka.otterly.validations.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService uSer;
	@Autowired
	private PostService pSer;
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
		List<Post> posts = user.getPosts();
		List<User> following = user.getFollowing();
		for(User follow: following) {
			posts.addAll(follow.getPosts());
		}
		System.out.println(posts);
		model.addAttribute("user", user);
		model.addAttribute("posts", posts);
		return "home.jsp";
	}
	
	@PostMapping("/home")
	public String searchUsers(HttpSession session, Model model, @RequestParam("searchstr") String search) {
		Long userId = (Long)session.getAttribute("user_id");
		//Verify there is a user in session
		if(userId == null)
			return "redirect:/";
		List<User> users = uSer.usernamesContaining(search);
		model.addAttribute("searched", search);
		model.addAttribute("user", this.uSer.getById(userId));
		model.addAttribute("results", users);
		return "search.jsp";
	}
	
	@GetMapping("/_{username}")
	public String userProfile(@PathVariable("username") String username, Model model, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		//Verify there is a user in session
		if(userId == null)
			return "redirect:/";
		
		User user = this.uSer.getByUsername(username);
		model.addAttribute("profileUser", user);
		model.addAttribute("user", this.uSer.getById(userId));
		
		return "profile.jsp";
	}
	
	@GetMapping("/_{username}/likes")
	public String profileLikes(@PathVariable("username") String username, Model model, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		//Verify there is a user in session
		if(userId == null)
			return "redirect:/";
		
		User user = this.uSer.getByUsername(username);
		model.addAttribute("profileUser", user);
		model.addAttribute("user", this.uSer.getById(userId));
		
		return "profilelikes.jsp";
	}
	
	@GetMapping("/_{username}/edit")
	public String editUser(@PathVariable("username") String username, Model model, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		//Verify there is a user in session
		if(userId == null)
			return "redirect:/";
		User user = uSer.getById(userId);
		User profileUser = uSer.getByUsername(username);
		if(profileUser == null || user == null || !user.equals(profileUser)) {
			return "redirect:/";
		}
		
		model.addAttribute("user", profileUser);
		return "edit.jsp";
	}
	
	@PostMapping("/_{username}/edit")
	public String updateUser(@PathVariable("username") String username, @Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		//Verify there is a user in session
		if(userId == null)
			return "redirect:/";
		if(result.hasErrors()) {
			return "edit.jsp";
		}
		
		uSer.updateUser(user);
		return "redirect:/_" +username;
	}
	
	@PostMapping("/_{username}/f")
	public String follow(@PathVariable("username") String username, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		//Verify there is a user in session
		if(userId == null)
			return "redirect:/";
		
		User user = this.uSer.getById(userId);
		User otherUser = this.uSer.getByUsername(username);
		this.uSer.followOtherUser(user, otherUser);
		
		return "redirect:/_" +username;
	}
	
	@PostMapping("/_{username}/uf")
	public String unfollow(@PathVariable("username") String username, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");
		if(userId == null)
			return "redirect:/";
		
		User user = this.uSer.getById(userId);
		User otherUser = this.uSer.getByUsername(username);
		this.uSer.unfollowOtherUser(user, otherUser);
		
		return "redirect:/_" +username;
	}	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
