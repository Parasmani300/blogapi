package com.paras.mai.web.blogapi.payloads;

import com.paras.mai.web.blogapi.entities.Category;
import com.paras.mai.web.blogapi.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
//    private Integer postId;
    private  String title;
    private String content;
    private String imageName = "default.png";
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
}
