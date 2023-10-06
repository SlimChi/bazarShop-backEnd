package fr.cs.bazarshop.exeption;

import org.springframework.http.HttpStatus;

public class CustomResponse {
    private String message;
    private HttpStatus status;

    public CustomResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
