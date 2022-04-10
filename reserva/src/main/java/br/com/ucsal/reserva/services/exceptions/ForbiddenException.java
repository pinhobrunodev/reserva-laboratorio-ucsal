package br.com.ucsal.reserva.services.exceptions;

public class ForbiddenException extends  RuntimeException{
    public ForbiddenException(String msg){
        super(msg);
    }
}
