package com.franka.otterly.services;

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
	
	public User updateUser(User user) {
		return this.uRepo.save(user);
	}
	
	public void deleteUser(Long id) {
		this.uRepo.deleteById(id);
	}
	
	
}
