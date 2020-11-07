package com.franka.otterly.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.franka.otterly.models.User;
import com.franka.otterly.repositories.UserRepository;

@Component
public class UserValidator {
	@Autowired
	private UserRepository uRepo;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	public void validateUser(Object target, Errors errors) {
		User user = (User)target;
		
		//Checks username for characters only [A-Z, a-z, 0-9] allowed
		for(int i = 0; i<user.getUsername().length(); i++) {
			char currentChar = user.getUsername().charAt(i);
			if( !(currentChar >='A'&& currentChar <= 'Z') 
				&& !(currentChar >= 'a' && currentChar <= 'z') 
				 && !(currentChar >= '0' && currentChar <= '9')) {
				errors.rejectValue("username", "invalid username chars", "Only characters [A-Z, a-z, 0-9] allowed!");
				break;
			}	
		}
		
		//Confirms username selected is unique
		if(this.uRepo.existsByUsername(user.getUsername())) {
			errors.rejectValue("username", "unique_username_failed", "Username already taken!" );
		}
		
		//Confirms password and confirm password match
		if(!user.getPassword().equals(user.getConfirmPw())) {
			errors.rejectValue("password", "password mismatch", "Passwords do not match!");
		}
		
		//Allows prompting of empty confirm password field
		if(user.getConfirmPw().isEmpty()) {
			errors.rejectValue("confirmPw", "confirm password empty", "Cannot be blank!");
		}
		
		//Confirms email selected is unique
		if(this.uRepo.existsByEmail(user.getEmail())) {
			errors.rejectValue("email","unique_email_failed", "Email already registered!");
		}
	}
}