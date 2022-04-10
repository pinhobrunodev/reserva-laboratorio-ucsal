package br.com.ucsal.reserva.services.exceptions;

public class DatabaseException extends  RuntimeException{
    public DatabaseException(String msg){
        super(msg);
    }
}
