package com.ibeus.Comanda.Digital.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(){
        super("Client not found");
    }
}
