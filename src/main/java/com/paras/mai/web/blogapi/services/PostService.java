package com.paras.mai.web.blogapi.services;

import com.paras.mai.web.blogapi.entities.Category;
import com.paras.mai.web.blogapi.entities.Post;
import com.paras.mai.web.blogapi.payloads.PostDto;
import javafx.geometry.Pos;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    Post updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPost();

    Post getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUsers(Integer userId);

    List<Post> searchPosts(String keyWords);

}
