package com.paras.mai.web.blogapi.services.impl;

import com.paras.mai.web.blogapi.dao.CategoryRepo;
import com.paras.mai.web.blogapi.dao.PostRepo;
import com.paras.mai.web.blogapi.dao.UserRepo;
import com.paras.mai.web.blogapi.entities.Category;
import com.paras.mai.web.blogapi.entities.Post;
import com.paras.mai.web.blogapi.entities.User;
import com.paras.mai.web.blogapi.exception.ResourceNotFoundException;
import com.paras.mai.web.blogapi.payloads.PostDto;
import com.paras.mai.web.blogapi.services.PostService;
import javafx.geometry.Pos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User Id",userId));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category","category Id",categoryId));

        Post post = this.modelMapper.map(postDto,Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        this.postRepo.save(post);

        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("category","categoryId",categoryId));

        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream()
                            .map(post -> this.modelMapper.map(post,PostDto.class))
                            .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUsers(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(
                post -> this.modelMapper.map(post, PostDto.class)
        ).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyWords) {
        return null;
    }
}
