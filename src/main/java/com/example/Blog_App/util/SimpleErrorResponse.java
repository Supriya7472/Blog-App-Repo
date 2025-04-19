package com.example.Blog_App.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class SimpleErrorResponse {
    private String type;
    private int status;  //404
    private String message;  //failed to update the user, The user is not found by the given
}
