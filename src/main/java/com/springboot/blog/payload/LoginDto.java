package com.springboot.blog.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
