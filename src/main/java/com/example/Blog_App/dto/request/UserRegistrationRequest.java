package com.example.Blog_App.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z ]{2,30}$", message = "First name must contain only letters and spaces, and be 2 to 30 characters long")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z ]{2,30}$", message = "Last name must contain only letters and spaces, and be 2 to 30 characters long")
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$", message = "Invalid international phone number")
    private String phno;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "Password must be 8 to 15 characters long and include uppercase, lowercase, number, and special character"
    )
    private String password;

    @NotBlank(message = "Confirm password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "Password must be 8 to 15 characters long and include uppercase, lowercase, number, and special character"
    )
    private String confirmPassword;
}
