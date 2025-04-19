package com.example.Blog_App.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(@NotBlank(message = "Email is required")
                           @Email(message = "Please provide a valid email address")
                           String email,

                           @NotBlank(message = "Password is required")
                           @Pattern(
                                   regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$",
                                   message = "Password must be 8 to 64 characters long and include uppercase, lowercase, number, and special character"
                           )
                           String password) {
}
