package com.myApp.productService.dtos;

public class ExceptionDto {
    private String message;
    private String solution;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getMessage() {
        return message;
    }

    public String getSolution() {
        return solution;
    }
}
