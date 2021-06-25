package com.syscho.grpc.exception;

public class ProductNotFoundException extends   RuntimeException {
    public ProductNotFoundException(String msg){
        super(msg);
    }

}
