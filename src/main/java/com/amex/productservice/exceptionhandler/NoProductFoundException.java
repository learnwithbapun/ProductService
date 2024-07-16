package com.amex.productservice.exceptionhandler;

public class NoProductFoundException {
    String message;
    public NoProductFoundException(String message) {
        this.message = message;
    }
}
