package com.example.Blog_App.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    @Pattern(regexp = "^[A-Za-z ]{2,30}$", message = "User name must contain only letters and spaces, and be 2 to 30 characters long")
    private String userName;

    @Pattern(regexp = "^[6-9]\\d{9}$",message = "Invalid phone number")
    private String phno;

    @Email(message = "Please provide a valid email address")
    private String email;

}
