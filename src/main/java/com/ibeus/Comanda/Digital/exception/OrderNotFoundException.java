package com.ibeus.Comanda.Digital.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(){
        super("Order not found");
    }
}
