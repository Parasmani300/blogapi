package com.paras.mai.web.blogapi.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {


    private Integer id;

    @NotEmpty
    @Size(min=4,message = "UserName must be minimum of 4 characters")
    private String name;

    @Email(message = "Email address not valid!!")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 3,max = 10,message = "Password must be minimum of 3 chars and max of 10 chars")
    private String password;

    @NotEmpty(message = "About cannot be left empty!!")
    private String about;

}
