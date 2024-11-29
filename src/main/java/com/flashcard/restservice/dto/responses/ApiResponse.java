package com.flashcard.restservice.dto.responses;

public class ApiResponse<T> {

    private String message;
    private T data;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Convenience methods to create common responses
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("Success", data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message,  null);
    }
}

