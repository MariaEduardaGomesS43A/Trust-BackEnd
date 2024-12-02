package com.ibeus.Comanda.Digital.model.enums;

public enum OrderStatus {

    PREPARING(1),
    SHIPPED(2),
    DELIVERED(3),
    CANCELED(4);

    private Integer code;

    OrderStatus(Integer code) {
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static OrderStatus valueOf(Integer code){
        for (OrderStatus value : OrderStatus.values()){
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus");
    }
}
