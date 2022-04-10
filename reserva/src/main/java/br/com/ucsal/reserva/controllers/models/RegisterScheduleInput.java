package br.com.ucsal.reserva.controllers.models;

import br.com.ucsal.reserva.domains.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterScheduleInput {

    private Long requesterId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateTimeStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateTimeEnd;
    private State state;
    private String reason;
}
