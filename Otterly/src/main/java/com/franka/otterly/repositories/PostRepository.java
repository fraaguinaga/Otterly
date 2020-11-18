package com.franka.otterly.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.franka.otterly.models.Post;
import com.franka.otterly.models.User;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
	List<Post> findAll();
	List<Post> findByUserOrderByCreatedAtDesc(User user);
}
