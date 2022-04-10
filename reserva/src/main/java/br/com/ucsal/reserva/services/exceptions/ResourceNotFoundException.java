package br.com.ucsal.reserva.services.exceptions;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
