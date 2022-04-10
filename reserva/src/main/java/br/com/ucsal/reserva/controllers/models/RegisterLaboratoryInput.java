package br.com.ucsal.reserva.controllers.models;

import lombok.Data;

@Data
public class RegisterLaboratoryInput {

    private Long requesterId;
    private String block;
    private String number;
    private String equipments;

}
