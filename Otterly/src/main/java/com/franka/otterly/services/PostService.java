package com.franka.otterly.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franka.otterly.models.Post;
import com.franka.otterly.models.User;
import com.franka.otterly.repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository rRepo;
	
	public List<Post> getPostsByUser(User user){
		return this.rRepo.findByUserOrderByCreatedAtDesc(user);
	}
	
	public Post getById(Long id) {
		return this.rRepo.findById(id).orElse(null);
	}
	
	public Post createPost(Post post) {
		return this.rRepo.save(post);
	}
	
	public void deletePost(Long id) {
		this.rRepo.deleteById(id);
	}
}
