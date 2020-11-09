package com.franka.otterly.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franka.otterly.models.User;
import com.franka.otterly.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository uRepo;
	
	public User getByEmail(String email) {
		return this.uRepo.findByEmail(email);
	}
	
	public User getByUsername(String username) {
		return this.uRepo.findByUsername(username);
	}
	
	public User getById(Long id) {
		return this.uRepo.findById(id).orElse(null);
	}
	
	public List<User> usernamesContaining(String search){
		return this.uRepo.findByUsernameContaining(search);
	}
	
	public User createUser(User user) {
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hash);
		return this.uRepo.save(user);
	}
	
	public boolean validateUser(String email, String password) {
		User user = this.uRepo.findByEmail(email);
		if(user == null)
			return false;
		return BCrypt.checkpw(password, user.getPassword());
	}
	
	public void followOtherUser(User user, User otherUser) {
		List<User> following = user.getFollowing();
		List<User> othersFollowers = otherUser.getFollowers();
		if(!following.contains(otherUser)) {
			//otherUser added to users following list
			following.add(otherUser);
			this.uRepo.save(user);
			//user added to otherUsers followers list
			othersFollowers.add(user);
			this.uRepo.save(otherUser);
		}
	}
	
	public void unfollowOtherUser(User user, User otherUser) {
		List<User> following = user.getFollowing();
		List<User> othersFollowers = otherUser.getFollowers();
		if(following.contains(otherUser)) {
			//otherUser removed from users following list
			following.remove(otherUser);
			this.uRepo.save(user);
			//user removed from otherUsers followers list
			othersFollowers.remove(user);
			this.uRepo.save(otherUser);
		}
	}
	
	public User updateUser(User user) {
		return this.uRepo.save(user);
	}
	
	public void deleteUser(Long id) {
		this.uRepo.deleteById(id);
	}
	
	
}
