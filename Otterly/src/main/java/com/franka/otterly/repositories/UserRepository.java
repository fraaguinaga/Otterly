package com.franka.otterly.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.franka.otterly.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findAll();
	User findByEmail(String email);
	User findByUsername(String username);
	boolean existsByEmail(String email);
	boolean existsByUsername(String username);
}
