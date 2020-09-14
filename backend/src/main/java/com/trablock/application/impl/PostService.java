package com.trablock.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trablock.application.IPostService;
import com.trablock.domain.Post;
import com.trablock.domain.repository.IPostRepository;

@Service
public class PostService implements IPostService {

	@Autowired
	private IPostRepository postRepository;
	
	@Override
	public int registePost(Post post) {
		return postRepository.createPost(post);
	}

	@Override
	public Post searchPostById(String postId) {
		return postRepository.selectPostById(postId);
	}

	@Override
	public List<Post> searchPosts(String title, String destination, String range_start, String range_end, String userId,
			String type) {
		return postRepository.selectPosts(title, destination, range_start, range_end, userId, type);
	}

	@Override
	public int updatePost(Post post) {
		return postRepository.updatePost(post);
	}

	@Override
	public int removePost(String postId, String userId) {
		return postRepository.deletePost(postId, userId);
	}

}
