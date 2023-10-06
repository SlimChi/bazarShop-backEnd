package fr.cs.bazarshop.exeption;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public String HandleProductNotFounfException(){
        return null;
    }
}
