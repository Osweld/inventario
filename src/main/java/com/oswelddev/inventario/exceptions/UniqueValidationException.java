package com.oswelddev.inventario.exceptions;

public class UniqueValidationException extends RuntimeException{


    public UniqueValidationException(){
        super();
    }

    public UniqueValidationException(String message){
        super(message);
    }

}
