package com.example.Blog_App.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    /**
     * Helps creating the success responses with data including
     * the HttpStatus code, message and the data itself.
     *
     * @param  status Specifies HttpStatus for success
     * @param message Specifies success message
     * @param data Specifies success data
     *
     * @Returns ResponseEntity of type ResponseStructure of type T
     * */
    public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message , T data){
        ResponseStructure<T> structure=ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .body(structure);

    }

    /**
     * Helps to provide status code(OK) for general operations using success method
     * Acts as factory method for success method
     *
     * @param message success message
     * @param data response data
     *
     * @Returns ResponseEntity of type ResponseStructure of type T
     * */
    public static <T>ResponseEntity<ResponseStructure<T>> ok(String message , T data){
        return success(HttpStatus.OK,message,data);
    }


    /**
     * Helps to provide status code(created) for general operations using success method
     * Acts as factory method for success method
     *
     * @param message success message
     * @param data response data
     *
     * @Returns ResponseEntity of type ResponseStructure of type T
     * */
    public static <T>ResponseEntity<ResponseStructure<T>> created(String message , T data){
        return success(HttpStatus.CREATED,message,data);
    }


    public static <T>ResponseEntity<ResponseStructure<T>> found(String message, T data){
        return success(HttpStatus.FOUND,message,data);
    }

    /**
     * Helps creating the success responses with data including
     * the HttpStatus code, message and the data itself.
     *
     * @param  status Specifies HttpStatus for succes
     * @param message Specifies success message
     * @param data Specifies success data
     * @param headers specifies headers
     *
     * @Returns ResponseEntity of type ResponseStructure of type T
     * */

    public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, HttpHeaders headers, String message, T data){
        ResponseStructure<T> structure=ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .headers(headers)
                .body(structure);
    }

    /**
     * Helps creating the error responses with HttpStatus code and message.
     *
     * @param  status -> Specifies HttpStatus for error
     * @param  message -> Specifies error message
     *
     * @return ResponseEntity of type SimpleErrorResponse
     * */
    public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status,String message){
        SimpleErrorResponse error=SimpleErrorResponse.builder()
                .type(status.name())
                .status(status.value())
                .message(message)
                .build();

        return ResponseEntity.status(status)
                .body(error);

    }


    /**
     * Helps to provide status code(created) for general operations using success method
     * Acts as factory method for success method
     *
     * @param message success message
     *
     * @Returns ResponseEntity of type ResponseStructure of type SimpleErrorResponse
     * */
    public static ResponseEntity<SimpleErrorResponse> notFound(String message){
        return error(HttpStatus.NOT_FOUND,message);
    }

    public static ResponseEntity<SimpleErrorResponse> forbidden(String message){
        return error(HttpStatus.FORBIDDEN,message);
    }
}
