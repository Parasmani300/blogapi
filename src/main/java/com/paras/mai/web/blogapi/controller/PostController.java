package com.paras.mai.web.blogapi.controller;

import com.paras.mai.web.blogapi.entities.Post;
import com.paras.mai.web.blogapi.payloads.PostDto;
import com.paras.mai.web.blogapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("/user/{userId}/category/{categoryId}/")
    public ResponseEntity<PostDto> postPost(@PathVariable Integer userId,@PathVariable Integer categoryId, @RequestBody PostDto postDto)
    {
        PostDto post = this.postService.createPost(postDto,userId,categoryId);

        return  new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId)
    {
        List<PostDto> post = this.postService.getPostsByCategory(categoryId);
        return  new ResponseEntity<>(post,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
    {
        List<PostDto> postDtos = this.postService.getPostsByUsers(userId);
        return  new ResponseEntity<>(postDtos,HttpStatus.OK);
    }
}
