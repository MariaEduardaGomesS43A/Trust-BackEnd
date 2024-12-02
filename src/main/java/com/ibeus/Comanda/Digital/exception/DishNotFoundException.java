package com.ibeus.Comanda.Digital.exception;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(){
        super("Dish not found");
    }
}
