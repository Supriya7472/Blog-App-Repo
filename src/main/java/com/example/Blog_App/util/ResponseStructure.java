package com.example.Blog_App.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ResponseStructure<T> {
    private int status;
    private String message;
    private T data;

    public static <T> ResponseStructure<T> create(HttpStatus status, String message, T data){
        ResponseStructure<T> response=new ResponseStructure<>();
        response.status=status.value();
        response.message=message;
        response.data=data;

        return response;
    }
}
